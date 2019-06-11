package testrest

import io.gatling.core.Predef._     // required for Gatling core structure DSL
// import io.gatling.jdbc.Predef._     // can be omitted if you don't use jdbcFeeder
import io.gatling.http.Predef._     // required for Gatling HTTP DSL

import scala.concurrent.duration._  // used for specifying duration unit, eg "5 second"

class BasicSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8081") // Here is the root for all relative URLs

  val headers = Map("Content-Type" -> "application/json")
  val headers_auth = Map("Content-Type" -> "application/json", "Authorization" -> "Bearer ${token}")

  object Auth {
    val execute = exec(http("auth")
      .post("/auth/signin")
      .headers(headers)
      .body(StringBody("{\"username\":\"user\",\"password\":\"userpass\"}"))
      .check(jsonPath("$.token").saveAs("token"))
    )
  }

  object PostPerson {
    val execute = exec(http("post_person")
      .post("/person")
      .headers(headers_auth)
      .body(StringBody("{\"personId\":0,\"name\":\"Lanny\",\"sex\":\"m\",\"birth\":\"1994-03-24\",\"countryId\":1,\"cityId\":1,\"status\":\"I am a God\",\"approveEmail\":\"2019-03-24 18:08:56+0300\",\"approvePhone\":\"2019-03-24 17:55:56+0300\",\"createDate\":null,\"premiumStart\":null,\"premiumFinish\":null,\"about\":\"I am owner Asgard!\",\"interests\":[\"sex\",\"box\",\"jazz\"]}"))
      .check(jsonPath("$.personId").saveAs("personId"))
    )
  }

  object GetPerson {
    val execute = exec(http("get_person")
      .get("/person/${personId}")
      .headers(headers_auth)
    )
  }

  object PremiumSet {
    val execute = exec(http("person_premium_set")
      .post("/person/premium/set/${personId}")
      .headers(headers_auth)
    )
  }

  object PremiumUnset {
    val execute = exec(http("person_premium_unset")
      .post("/person/premium/unset/${personId}")
      .headers(headers_auth)
    )
  }

  object PersonDelete {
    val execute = exec(http("person_delete")
      .post("/person/delete/${personId}")
      .headers(headers_auth)
    )
  }

  val scn = scenario("Base scenario") // A scenario is a chain of requests and pauses
      .exec(Auth.execute)
      .exec(PostPerson.execute)
      .exec(GetPerson.execute)
      .exec(PremiumSet.execute)
      .exec(PremiumUnset.execute)
//      .exec(PersonDelete.execute)

  setUp(
    scn.inject(
      constantConcurrentUsers(10) during (10 seconds),
      rampConcurrentUsers(10) to (100) during (60 seconds)
    )
      .protocols(httpProtocol)
  )
//  setUp(scn.inject(
//    incrementUsersPerSec(5) // Double
//      .times(5)
//      .eachLevelLasting(10 seconds)
//      .separatedByRampsLasting(10 seconds)
//      .startingFrom(10))
//    .protocols(httpProtocol))

}

//https://gatling.io/docs/current/http/http_check/
//  setUp(scn.inject(atOnceUsers(1)).protocols(httpProtocol))
