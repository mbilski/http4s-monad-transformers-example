package pl.immutables.monads.models

case class User(username: String, email: String, token: String, devices: List[String])
case class UserWithDevices(user: User, devices: List[Device])