package pl.immutables.monads.endpoints

import org.http4s._
import org.http4s.dsl._
import org.scalatest._

class ProfileEndpointSpec extends FlatSpec with Matchers with Http4s {
  it should "return BadRequest when token is missing" in {
    val response = serve(Request(GET, Uri(path = "/profile")))
    response.status shouldBe (BadRequest)
  }

  it should "return Forbidden when token is invalid" in {
    val response = serve(buildRequest("invalid"))
    response.status shouldBe (Forbidden)
  }

  it should "return Ok for valid token" in {
    val response = serve(buildRequest("abcde123"))
    response.status shouldBe (Ok)
  }

  def buildRequest(token: String) = Request(
    GET, Uri(path = "/profile"),
    headers = Headers(Header("token", token))
  )

  def serve(req: Request): Response =
    ProfileEndpoint.service.run(req).run
}
