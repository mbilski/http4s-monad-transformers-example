package pl.immutables.monads.services

import pl.immutables.monads.models.Device

import scalaz.concurrent.Task

object DeviceService {
  val devices = Seq(Device("1", "nexus"))

  def getById(id: String): Task[Option[Device]] = {
    Task.now(devices.find(_.id == id))
  }
}
