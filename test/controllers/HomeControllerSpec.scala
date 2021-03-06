package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.mvc.{DefaultMessagesControllerComponents, MessagesControllerComponents}
import play.api.test._
import play.api.test.Helpers._

import play.filters.csrf._
import play.api.test.CSRFTokenHelper._
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class HomeControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {
  "HomeController GET" should {
    

	"render the index page from a new instance of controller" in {
      val controller = new HomeController(app.injector.instanceOf[MessagesControllerComponents])
      val home = controller.index().apply(FakeRequest(GET, "/").withCSRFToken)

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Welcome to Play")
    }


    "render the index page from the application" in {
      val controller = inject[HomeController]
      val home = controller.index().apply( FakeRequest(GET, "/").withCSRFToken)

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Welcome to Play")
    }

    "render the index page from the router" in {
      val request = FakeRequest(GET, "/")
      val home = route(app, request).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Welcome to Play")
    }




  }
}
