package controllers

import dao.CompaniesDAO
import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.ExecutionContext

class CompanyController @Inject()(dbConfigProvider: DatabaseConfigProvider,
                                  companiesDAO: CompaniesDAO,
                                  cc: ControllerComponents)(
                                  implicit ec: ExecutionContext) extends AbstractController(cc) with I18nSupport {




  def index = Action.async {implicit req =>
    companiesDAO.findAll().map(page =>{
      Ok(views.html.company.companyList(page,1,""))
    })
  }

  def nuevo = Action { implicit  req =>
    Ok("")
  }

  def create = Action {implicit req =>
    Ok("")
  }

  def edit(id: Long) = Action {implicit req =>
    Ok("")
  }

  def update(id: Long) = Action {implicit req =>
    Ok("")
  }

  def remove(id: Long) = Action { implicit req =>
    Ok("")
  }

  def delete(id: Long) = Action {implicit req =>
    Ok("")
  }

}
