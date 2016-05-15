package pl.immutables.monads.endpoints

import io.circe.syntax._
import io.circe.generic.auto._

import org.http4s._
import org.http4s.circe._
import org.http4s.dsl._
import org.http4s.server.blaze.BlazeBuilder
import org.http4s.util.CaseInsensitiveString

import scalaz._
import scalaz.Scalaz._

import pl.immutables.monads.models._
import pl.immutables.monads.services._

import scalaz.concurrent.Task

object ProfileEndpoint extends App {
  lazy val tokenHeader = CaseInsensitiveString("token")

  lazy val service = HttpService {
    case req @ GET -> Root / "profile" => for {
      token <- req.headers.get(tokenHeader) |> 
        Result.ofOption(BadRequest("missing token"))

      user <- UserService.authenticate(token.value) |> 
        Result.ofTEither(e => Forbidden("invalid token"))

      devices <- Task.gatherUnordered(
          user.devices.map(id => DeviceService.getById(id))
        ) |> Result.ofTask
    } yield Ok(UserWithDevices(user, devices.flatten).asJson)
  }

  BlazeBuilder
    .mountService(service)
    .run.awaitShutdown()
}
