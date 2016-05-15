package pl.immutables.monads.endpoints

import scalaz._
import scalaz.Scalaz._
import scalaz.concurrent.Task

object Result {
  def ofTask[A](task: Task[A]): Result[A] = EitherT(task.map(_.right))

  def ofOption[A](failure: => Failure)(oa: Option[A]): Result[A] =
    EitherT(Task.now(oa.toRightDisjunction(failure)))

  def ofTOption[A](failure: => Failure)(toa: Task[Option[A]]): Result[A] =
    EitherT(toa.map(_.toRightDisjunction(failure)))

  def ofTEither[A, E](failure: E => Failure)(tea: Task[E \/ A]): Result[A] =
    EitherT(tea.map(_.leftMap(failure)))
}
