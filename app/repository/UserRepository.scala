package repository


import javax.inject.{Inject, Singleton}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.ast.BaseTypedType
import slick.jdbc.JdbcProfile
import slick.lifted
import slick.lifted.TableQuery

import scala.concurrent.{ExecutionContext, Future}


case class User(id: Option[Long],email: String, password: String) extends Entity[User,Long]{
  override def withId(id: Long): User = this.copy(id = Some(id))
}

trait Role
case object User extends Role
case object Admin extends Role

object Roles {

  val user = "user"

  val admin = "admin"
}

case class UserCriteria(
  emailEq: Option[String] = None,
  limit: Int = 10,
  page: Int = 1

) extends Criteria

case class UserRole(userId: Long, role: String)

trait UserRepository extends Repository[User,Long,UserCriteria]{

  def insert(user: User,roles: Seq[String]): Future[Long]
}

trait UserComponent { self: HasDatabaseConfigProvider[JdbcProfile] =>

  import profile.api._

  class Users(tag: Tag) extends Table[User](tag,"users") with Keyed[Long]{

    def id       = column[Long]("id",O.PrimaryKey,O.AutoInc)
    def email    = column[String]("email",O.Unique)
    def password = column[String]("password")

    def * = (id.?,email,password)<>((User.apply _).tupled,User.unapply _)
  }
}

trait UnregisteredUserComponent { self: HasDatabaseConfigProvider[JdbcProfile] =>

  import profile.api._

  class UnregisteredUsers(tag: Tag) extends Table[User](tag,"unregistered_users") with Keyed[Long]{

    def id       = column[Long]("id",O.PrimaryKey,O.AutoInc)
    def email    = column[String]("email",O.Unique)
    def password = column[String]("password")

    def * = (id.?,email,password)<>((User.apply _).tupled,User.unapply _)
  }
}

trait UserRoleComponent {self: HasDatabaseConfigProvider[JdbcProfile] =>

  import profile.api._

  class UserRoles(tag: Tag) extends Table[UserRole](tag,"user_roles"){

    def userId = column[Long]("user_id")
    def role   = column[String]("role")

    def * = (userId,role).mapTo[UserRole]
  }
}


trait UserRoleRepository {

  def findByUser(userId: Long): Future[Seq[UserRole]]
}

@Singleton()
class UserRoleSlickRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends UserRoleRepository with UserRoleComponent with HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  val userRoles = lifted.TableQuery[UserRoles]

  override def findByUser(userId: Long): Future[Seq[UserRole]] = db.run(userRoles.filter(_.userId === userId).result)
}


@Singleton()
class UserSlickRepository @Inject()(override protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) extends BaseRepository[User,Long,UserCriteria] with UserRepository with UserComponent with UserRoleComponent {

  import profile.api._

  import slick.lifted.TableQuery

  override type TableType = Users

  override def pkType = implicitly[BaseTypedType[Long]]

  override def tableQuery: TableQuery[Users] = TableQuery[Users]

  val userRoles = TableQuery[UserRoles]

  def insert(user: User, roles: Seq[String]): Future[Long] = {
    db.run{
      (for{
        i   <- tableQuery.returning(tableQuery.map(_.id)) += user
        urs =  roles.map(UserRole(i,_))
        _   <- userRoles  ++= urs
      } yield i).transactionally
    }
  }

  def addRole(userRole: UserRole): Future[Int] = {
    db.run(userRoles += userRole)
  }

  override def findByCriteriaQuery(criteria: UserCriteria): Query[Users, User, Seq] = {

    tableQuery.filter(user =>
      Seq(
        criteria.emailEq.map(user.email.toLowerCase === _.toLowerCase)
      ).flatten.reduceLeftOption(_ && _).getOrElse(LiteralColumn(true))
    )
  }
}


case class RoleRow(name: String)

trait RoleRepository {

  def findByName(name: String): Future[Option[RoleRow]]

  def findAll: Future[Seq[RoleRow]]
}

trait RoleComponent { self: HasDatabaseConfigProvider[JdbcProfile] =>

  import profile.api._

  class RoleTable(tag: Tag) extends Table[RoleRow](tag,"roles"){

    def name = column[String]("name",O.PrimaryKey,O.Unique)

    def * = (name).mapTo[RoleRow]
  }
}

class RoleSlickRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) extends RoleComponent with RoleRepository with HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  val roles = lifted.TableQuery[RoleTable]

  override def findByName(name: String): Future[Option[RoleRow]] =
    db.run(roles.filter(_.name === name).result.headOption)

  override def findAll: Future[Seq[RoleRow]] =
    db.run(roles.result)

}

trait UnregisteredUserRepository extends Repository[User,Long,UserCriteria]

class UnregisteredUserSlickRepository @Inject()(override protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) extends BaseRepository[User,Long,UserCriteria] with UnregisteredUserRepository with UnregisteredUserComponent {

  import profile.api._

  override type TableType = UnregisteredUsers

  override def pkType = implicitly[BaseTypedType[Long]]

  override def tableQuery = lifted.TableQuery[UnregisteredUsers]

  override def findByCriteriaQuery(criteria: UserCriteria): Query[UnregisteredUsers, User, Seq] = {
    tableQuery
      .filter(user =>
        Seq(
          criteria.emailEq.map(user.email.toLowerCase === _.toLowerCase)
        ).flatten.reduceLeftOption(_ && _).getOrElse(LiteralColumn(true))
      )
  }
}