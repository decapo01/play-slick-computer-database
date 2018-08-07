package bootstrap

import com.google.inject.AbstractModule
import repository.{CompanyRepository, CompanySlickRepository, ComputerRepository, ComputerSlickRepository}

class DiModule extends AbstractModule{

  override protected def configure(): Unit = {
    bind(classOf[ComputerRepository]).to(classOf[ComputerSlickRepository])
    bind(classOf[CompanyRepository]).to(classOf[CompanySlickRepository])
  }

}
