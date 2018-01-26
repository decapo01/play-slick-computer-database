
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

object list extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Page[scala.Tuple2[Computer, Company]],Int,String,play.api.mvc.RequestHeader,play.api.mvc.Flash,Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(currentPage: Page[(Computer, Company)], currentOrderBy: Int, currentFilter: String)(implicit request: play.api.mvc.RequestHeader, flash: play.api.mvc.Flash, messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {

def /*16.2*/header/*16.8*/(orderBy: Int, title: String):play.twirl.api.HtmlFormat.Appendable = {_display_(

Seq[Any](format.raw/*16.41*/("""
    """),format.raw/*17.5*/("""<th class="col"""),_display_(/*17.20*/orderBy),format.raw/*17.27*/(""" """),format.raw/*17.28*/("""header """),_display_(/*17.36*/if(scala.math.abs(currentOrderBy) == orderBy)/*17.81*/ {_display_(Seq[Any](format.raw/*17.83*/(""" """),_display_(/*17.85*/{if(currentOrderBy < 0) "headerSortDown" else "headerSortUp"}),format.raw/*17.146*/(""" """)))}),format.raw/*17.148*/("""">
        <a href=""""),_display_(/*18.19*/link(0, Some(orderBy))),format.raw/*18.41*/("""">"""),_display_(/*18.44*/title),format.raw/*18.49*/("""</a>
    </th>
""")))};def /*6.2*/link/*6.6*/(newPage: Int, newOrderBy: Option[Int] = None) = {{
    routes.Application.list(newPage, newOrderBy.map { orderBy =>
        if(orderBy == scala.math.abs(currentOrderBy)) -currentOrderBy else orderBy
    }.getOrElse(currentOrderBy), currentFilter)
    
}};
Seq[Any](format.raw/*1.179*/("""

"""),format.raw/*5.42*/("""
"""),format.raw/*11.2*/("""

"""),format.raw/*15.37*/("""
"""),format.raw/*20.2*/("""

"""),_display_(/*22.2*/main/*22.6*/ {_display_(Seq[Any](format.raw/*22.8*/("""
    
    """),format.raw/*24.5*/("""<h1>"""),_display_(/*24.10*/Messages("computers.list.title", currentPage.total)),format.raw/*24.61*/("""</h1>

    """),_display_(/*26.6*/flash/*26.11*/.get("success").map/*26.30*/ { message =>_display_(Seq[Any](format.raw/*26.43*/("""
        """),format.raw/*27.9*/("""<div class="alert-message warning">
            <strong>Done!</strong> """),_display_(/*28.37*/message),format.raw/*28.44*/("""
        """),format.raw/*29.9*/("""</div>
    """)))}),format.raw/*30.6*/("""

    """),format.raw/*32.5*/("""<div id="actions">
        
        """),_display_(/*34.10*/helper/*34.16*/.form(action=routes.Application.list())/*34.55*/ {_display_(Seq[Any](format.raw/*34.57*/("""
            """),format.raw/*35.13*/("""<input type="search" id="searchbox" name="f" value=""""),_display_(/*35.66*/currentFilter),format.raw/*35.79*/("""" placeholder="Filter by computer name..."/>
            <input type="submit" id="searchsubmit" value="Filter by name" class="btn primary"/>
        """)))}),format.raw/*37.10*/("""
        
        """),format.raw/*39.9*/("""<a class="btn success" id="add" href=""""),_display_(/*39.48*/routes/*39.54*/.Application.create()),format.raw/*39.75*/("""">Add a new computer</a>
        
    </div>
    
    """),_display_(/*43.6*/Option(currentPage.items)/*43.31*/.filterNot(_.isEmpty).map/*43.56*/ { computers =>_display_(Seq[Any](format.raw/*43.71*/("""
        
        """),format.raw/*45.9*/("""<table class="computers zebra-striped">
            <thead>
                <tr>
                    """),_display_(/*48.22*/header(2, "Computer name")),format.raw/*48.48*/("""
                    """),_display_(/*49.22*/header(3, "Introduced")),format.raw/*49.45*/("""
                    """),_display_(/*50.22*/header(4, "Discontinued")),format.raw/*50.47*/("""
                    """),_display_(/*51.22*/header(5, "Company")),format.raw/*51.42*/("""
                """),format.raw/*52.17*/("""</tr>
            </thead>
            <tbody>

                """),_display_(/*56.18*/computers/*56.27*/.map/*56.31*/ {/*57.21*/case (computer, company) =>/*57.48*/ {_display_(Seq[Any](format.raw/*57.50*/("""
                        """),format.raw/*58.25*/("""<tr>
                            <td><a href=""""),_display_(/*59.43*/routes/*59.49*/.Application.edit(computer.id.get)),format.raw/*59.83*/("""">"""),_display_(/*59.86*/computer/*59.94*/.name),format.raw/*59.99*/("""</a></td>
                            <td>
                                """),_display_(/*61.34*/computer/*61.42*/.introduced.map(_.format("dd MMM yyyy")).getOrElse/*61.92*/ {_display_(Seq[Any](format.raw/*61.94*/(""" """),format.raw/*61.95*/("""<em>-</em> """)))}),format.raw/*61.107*/("""
                            """),format.raw/*62.29*/("""</td>
                            <td>
                                """),_display_(/*64.34*/computer/*64.42*/.discontinued.map(_.format("dd MMM yyyy")).getOrElse/*64.94*/ {_display_(Seq[Any](format.raw/*64.96*/(""" """),format.raw/*64.97*/("""<em>-</em> """)))}),format.raw/*64.109*/("""
                            """),format.raw/*65.29*/("""</td>
                            <td>
                                """),_display_(/*67.34*/company/*67.41*/.name),format.raw/*67.46*/("""
                            """),format.raw/*68.29*/("""</td>
                        </tr>
                    """)))}}),format.raw/*71.18*/("""

            """),format.raw/*73.13*/("""</tbody>
        </table>

        <div id="pagination" class="pagination">
            <ul>
                """),_display_(/*78.18*/currentPage/*78.29*/.prev.map/*78.38*/ { page =>_display_(Seq[Any](format.raw/*78.48*/("""
                    """),format.raw/*79.21*/("""<li class="prev">
                        <a href=""""),_display_(/*80.35*/link(page)),format.raw/*80.45*/("""">&larr; Previous</a>
                    </li> 
                """)))}/*82.18*/.getOrElse/*82.28*/ {_display_(Seq[Any](format.raw/*82.30*/("""
                    """),format.raw/*83.21*/("""<li class="prev disabled">
                        <a>&larr; Previous</a>
                    </li>
                """)))}),format.raw/*86.18*/("""
                """),format.raw/*87.17*/("""<li class="current">
                    <a>Displaying """),_display_(/*88.36*/(currentPage.offset + 1)),format.raw/*88.60*/(""" """),format.raw/*88.61*/("""to """),_display_(/*88.65*/(currentPage.offset + computers.size)),format.raw/*88.102*/(""" """),format.raw/*88.103*/("""of """),_display_(/*88.107*/currentPage/*88.118*/.total),format.raw/*88.124*/("""</a>
                </li>
                """),_display_(/*90.18*/currentPage/*90.29*/.next.map/*90.38*/ { page =>_display_(Seq[Any](format.raw/*90.48*/("""
                    """),format.raw/*91.21*/("""<li class="next">
                        <a href=""""),_display_(/*92.35*/link(page)),format.raw/*92.45*/("""">Next &rarr;</a>
                    </li> 
                """)))}/*94.18*/.getOrElse/*94.28*/ {_display_(Seq[Any](format.raw/*94.30*/("""
                    """),format.raw/*95.21*/("""<li class="next disabled">
                        <a>Next &rarr;</a>
                    </li>
                """)))}),format.raw/*98.18*/("""
            """),format.raw/*99.13*/("""</ul>
        </div>
        
    """)))}/*102.6*/.getOrElse/*102.16*/ {_display_(Seq[Any](format.raw/*102.18*/("""
        
        """),format.raw/*104.9*/("""<div class="well">
            <em>Nothing to display</em>
        </div>
        
    """)))}),format.raw/*108.6*/("""

        
""")))}),format.raw/*111.2*/("""

            """))
      }
    }
  }

  def render(currentPage:Page[scala.Tuple2[Computer, Company]],currentOrderBy:Int,currentFilter:String,request:play.api.mvc.RequestHeader,flash:play.api.mvc.Flash,messages:Messages): play.twirl.api.HtmlFormat.Appendable = apply(currentPage,currentOrderBy,currentFilter)(request,flash,messages)

  def f:((Page[scala.Tuple2[Computer, Company]],Int,String) => (play.api.mvc.RequestHeader,play.api.mvc.Flash,Messages) => play.twirl.api.HtmlFormat.Appendable) = (currentPage,currentOrderBy,currentFilter) => (request,flash,messages) => apply(currentPage,currentOrderBy,currentFilter)(request,flash,messages)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Thu Jan 25 21:33:18 CST 2018
                  SOURCE: /home/decapo/apps/computer-database/app/views/list.scala.html
                  HASH: 3a14a2924723d72d8d1cfec1754ea0456b837710
                  MATRIX: 825->1|1081->677|1095->683|1205->716|1237->721|1279->736|1307->743|1336->744|1371->752|1425->797|1465->799|1494->801|1577->862|1611->864|1659->885|1702->907|1732->910|1758->915|1796->307|1807->311|2092->178|2121->305|2149->565|2179->675|2207->931|2236->934|2248->938|2287->940|2324->950|2356->955|2428->1006|2466->1018|2480->1023|2508->1042|2559->1055|2595->1064|2694->1136|2722->1143|2758->1152|2800->1164|2833->1170|2897->1207|2912->1213|2960->1252|3000->1254|3041->1267|3121->1320|3155->1333|3336->1483|3381->1501|3447->1540|3462->1546|3504->1567|3585->1622|3619->1647|3653->1672|3706->1687|3751->1705|3880->1807|3927->1833|3976->1855|4020->1878|4069->1900|4115->1925|4164->1947|4205->1967|4250->1984|4342->2049|4360->2058|4373->2062|4384->2086|4420->2113|4460->2115|4513->2140|4587->2187|4602->2193|4657->2227|4687->2230|4704->2238|4730->2243|4833->2319|4850->2327|4909->2377|4949->2379|4978->2380|5022->2392|5079->2421|5178->2493|5195->2501|5256->2553|5296->2555|5325->2556|5369->2568|5426->2597|5525->2669|5541->2676|5567->2681|5624->2710|5713->2785|5755->2799|5892->2909|5912->2920|5930->2929|5978->2939|6027->2960|6106->3012|6137->3022|6222->3088|6241->3098|6281->3100|6330->3121|6478->3238|6523->3255|6606->3311|6651->3335|6680->3336|6711->3340|6770->3377|6800->3378|6832->3382|6853->3393|6881->3399|6952->3443|6972->3454|6990->3463|7038->3473|7087->3494|7166->3546|7197->3556|7278->3618|7297->3628|7337->3630|7386->3651|7530->3764|7571->3777|7625->3812|7645->3822|7686->3824|7732->3842|7851->3930|7894->3942
                  LINES: 21->1|25->16|25->16|27->16|28->17|28->17|28->17|28->17|28->17|28->17|28->17|28->17|28->17|28->17|29->18|29->18|29->18|29->18|31->6|31->6|37->1|39->5|40->11|42->15|43->20|45->22|45->22|45->22|47->24|47->24|47->24|49->26|49->26|49->26|49->26|50->27|51->28|51->28|52->29|53->30|55->32|57->34|57->34|57->34|57->34|58->35|58->35|58->35|60->37|62->39|62->39|62->39|62->39|66->43|66->43|66->43|66->43|68->45|71->48|71->48|72->49|72->49|73->50|73->50|74->51|74->51|75->52|79->56|79->56|79->56|79->57|79->57|79->57|80->58|81->59|81->59|81->59|81->59|81->59|81->59|83->61|83->61|83->61|83->61|83->61|83->61|84->62|86->64|86->64|86->64|86->64|86->64|86->64|87->65|89->67|89->67|89->67|90->68|92->71|94->73|99->78|99->78|99->78|99->78|100->79|101->80|101->80|103->82|103->82|103->82|104->83|107->86|108->87|109->88|109->88|109->88|109->88|109->88|109->88|109->88|109->88|109->88|111->90|111->90|111->90|111->90|112->91|113->92|113->92|115->94|115->94|115->94|116->95|119->98|120->99|123->102|123->102|123->102|125->104|129->108|132->111
                  -- GENERATED --
              */
          