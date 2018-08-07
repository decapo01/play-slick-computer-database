package repository

import models.Page
import play.api.db.slick.HasDatabaseConfigProvider
import slick.ast.BaseTypedType
import slick.jdbc.JdbcProfile
import slick.lifted.{AbstractTable, Rep}
import slick.relational.RelationalProfile

import scala.concurrent.{ExecutionContext, Future}

trait Entity[T,ID]{

  val id: Option[ID]

  def withId(id: ID): T
}

trait Keyed[ID]{
  def id: Rep[ID]
}

trait Criteria {

  val limit: Int

  val page: Int
}

trait Repository[T,ID,CRITERIA <: Criteria] {

  def insert(item: T): Future[Int]

  def save(item: T): Future[Int]

  def delete(item: ID): Future[Int]

  def findById(id: ID): Future[Option[T]]

  def findByCriteria(criteria: CRITERIA): Future[Option[T]]

  def findAll: Future[Seq[T]]

  def findAllByCriteria(criteria: CRITERIA): Future[Seq[T]]

  def findPage(criteria: CRITERIA): Future[Page[T]]

  def count(criteria: CRITERIA): Future[Int]

}


abstract class BaseRepository[T <: Entity[T,ID],ID,CRITERIA <: Criteria](implicit ec: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  type TableType <: Keyed[ID] with RelationalProfile#Table[T]

  def pkType: BaseTypedType[ID]

  implicit lazy val _pkType: BaseTypedType[ID] = pkType

  def tableQuery: TableQuery[TableType]

  def findAll(): Future[Seq[T]] = db.run(tableQuery.result)

  def findById(id: ID): Future[Option[T]] = db.run(tableQuery.filter(_.id === id).result.headOption)

  def insert(item: T): Future[Int] = db.run(tableQuery += item)

  def save(item: T): Future[Int] = db.run(tableQuery.insertOrUpdate(item))

  def delete(id: ID): Future[Int] = db.run(tableQuery.filter(_.id === id).delete)

  def findByCriteriaQuery(criteria: CRITERIA): Query[TableType, T, Seq]

  def findByCriteria(criteria: CRITERIA): Future[Option[T]] = db.run(findByCriteriaQuery(criteria).result.headOption)

  def findAllByCriteria(criteria: CRITERIA): Future[Seq[T]] = db.run(findByCriteriaQuery(criteria).result)

  def findPage(criteria: CRITERIA): Future[Page[T]] = {

    val offset = criteria.limit * criteria.page

    for{
      items <- db.run(findByCriteriaQuery(criteria).drop(offset).take(criteria.limit).result)
      total <- count(criteria)
    }
    yield Page(items,criteria.page,offset,total)
  }

  def count(criteria: CRITERIA): Future[Int] = db.run(findByCriteriaQuery(criteria).length.result)

}