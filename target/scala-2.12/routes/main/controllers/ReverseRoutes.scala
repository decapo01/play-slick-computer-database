
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/decapo/apps/computer-database/conf/routes
// @DATE:Thu Jan 25 21:33:18 CST 2018

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:6
package controllers {

  // @LINE:23
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:23
    def at(file:String): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[String]].unbind("file", file))
    }
  
  }

  // @LINE:6
  class ReverseApplication(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:20
    def delete(id:Long): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "computers/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)) + "/delete")
    }
  
    // @LINE:12
    def create(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "computers/new")
    }
  
    // @LINE:16
    def edit(id:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "computers/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:17
    def update(id:Long): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "computers/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:9
    def list(p:Int = 0, s:Int = 2, f:String = ""): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "computers" + play.core.routing.queryString(List(if(p == 0) None else Some(implicitly[play.api.mvc.QueryStringBindable[Int]].unbind("p", p)), if(s == 2) None else Some(implicitly[play.api.mvc.QueryStringBindable[Int]].unbind("s", s)), if(f == "") None else Some(implicitly[play.api.mvc.QueryStringBindable[String]].unbind("f", f)))))
    }
  
    // @LINE:13
    def save(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "computers")
    }
  
    // @LINE:6
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
  }


}
