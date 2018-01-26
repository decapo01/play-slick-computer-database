
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/decapo/apps/computer-database/conf/routes
// @DATE:Thu Jan 25 21:33:18 CST 2018


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
