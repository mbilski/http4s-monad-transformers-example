package pl.immutables.monads.services

import pl.immutables.monads.models._
import scalaz.concurrent.Task

import scalaz._
import scalaz.Scalaz._

object UserService {
  val users = Seq(User("test", "test@immutables.pl", "abcde123", List("1")))

  def authenticate(token: String): Task[Error \/ User] = {
    Task.now(users.find(_.token == token)).map {
      case Some(user) => user.right[Error]
      case _ => InvalidToken.left[User]
    }
  }
}
