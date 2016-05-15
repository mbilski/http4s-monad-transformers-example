package pl.immutables.monads

import org.http4s.Response

import scalaz.EitherT
import scalaz.Liskov._
import scalaz.concurrent.Task

package object endpoints {
  type Failure = Task[Response]
  type Result[A] = EitherT[Task, Failure, A]

  implicit def toResponse[A](result: Result[A])(implicit ev: A <~< Failure): Task[Response] =
    result.run.map(_.merge).run
}
