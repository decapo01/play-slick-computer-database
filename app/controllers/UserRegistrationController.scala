package controllers

import javax.inject.Inject
import org.apache.commons.lang3.{RandomStringUtils, StringUtils}
import org.apache.commons.text.RandomStringGenerator
import org.mindrot.jbcrypt.BCrypt
import play.api.data.Form
import play.api.mvc.{AbstractController, ControllerComponents, Request, Result}
import repository._
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.I18nSupport

import scala.concurrent.{ExecutionContext, Future}
import views.html.registration.{confirmFormTemp, registrationFormTemp}

case class RegistrationForm(email: String,password: String,confirmPassword: String)

case class ConfirmForm(code: String)

class UserRegistrationController @Inject()(userRepository: UserRepository, unregisteredUserRepository: UnregisteredUserRepository, cc: ControllerComponents)(implicit ec: ExecutionContext) extends AbstractController(cc) with I18nSupport {

  val registrationForm = Form[RegistrationForm]{
    mapping(
      "email"            -> email,
      "password"         -> nonEmptyText(minLength = 8, maxLength = 18).verifying(message, pw => validatePassword(pw)),
      "confirmPassword"  -> nonEmptyText
    )(RegistrationForm.apply)(RegistrationForm.unapply).verifying(
      "Passwords must match",
      fields => fields match {
        case data => data.password.equals(data.confirmPassword)
      }
    )
  }

  val confirmForm = Form[ConfirmForm](
    mapping(
      "code" -> nonEmptyText
    )(ConfirmForm.apply)(ConfirmForm.unapply)
  )

  def validatePassword(password: String): Boolean =
    password.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}")

  val message =
    """Password must be between 8 and 18 characters and must include a
    lowercase character, an uppercase character, a number and a symbol (@#$%^&+=)"""



  def get = Action { implicit req =>
    Ok(views.html.registration.registrationFormTemp(registrationForm))
  }

  def post = Action.async { implicit req =>
    registrationForm.bindFromRequest.fold(
      errors => {
        Future.successful{
          BadRequest(registrationFormTemp(errors))
        }
      },
      rForm => {

        val userCriteria = UserCriteria(emailEq = Some(rForm.email))

        userRepository.findByCriteria(userCriteria).flatMap {
          case Some(u) => Future.successful {
            BadRequest(registrationFormTemp(registrationForm.withError("global","An internal error has occurred.")))
          }
          case None =>

            val code = RandomStringUtils.random(10,true,true)

            println(s"you registration code is $code")

            val password = BCrypt.hashpw(rForm.password,BCrypt.gensalt())

            val uUser = User(None,rForm.email,password)

            unregisteredUserRepository.save(uUser).map { _ =>
              Redirect(routes.UserRegistrationController.getConfirm())
                .withSession(
                  req.session +
                  ("code" -> code) +
                  ("unregistered_user_email" -> rForm.email)
                )
            }
        }
      }
    )
  }

  def getConfirm = Action { implicit req =>
    Ok(confirmFormTemp(confirmForm))
  }

  def postConfirm = Action.async{ implicit req =>

    confirmForm.bindFromRequest().fold(
      errors => {
        Future.successful{
          BadRequest(confirmFormTemp(errors))
        }
      },
      confirm => {
        (req.session.get("code"),req.session.get("unregistered_user_email")) match {
          case (Some(code),Some(email)) => {
            if(code != confirm.code) {
              Future.successful{
                BadRequest(confirmFormTemp(confirmForm.fill(confirm)))
                  .flashing("msg" -> "The code you've entered does not match our records")
              }
            }
            else {
              unregisteredUserRepository.findByCriteria(UserCriteria(emailEq = Some(email))).flatMap {
                case None => Future.successful(Ok(""))
                case Some(uUser) => {

                  val user = User(None,uUser.email,uUser.password)

                  userRepository.insert(user,Seq(Roles.user)).flatMap { _ =>

                    unregisteredUserRepository.delete(uUser.id.get).map { _ =>
                      Redirect(routes.Application.index())
                        .flashing("msg"->"You've successfully signed up")
                        .withNewSession
                    }
                  }
                }
              }
            }
          }
          case _ => Future.successful {
            BadRequest(confirmFormTemp(confirmForm.fill(confirm).withError("global","An Internal error has occurred")))
          }
        }
      }
    )
  }
}
