package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.streams.ActorFlow
import akka.actor.ActorSystem
import akka.stream.Materializer

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */

 // Example to continue https://www.playframework.com/documentation/2.7.x/ScalaWebSockets

@Singleton
class HomeController @Inject()(cc: ControllerComponents)(implicit system: ActorSystem, mat: Materializer) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def socket = WebSocket.accept[JsValue, JsValue] { request =>
    print("HOLAAAA")
    ActorFlow.actorRef { out =>
      MyWebSocketActor.props(out)
    }
  }
}
