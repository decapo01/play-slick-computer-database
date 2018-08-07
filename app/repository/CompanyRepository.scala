package repository

import javax.inject.Inject
import javax.inject.Singleton
import models.Company
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.ast.BaseTypedType
import slick.jdbc.JdbcProfile

import scala.concurrent.ExecutionContext


case class CompanyCriteria(
  idsIn: Option[Seq[Long]] = None,
  limit: Int = 10,
  page: Int = 1
)  extends Criteria

trait CompanyRepository extends Repository[Company,Long,CompanyCriteria]

trait CompanyComponents { self: HasDatabaseConfigProvider[JdbcProfile] =>

  import profile.api._

  class Companies(tag: Tag) extends Table[Company](tag, "COMPANY") with Keyed[Long] {
    def id          = column[Long]("ID", O.PrimaryKey, O.AutoInc)
    def name       = column[String]("NAME")
    def * = (id.?, name) <> (Company.tupled, Company.unapply _)
  }
}

@Singleton()
class CompanySlickRepository @Inject()(override protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) extends BaseRepository[Company,Long,CompanyCriteria] with CompanyRepository with CompanyComponents{

  import profile.api._

  override type TableType = Companies

  override def pkType = implicitly[BaseTypedType[Long]]

  override def tableQuery: TableQuery[Companies] = TableQuery[Companies]

  override def findByCriteriaQuery(criteria: CompanyCriteria): Query[Companies, Company, Seq] = {

    tableQuery
      .filter(company => criteria.idsIn.map(idsIn => company.id.inSet(idsIn)).getOrElse(LiteralColumn(true)))
  }
}