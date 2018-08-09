package controllers

import javax.inject.{Inject, Singleton}
import dao.{CompaniesDAO, ComputersDAO}
import models.{Company, Computer, Page}
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, ControllerComponents, Flash, RequestHeader}
import views.html
import play.api.db.slick.DatabaseConfigProvider
import play.mvc.Security.AuthenticatedAction
import repository._

import scala.concurrent.ExecutionContext

/** Manage a database of computers. */
@Singleton()
class Application @Inject() (
    userAction: UserAction,
		dbConfigProvider: DatabaseConfigProvider,
    //companiesDao: CompaniesDAO,
    computersDao: ComputersDAO,
    computerRepository: ComputerRepository,
    companyRepository: CompanyRepository,
    controllerComponents: ControllerComponents
)(implicit executionContext: ExecutionContext) extends AbstractController(controllerComponents) with I18nSupport {



	val companiesDao: CompaniesDAO = new CompaniesDAO(dbConfigProvider)


  /** This result directly redirect to the application home.*/
  val Home = Redirect(routes.Application.list(0, 2, ""))



  /** Describe the computer form (used in both edit and create screens).*/
  val computerForm = Form(
    mapping(
      "id"           -> optional(longNumber),
      "name"         -> nonEmptyText,
      "introduced"   -> optional(date("yyyy-MM-dd")),
      "discontinued" -> optional(date("yyyy-MM-dd")),
      "company"      -> optional(longNumber)
    )(Computer.apply)(Computer.unapply)
  )



  // -- Actions

  /** Handle default path requests, redirect to computers list */
  def index = Action { Home }



  /**
   * Display the paginated list of computers.
   *
   * @param page Current page number (starts from 0)
   * @param orderBy Column to be sorted
   * @param filter Filter applied on computer names
   */
  def list(page: Int, orderBy: Int, filter: String) = Action.async { implicit request =>

    val inputFilter = if(filter.trim.isEmpty) None else Some(filter)

    val computerCriteria = ComputerCriteria(page = page, filter = inputFilter)

    for {
      computers <- computerRepository.findPage(computerCriteria)
      companyIds = computers.items.map(_.companyId).filter(_.isDefined).map(_.get)
      companyCriteria = CompanyCriteria(idsIn = Some(companyIds))
      companies <- companyRepository.findAllByCriteria(companyCriteria)
    }
    yield {
      val computerCompanyRelation = computers.items.map{ computer =>
        val companyForComputer = companies.find(_.id == computer.companyId).getOrElse(Company(id = None,name = ""))
        (computer,companyForComputer)
      }

      val computerPage = Page(items = computerCompanyRelation, page = computers.page, total = computers.total, offset = computers.offset)

      Ok(html.list(computerPage, orderBy, filter))
    }
  }


  /**
   * Display the 'edit form' of a existing Computer.
   *
   * @param id Id of the computer to edit
   */
  def edit(id: Long) = Action.async { implicit rs =>

    val computerAndOptions = for {
      computer <- computerRepository.findById(id)
      companies  <- companyRepository.findAll
    }
    yield {

      val options = companies.filter(_.id.isDefined).map(c => (c.id.get.toString,c.name))

      (computer, options)
    }

    computerAndOptions.map {
      case (computer, options) =>
        computer match {
          case Some(c) => Ok(html.editForm(id, computerForm.fill(c), options))
          case None    => NotFound
        }
    }
  }

  /**
   * Handle the 'edit form' submission
   *
   * @param id Id of the computer to edit
   */
  def update(id: Long) = Action.async { implicit rs =>

    computerForm.bindFromRequest.fold(

      formWithErrors =>
        companiesDao.options().map(options => BadRequest(html.editForm(id, formWithErrors, options))),

      computer => {

        computerRepository.save(computer.copy(id = Some(id))).map { c =>
          Home.flashing("success" -> s"Computer ${computer.name} has been updated")
        }
      }
    )
  }

  /** Display the 'new computer form'. */
  def create = Action.async { implicit rs =>

    companiesDao.options().map(options => Ok(html.createForm(computerForm, options)))

  }

  /** Handle the 'new computer form' submission. */
  def save = Action.async { implicit rs =>

    computerForm.bindFromRequest.fold(

      formWithErrors =>
        companiesDao.options().map(options => BadRequest(html.createForm(formWithErrors, options))),

      computer => {
        for {
          _ <- computersDao.insert(computer)
        }
        yield Home.flashing("success" -> "Computer %s has been created".format(computer.name))
      }
    )
  }

  /** Handle computer deletion. */
  def delete(id: Long) = Action.async { implicit rs =>

    for {
      _ <- computersDao.delete(id)
    }
    yield Home.flashing("success" -> "Computer has been deleted")
  }

  def restricted = (userAction andThen AuthenticationAction(Roles.admin)) { req =>
    Ok("ok")
  }
}
