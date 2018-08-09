package controllers

import javax.inject.Inject
import org.mindrot.jbcrypt.BCrypt
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, ControllerComponents}
import repository.{UserCriteria, UserRepository, UserRoleRepository}

import scala.concurrent.{ExecutionContext, Future}

case class LoginData(email: String,password: String)

class LoginController @Inject()(userRepository: UserRepository,
                                userRoleRepository: UserRoleRepository,
                                cc: ControllerComponents)(
                               implicit ed: ExecutionContext) extends AbstractController(cc)
                                                              with I18nSupport{


  val loginForm = Form[LoginData](
    mapping(
      "email" -> email,
      "password" -> nonEmptyText
    )(LoginData.apply _)(LoginData.unapply _)
  )


  val errorMessage = "The username password combination entered could not be found"

  def getLogin = Action{implicit req =>
    Ok(views.html.loginFormTemp(loginForm))
  }


  def postLogin = Action.async { implicit req =>

    loginForm.bindFromRequest.fold(
      error => {
        Future.successful {
          BadRequest(views.html.loginFormTemp(error))
        }
      },
      loginData => {
        userRepository.findByCriteria(UserCriteria(emailEq = Some(loginData.email))).flatMap{
          case None   => Future.successful(BadRequest(views.html.loginFormTemp(loginForm.withError("global",errorMessage))))
          case Some(user) =>
            userRoleRepository.findByUser(user.id.get).map { userRoles =>

              if(BCrypt.checkpw(loginData.password,user.password))
                Redirect(routes.Application.index())
                  .withSession{
                    req.session +
                      ("user"  -> user.email) +
                      ("roles" -> userRoles.map(_.role).reduce(_ + "," + _))
                  }
                  .flashing("msg" -> "You have successfully logged in")
              else
                BadRequest(views.html.loginFormTemp(loginForm.fill(loginData).withError("global",errorMessage)))
            }
        }
      }
    )
  }


  def logout = Action { implicit req =>
    Redirect(routes.Application.index())
      .withSession(req.session - "user" - "roles")
  }


}
