package subscriber

import akka.actor.Actor
import messageBroker.MessageBroker.Subscribe
import main.Main.messageBroker

class Subscriber extends Actor {

  messageBroker ! Subscribe

  override def receive: Receive = {
    case message: String =>
      println( context.self.path.name + " received message: " + message )
  }
}