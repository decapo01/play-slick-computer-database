
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

object createForm extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Form[Computer],Seq[scala.Tuple2[String, String]],RequestHeader,Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(computerForm: Form[Computer], companies: Seq[(String, String)])(implicit request: RequestHeader, messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {
/*3.2*/import helper._

implicit def /*5.2*/implicitFieldConstructor/*5.26*/ = {{ FieldConstructor(twitterBootstrapInput.f) }};
Seq[Any](format.raw/*1.119*/("""

"""),format.raw/*4.1*/("""
"""),format.raw/*5.75*/(""" 

"""),_display_(/*7.2*/main/*7.6*/ {_display_(Seq[Any](format.raw/*7.8*/("""
    
    """),format.raw/*9.5*/("""<h1>Add a computer</h1>
    
    """),_display_(/*11.6*/form(routes.Application.save())/*11.37*/ {_display_(Seq[Any](format.raw/*11.39*/("""
       	"""),_display_(/*12.10*/helper/*12.16*/.CSRF.formField),format.raw/*12.31*/(""" 
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
            

        """),format.raw/*27.9*/("""</fieldset>
        
        <div class="actions">
            <input type="submit" value="Create this computer" class="btn primary"> or 
            <a href=""""),_display_(/*31.23*/routes/*31.29*/.Application.list()),format.raw/*31.48*/("""" class="btn">Cancel</a> 
        </div>
        
    """)))}),format.raw/*34.6*/("""
    
""")))}),format.raw/*36.2*/("""
"""))
      }
    }
  }

  def render(computerForm:Form[Computer],companies:Seq[scala.Tuple2[String, String]],request:RequestHeader,messages:Messages): play.twirl.api.HtmlFormat.Appendable = apply(computerForm,companies)(request,messages)

  def f:((Form[Computer],Seq[scala.Tuple2[String, String]]) => (RequestHeader,Messages) => play.twirl.api.HtmlFormat.Appendable) = (computerForm,companies) => (request,messages) => apply(computerForm,companies)(request,messages)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Thu Jan 25 22:00:55 CST 2018
                  SOURCE: /home/decapo/apps/computer-database/app/views/createForm.scala.html
                  HASH: 26588486fabf2a83d981d30d08fba4c06176b731
                  MATRIX: 799->1|989->121|1026->139|1058->163|1138->118|1166->137|1194->212|1223->216|1234->220|1272->222|1308->232|1368->266|1408->297|1448->299|1485->309|1500->315|1536->330|1573->340|1632->372|1712->431|1753->445|1841->512|1882->526|1974->597|2016->612|2248->823|2298->846|2485->1006|2500->1012|2540->1031|2625->1086|2662->1093
                  LINES: 21->1|24->3|26->5|26->5|27->1|29->4|30->5|32->7|32->7|32->7|34->9|36->11|36->11|36->11|37->12|37->12|37->12|38->13|40->15|40->15|41->16|41->16|42->17|42->17|44->19|49->24|52->27|56->31|56->31|56->31|59->34|61->36
                  -- GENERATED --
              */
          