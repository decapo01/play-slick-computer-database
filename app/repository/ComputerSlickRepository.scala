package repository

import javax.inject.{Inject, Singleton}
import models.Computer
import play.api.db.slick.DatabaseConfigProvider
import slick.ast.BaseTypedType

import scala.concurrent.ExecutionContext


@Singleton()
class ComputerSlickRepository @Inject()(override protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) extends BaseRepository[Computer,Long,ComputerCriteria] with ComputerRepository with ComputerComponent {

  import profile.api._

  val pkType: BaseTypedType[Long] = implicitly[BaseTypedType[Long]]

  type TableType = Computers

  val tableQuery = TableQuery[Computers]

  override def findByCriteriaQuery(criteria: ComputerCriteria): Query[Computers, Computer, Seq] = {

    tableQuery
      .filter { computer =>
        Seq(
          criteria.id.map(computer.id === _),
          criteria.filter.map(computer.name.toLowerCase like _.toLowerCase)
        )
        .flatten.reduceLeftOption(_ && _).getOrElse(true: LiteralColumn[Boolean])
      }
  }

}

