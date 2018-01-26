
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

object main extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.17*/("""

"""),format.raw/*3.1*/("""<!DOCTYPE html>
<html>
    <head>
        <title>Computers database</title>
        <link rel="stylesheet" type="text/css" media="screen" href=""""),_display_(/*7.70*/routes/*7.76*/.Assets.at("stylesheets/bootstrap.min.css")),format.raw/*7.119*/(""""> 
        <link rel="stylesheet" type="text/css" media="screen" href=""""),_display_(/*8.70*/routes/*8.76*/.Assets.at("stylesheets/main.css")),format.raw/*8.110*/(""""> 
    </head>
    <body>
        
        <header class="topbar">
            <h1 class="fill">
                <a href=""""),_display_(/*14.27*/routes/*14.33*/.Application.index()),format.raw/*14.53*/("""">
                    Play sample application &mdash; Computer database
                </a>
            </h1>
        </header>
        
        <section id="main">
            """),_display_(/*21.14*/content),format.raw/*21.21*/("""
        """),format.raw/*22.9*/("""</section>
        
    </body>
</html>
"""))
      }
    }
  }

  def render(content:Html): play.twirl.api.HtmlFormat.Appendable = apply(content)

  def f:((Html) => play.twirl.api.HtmlFormat.Appendable) = (content) => apply(content)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Thu Jan 25 21:33:18 CST 2018
                  SOURCE: /home/decapo/apps/computer-database/app/views/main.scala.html
                  HASH: 753e667a758e67804c666fdc44badbc7c1de338d
                  MATRIX: 726->1|836->16|864->18|1035->163|1049->169|1113->212|1212->285|1226->291|1281->325|1432->449|1447->455|1488->475|1695->655|1723->662|1759->671
                  LINES: 21->1|26->1|28->3|32->7|32->7|32->7|33->8|33->8|33->8|39->14|39->14|39->14|46->21|46->21|47->22
                  -- GENERATED --
              */
          