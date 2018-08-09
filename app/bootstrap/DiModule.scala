package bootstrap

import com.google.inject.AbstractModule
import repository._

class DiModule extends AbstractModule{

  override protected def configure(): Unit = {
    bind(classOf[ComputerRepository]).to(classOf[ComputerSlickRepository])
    bind(classOf[CompanyRepository]).to(classOf[CompanySlickRepository])
    bind(classOf[UserRepository]).to(classOf[UserSlickRepository])
    bind(classOf[UnregisteredUserRepository]).to(classOf[UnregisteredUserSlickRepository])
    bind(classOf[UserRoleRepository]).to(classOf[UserRoleSlickRepository])
  }

}
