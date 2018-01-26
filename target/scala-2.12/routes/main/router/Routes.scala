
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/decapo/apps/computer-database/conf/routes
// @DATE:Thu Jan 25 21:33:18 CST 2018

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  Application_1: controllers.Application,
  // @LINE:23
  Assets_0: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    Application_1: controllers.Application,
    // @LINE:23
    Assets_0: controllers.Assets
  ) = this(errorHandler, Application_1, Assets_0, "/")

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, Application_1, Assets_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.Application.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """computers""", """controllers.Application.list(p:Int ?= 0, s:Int ?= 2, f:String ?= "")"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """computers/new""", """controllers.Application.create"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """computers""", """controllers.Application.save"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """computers/""" + "$" + """id<[^/]+>""", """controllers.Application.edit(id:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """computers/""" + "$" + """id<[^/]+>""", """controllers.Application.update(id:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """computers/""" + "$" + """id<[^/]+>/delete""", """controllers.Application.delete(id:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.at(path:String = "/public", file:String)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_Application_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_Application_index0_invoker = createInvoker(
    Application_1.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """ Default path will just redirect to the computer list""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_Application_list1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("computers")))
  )
  private[this] lazy val controllers_Application_list1_invoker = createInvoker(
    Application_1.list(fakeValue[Int], fakeValue[Int], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "list",
      Seq(classOf[Int], classOf[Int], classOf[String]),
      "GET",
      this.prefix + """computers""",
      """ Computers list (look at the default values for pagination parameters)""",
      Seq()
    )
  )

  // @LINE:12
  private[this] lazy val controllers_Application_create2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("computers/new")))
  )
  private[this] lazy val controllers_Application_create2_invoker = createInvoker(
    Application_1.create,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "create",
      Nil,
      "GET",
      this.prefix + """computers/new""",
      """ Add computer""",
      Seq()
    )
  )

  // @LINE:13
  private[this] lazy val controllers_Application_save3_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("computers")))
  )
  private[this] lazy val controllers_Application_save3_invoker = createInvoker(
    Application_1.save,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "save",
      Nil,
      "POST",
      this.prefix + """computers""",
      """""",
      Seq()
    )
  )

  // @LINE:16
  private[this] lazy val controllers_Application_edit4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("computers/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_Application_edit4_invoker = createInvoker(
    Application_1.edit(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "edit",
      Seq(classOf[Long]),
      "GET",
      this.prefix + """computers/""" + "$" + """id<[^/]+>""",
      """ Edit existing computer""",
      Seq()
    )
  )

  // @LINE:17
  private[this] lazy val controllers_Application_update5_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("computers/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_Application_update5_invoker = createInvoker(
    Application_1.update(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "update",
      Seq(classOf[Long]),
      "POST",
      this.prefix + """computers/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:20
  private[this] lazy val controllers_Application_delete6_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("computers/"), DynamicPart("id", """[^/]+""",true), StaticPart("/delete")))
  )
  private[this] lazy val controllers_Application_delete6_invoker = createInvoker(
    Application_1.delete(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "delete",
      Seq(classOf[Long]),
      "POST",
      this.prefix + """computers/""" + "$" + """id<[^/]+>/delete""",
      """ Delete a computer""",
      Seq()
    )
  )

  // @LINE:23
  private[this] lazy val controllers_Assets_at7_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_at7_invoker = createInvoker(
    Assets_0.at(fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "at",
      Seq(classOf[String], classOf[String]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_Application_index0_route(params@_) =>
      call { 
        controllers_Application_index0_invoker.call(Application_1.index)
      }
  
    // @LINE:9
    case controllers_Application_list1_route(params@_) =>
      call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[Int]("s", Some(2)), params.fromQuery[String]("f", Some(""))) { (p, s, f) =>
        controllers_Application_list1_invoker.call(Application_1.list(p, s, f))
      }
  
    // @LINE:12
    case controllers_Application_create2_route(params@_) =>
      call { 
        controllers_Application_create2_invoker.call(Application_1.create)
      }
  
    // @LINE:13
    case controllers_Application_save3_route(params@_) =>
      call { 
        controllers_Application_save3_invoker.call(Application_1.save)
      }
  
    // @LINE:16
    case controllers_Application_edit4_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_Application_edit4_invoker.call(Application_1.edit(id))
      }
  
    // @LINE:17
    case controllers_Application_update5_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_Application_update5_invoker.call(Application_1.update(id))
      }
  
    // @LINE:20
    case controllers_Application_delete6_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_Application_delete6_invoker.call(Application_1.delete(id))
      }
  
    // @LINE:23
    case controllers_Assets_at7_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at7_invoker.call(Assets_0.at(path, file))
      }
  }
}
