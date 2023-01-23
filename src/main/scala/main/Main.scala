package main

import akka.actor.{ActorRef, ActorSystem, Props}
import messageBroker.MessageBroker
import publisher.Publisher
import subscriber.Subscriber

object Main {

  implicit val system: ActorSystem = ActorSystem("system")

  val messageBroker: ActorRef = system.actorOf( Props( new MessageBroker() ), "messageBroker" )

  val rand = new scala.util.Random

  def main(args: Array[String]): Unit = {

    for ( i <- 0 to (rand.nextInt(10) +1) )
      system.actorOf( Props( new Publisher() ), "publisher" + i )

    for (i <- 0 to (rand.nextInt(10) + 1))
      system.actorOf(Props(new Subscriber()), "subscriber" + i)

  }

}