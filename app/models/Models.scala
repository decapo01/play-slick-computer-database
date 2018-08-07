package models

import java.util.Date

import repository.Entity

case class Page[A](items: Seq[A], page: Int, offset: Long, total: Long) {
  lazy val prev = Option(page - 1).filter(_ >= 0)
  lazy val next = Option(page + 1).filter(_ => (offset + items.size) < total)
}

case class Company(id: Option[Long], name: String) extends Entity[Company,Long]{
  override def withId(id: Long): Company = this.copy(id = Some(id))
}

case class Computer(id: Option[Long] = None, name: String, introduced: Option[Date] = None, discontinued: Option[Date] = None, companyId: Option[Long] = None) extends Entity[Computer,Long]{

  override def withId(id: Long): Computer = this.copy(id = Some(id))
}
