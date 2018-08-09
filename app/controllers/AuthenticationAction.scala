package controllers

import javax.inject.Inject
import play.api.mvc._
import play.api.mvc.Results._

import scala.concurrent.{ExecutionContext, Future}



case class UserRequest[A](email: Option[String],roles: Seq[String],request: Request[A]) extends WrappedRequest[A](request)


class UserAction @Inject()(val parser: BodyParsers.Default)(implicit val executionContext: ExecutionContext) extends ActionBuilder[UserRequest,AnyContent] with ActionTransformer[Request,UserRequest]{

  def transform[A](request: Request[A]) = {

    val emailOpt = request.session.get("user")
    val rolesOpt = request.session.get("roles")

    (emailOpt,rolesOpt) match {
      case (Some(email),Some(roles)) =>
        Future.successful(UserRequest(Some(email),roles.split(","),request))
      case _ =>
        Future.successful(UserRequest(None,Seq(),request))
    }
  }
}


object AuthAction {

  def apply[A](allowedRoles: String*)(action: Action[A]) = Action.async(action.parser){ req =>

    val emailOpt = req.session.get("user")
    val rolesOpt = req.session.get("roles")

    val newReq = (emailOpt,rolesOpt) match {
      case (Some(email),Some(roles)) =>
        UserRequest(Some(email),roles.split(","),req)
      case _ =>
        UserRequest(None,Seq(),req)
    }

    action(newReq)
  }

  def async[A](allowedRoles: String*)(action: Action[A]) = Future.successful(Action.async(action.parser){ req =>

    val emailOpt = req.session.get("email")
    val rolesOpt = req.session.get("roles")

    val newReq = (emailOpt,rolesOpt) match {
      case (Some(email),Some(roles)) =>
        UserRequest(Some(email),roles.split(","),req)
      case _ =>
        UserRequest(None,Seq(),req)
    }

    action(newReq)
  })
}


object AuthenticationAction {

  def apply(rolesAllowed: String*)(implicit ec: ExecutionContext) = new ActionFilter[UserRequest]{

    override def executionContext: ExecutionContext = ec

    def filter[A](input: UserRequest[A]) = input.email match {
      case None =>
        Future.successful(Some(Forbidden("Forbidden")))
      case Some(email) =>
        if(input.roles.intersect(rolesAllowed).nonEmpty)
          Future.successful(None)
        else
          Future.successful(Some(Forbidden("Forbidden")))
    }
  }
}
