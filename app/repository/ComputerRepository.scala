package repository

import java.util.Date

import javax.inject.{Inject, Singleton}
import models.{Company, Computer}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.ast.BaseTypedType
import slick.jdbc.JdbcProfile

import scala.concurrent.ExecutionContext


case class ComputerCriteria(id: Option[Long] = None,
                            orderBy: Int = 1,
                            filter: Option[String] = None,
                            limit: Int = 10,
                            page:  Int = 1) extends Criteria

trait ComputerRepository extends Repository[Computer,Long,ComputerCriteria]

trait ComputerComponent { self: HasDatabaseConfigProvider[JdbcProfile] =>
  import profile.api._

  class Computers(tag: Tag) extends Table[Computer](tag, "COMPUTER") with Keyed[Long] {

    implicit val dateColumnType = MappedColumnType.base[Date, Long](d => d.getTime, d => new Date(d))

    def id           = column[Long]("ID", O.PrimaryKey, O.AutoInc)
    def name         = column[String]("NAME")
    def introduced   = column[Option[Date]]("INTRODUCED")
    def discontinued = column[Option[Date]]("DISCONTINUED")
    def companyId    = column[Option[Long]]("COMPANY_ID")

    def * = (id.?, name, introduced, discontinued, companyId) <> (Computer.tupled, Computer.unapply _)
  }
}
