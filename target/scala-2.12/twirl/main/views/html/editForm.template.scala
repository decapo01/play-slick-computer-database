
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

object editForm extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Long,Form[Computer],Seq[scala.Tuple2[String, String]],RequestHeader,Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(id: Long, computerForm: Form[Computer], companies : Seq[(String, String)])(implicit request: RequestHeader, messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {
/*3.2*/import helper._

implicit def /*5.2*/implicitFieldConstructor/*5.26*/ = {{ FieldConstructor(twitterBootstrapInput.f) }};
Seq[Any](format.raw/*1.130*/("""

"""),format.raw/*4.1*/("""
"""),format.raw/*5.75*/("""

"""),_display_(/*7.2*/main/*7.6*/ {_display_(Seq[Any](format.raw/*7.8*/("""
    
    """),format.raw/*9.5*/("""<h1>Edit computer</h1>
    
    """),_display_(/*11.6*/form(routes.Application.update(id))/*11.41*/ {_display_(Seq[Any](format.raw/*11.43*/("""
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
        
        """),format.raw/*26.9*/("""</fieldset>
        
        <div class="actions">
            <input type="submit" value="Save this computer" class="btn primary"> or 
            <a href=""""),_display_(/*30.23*/routes/*30.29*/.Application.list()),format.raw/*30.48*/("""" class="btn">Cancel</a> 
        </div>
        
    """)))}),format.raw/*33.6*/("""
    
    """),_display_(/*35.6*/form(routes.Application.delete(id), 'class -> "topRight")/*35.63*/ {_display_(Seq[Any](format.raw/*35.65*/("""
				"""),_display_(/*36.6*/helper/*36.12*/.CSRF.formField),format.raw/*36.27*/("""
        """),format.raw/*37.9*/("""<input type="submit" value="Delete this computer" class="btn danger">
    """)))}),format.raw/*38.6*/("""
    
""")))}),format.raw/*40.2*/("""
"""))
      }
    }
  }

  def render(id:Long,computerForm:Form[Computer],companies:Seq[scala.Tuple2[String, String]],request:RequestHeader,messages:Messages): play.twirl.api.HtmlFormat.Appendable = apply(id,computerForm,companies)(request,messages)

  def f:((Long,Form[Computer],Seq[scala.Tuple2[String, String]]) => (RequestHeader,Messages) => play.twirl.api.HtmlFormat.Appendable) = (id,computerForm,companies) => (request,messages) => apply(id,computerForm,companies)(request,messages)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Thu Jan 25 22:04:56 CST 2018
                  SOURCE: /home/decapo/apps/computer-database/app/views/editForm.scala.html
                  HASH: de10271eeced15341958007e565e0ff462be79a4
                  MATRIX: 802->1|1003->132|1040->150|1072->174|1152->129|1180->148|1208->223|1236->226|1247->230|1285->232|1321->242|1380->275|1424->310|1464->312|1501->322|1516->328|1552->343|1588->352|1650->387|1730->446|1771->460|1859->527|1900->541|1992->612|2046->639|2278->850|2323->868|2508->1026|2523->1032|2563->1051|2648->1106|2685->1117|2751->1174|2791->1176|2823->1182|2838->1188|2874->1203|2910->1212|3015->1287|3052->1294
                  LINES: 21->1|24->3|26->5|26->5|27->1|29->4|30->5|32->7|32->7|32->7|34->9|36->11|36->11|36->11|37->12|37->12|37->12|38->13|40->15|40->15|41->16|41->16|42->17|42->17|44->19|49->24|51->26|55->30|55->30|55->30|58->33|60->35|60->35|60->35|61->36|61->36|61->36|62->37|63->38|65->40
                  -- GENERATED --
              */
          