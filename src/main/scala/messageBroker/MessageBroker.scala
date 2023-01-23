package messageBroker

import akka.actor.Actor
import akka.routing.{ActorRefRoutee, BroadcastRoutingLogic, Router}
import messageBroker.MessageBroker.{Publish, Subscribe}

object MessageBroker {
  case class Subscribe()

  case class Publish(message: String)
}

class MessageBroker extends Actor {

  var subscribers: Router = Router(BroadcastRoutingLogic(), Vector[ActorRefRoutee]())

  override def receive: Receive = {
    case Subscribe =>
      subscribers = subscribers.addRoutee(sender())
      println( sender().path.name + " subscribed!" )

    case Publish(message) =>
      subscribers.route(message, self)
  }

}