package publisher

import messageBroker.MessageBroker.Publish
import akka.actor.Actor
import main.Main.{messageBroker, rand, system}

import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.duration.DurationInt
import scala.language.{existentials, postfixOps}
import scala.util.Random

class Publisher extends Actor {

  implicit val dispatcher: ExecutionContextExecutor = main.Main.system.dispatcher

  system.scheduler.scheduleAtFixedRate( ( rand.nextInt(10) + 1 ) seconds, 20 seconds) ( () => {
    val message: String = Random.alphanumeric.take(10).mkString
    messageBroker ! Publish(message)
    println( context.self.path.name + " has send message: " + message )
  } )

  override def receive: Receive = {
    case _ =>
  }
}