
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

object twitterBootstrapInput extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[helper.FieldElements,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(elements: helper.FieldElements):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.34*/("""

"""),format.raw/*5.52*/("""
"""),format.raw/*6.1*/("""<div class="clearfix """),_display_(/*6.23*/if(elements.hasErrors)/*6.45*/ {_display_(Seq[Any](format.raw/*6.47*/("""error""")))}),format.raw/*6.53*/("""">
    <label for=""""),_display_(/*7.18*/elements/*7.26*/.id),format.raw/*7.29*/("""">"""),_display_(/*7.32*/elements/*7.40*/.label),format.raw/*7.46*/("""</label>
    <div class="input">
        """),_display_(/*9.10*/elements/*9.18*/.input),format.raw/*9.24*/("""
        """),format.raw/*10.9*/("""<span class="help-inline">"""),_display_(/*10.36*/elements/*10.44*/.infos.mkString(", ")),format.raw/*10.65*/("""</span> 
    </div>
</div>
"""))
      }
    }
  }

  def render(elements:helper.FieldElements): play.twirl.api.HtmlFormat.Appendable = apply(elements)

  def f:((helper.FieldElements) => play.twirl.api.HtmlFormat.Appendable) = (elements) => apply(elements)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Thu Jan 25 21:33:18 CST 2018
                  SOURCE: /home/decapo/apps/computer-database/app/views/twitterBootstrapInput.scala.html
                  HASH: 94e3769fb0bd6f674946364c33089c9750cde380
                  MATRIX: 759->1|886->33|915->190|942->191|990->213|1020->235|1059->237|1095->243|1141->263|1157->271|1180->274|1209->277|1225->285|1251->291|1319->333|1335->341|1361->347|1397->356|1451->383|1468->391|1510->412
                  LINES: 21->1|26->1|28->5|29->6|29->6|29->6|29->6|29->6|30->7|30->7|30->7|30->7|30->7|30->7|32->9|32->9|32->9|33->10|33->10|33->10|33->10
                  -- GENERATED --
              */
          