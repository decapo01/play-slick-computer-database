package controllers

import dao.CompaniesDAO
import javax.inject.Inject
import models.Company
import play.api.data.Form
import play.api.data.Forms._
import play.api.db.slick.DatabaseConfigProvider
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, Call, ControllerComponents}
import play.api.mvc.Request
import play.api.mvc.Result
import repository.{CompanyCriteria, CompanyRepository, Roles}

import scala.concurrent.{ExecutionContext, Future}

class CompanyController @Inject()(userAction: UserAction,
                                  companyRepository: CompanyRepository,
                                  cc: ControllerComponents)(
                                  implicit ec: ExecutionContext) extends AbstractController(cc) with I18nSupport {


  val companyForm = Form[Company](
    mapping(
      "id" -> optional(longNumber),
      "name" -> nonEmptyText
    )(Company.apply _)(Company.unapply _)
  )


  def index(page: Int, sort: Int, filter: String) = (userAction andThen AuthenticationAction(Roles.user)).async { implicit req =>

    val criteria = CompanyCriteria(page = page)

    companyRepository.findPage(criteria).map { cPage =>

      val action: Call = routes.CompanyController.insert()

      Ok(views.html.company.companyList(cPage,1,""))
    }
  }

  def create = (userAction andThen AuthenticationAction(Roles.user)) { implicit  req =>

    val action = routes.CompanyController.insert()

    Ok(views.html.company.companyForm(companyForm,action))
  }

  def insert = (userAction andThen AuthenticationAction(Roles.user)).async {implicit req =>
    handleForm(None)
  }

  def edit(id: Long) = (userAction andThen AuthenticationAction(Roles.user)).async {implicit req =>

    companyRepository.findById(id).map {
      case None => NotFound
      case Some(company) =>

        val action = routes.CompanyController.update(id)

        Ok(views.html.company.companyForm(companyForm.fill(company), action))
    }
  }

  def update(id: Long) = (userAction andThen AuthenticationAction(Roles.user)).async{implicit req =>
    handleForm(Some(id))
  }

  def remove(id: Long) = (userAction andThen AuthenticationAction(Roles.user)).async { implicit req =>
    companyRepository.findById(id).map {
      case None => NotFound
      case Some(company) =>

        val action = routes.CompanyController.delete(id)

        Ok(views.html.company.companyForm(companyForm.fill(company), action))
    }
  }

  def delete(id: Long) = (userAction andThen AuthenticationAction(Roles.user)).async {implicit req =>
    companyRepository.delete(id).map{ _ =>
      Redirect(routes.CompanyController.index())
    }
  }


  def handleForm(id: Option[Long])(implicit req: Request[_]): Future[Result] = {
    companyForm.bindFromRequest().fold(
      error => Future.successful{
        val action = id match {
          case None => routes.CompanyController.insert()
          case Some(i) => routes.CompanyController.update(i)
        }
        BadRequest(views.html.company.companyForm(error,action))
      },
      company => {
        companyRepository.save(company.copy(id = id)).map { _ =>
          val msg = id match {
            case None => "Company Created"
            case Some(i) => "Company Updated"
          }
          Redirect(routes.CompanyController.index()).flashing("msg" -> msg)
        }
      }
    )
  }

}
