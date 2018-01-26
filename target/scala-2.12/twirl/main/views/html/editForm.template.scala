
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

object editForm extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Long,Form[Computer],Seq[scala.Tuple2[String, String]],Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(id: Long, computerForm: Form[Computer], companies : Seq[(String, String)])(implicit messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {
/*3.2*/import helper._

implicit def /*5.2*/implicitFieldConstructor/*5.26*/ = {{ FieldConstructor(twitterBootstrapInput.f) }};
Seq[Any](format.raw/*1.106*/("""

"""),format.raw/*4.1*/("""
"""),format.raw/*5.75*/("""

"""),_display_(/*7.2*/main/*7.6*/ {_display_(Seq[Any](format.raw/*7.8*/("""
    
    """),format.raw/*9.5*/("""<h1>Edit computer</h1>
    
    """),_display_(/*11.6*/form(routes.Application.update(id))/*11.41*/ {_display_(Seq[Any](format.raw/*11.43*/("""
        
        """),format.raw/*13.9*/("""<fieldset>
        
            """),_display_(/*15.14*/inputText(computerForm("name"), '_label -> "Computer name")),format.raw/*15.73*/("""
            """),_display_(/*16.14*/inputText(computerForm("introduced"), '_label -> "Introduced date")),format.raw/*16.81*/("""
            """),_display_(/*17.14*/inputText(computerForm("discontinued"), '_label -> "Discontinued date")),format.raw/*17.85*/("""
            
            """),_display_(/*19.14*/select(
                computerForm("company"), 
                companies, 
                '_label -> "Company", '_default -> "-- Choose a company --",
                '_showConstraints -> false
            )),format.raw/*24.14*/("""
        
        """),format.raw/*26.9*/("""</fieldset>
        
        <div class="actions">
            <input type="submit" value="Save this computer" class="btn primary"> or 
            <a href=""""),_display_(/*30.23*/routes/*30.29*/.Application.list()),format.raw/*30.48*/("""" class="btn">Cancel</a> 
        </div>
        
    """)))}),format.raw/*33.6*/("""
    
    """),_display_(/*35.6*/form(routes.Application.delete(id), 'class -> "topRight")/*35.63*/ {_display_(Seq[Any](format.raw/*35.65*/("""
        """),format.raw/*36.9*/("""<input type="submit" value="Delete this computer" class="btn danger">
    """)))}),format.raw/*37.6*/("""
    
""")))}),format.raw/*39.2*/("""
"""))
      }
    }
  }

  def render(id:Long,computerForm:Form[Computer],companies:Seq[scala.Tuple2[String, String]],messages:Messages): play.twirl.api.HtmlFormat.Appendable = apply(id,computerForm,companies)(messages)

  def f:((Long,Form[Computer],Seq[scala.Tuple2[String, String]]) => (Messages) => play.twirl.api.HtmlFormat.Appendable) = (id,computerForm,companies) => (messages) => apply(id,computerForm,companies)(messages)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Thu Jan 25 21:33:18 CST 2018
                  SOURCE: /home/decapo/apps/computer-database/app/views/editForm.scala.html
                  HASH: b5a6a6e5f479829e63619db8cf4e5473b7d07294
                  MATRIX: 788->1|965->108|1002->126|1034->150|1114->105|1142->124|1170->199|1198->202|1209->206|1247->208|1283->218|1342->251|1386->286|1426->288|1471->306|1531->339|1611->398|1652->412|1740->479|1781->493|1873->564|1927->591|2159->802|2204->820|2389->978|2404->984|2444->1003|2529->1058|2566->1069|2632->1126|2672->1128|2708->1137|2813->1212|2850->1219
                  LINES: 21->1|24->3|26->5|26->5|27->1|29->4|30->5|32->7|32->7|32->7|34->9|36->11|36->11|36->11|38->13|40->15|40->15|41->16|41->16|42->17|42->17|44->19|49->24|51->26|55->30|55->30|55->30|58->33|60->35|60->35|60->35|61->36|62->37|64->39
                  -- GENERATED --
              */
          