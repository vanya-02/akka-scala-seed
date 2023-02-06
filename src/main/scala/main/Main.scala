import akka.actor._

import scala.concurrent._
import scala.concurrent.duration._

import main.MessageBroker
import main.Publish
import main.Subscribe
import main.Unsubscribe

import main.TopicBroadcaster
import main.SubscribeToTopic
import main.UnsubscribeFromTopic
import main.Broadcast

import scala.util.Random



class Task (actorsystem: ActorSystem, broadcaster: ActorRef) extends Thread{
  override def run() {
    implicit val dispatcher: ExecutionContextExecutor = actorsystem.dispatcher
    while (true) {
      

      val str = Random.alphanumeric.take(3).mkString
      val tme = Random.nextInt(10)

      actorsystem.scheduler.scheduleOnce(1.seconds, broadcaster, Broadcast("news", str) )
      Thread.sleep(tme *1000)

      
      


    }
  }
}



object Main extends App {
  
  // actor supervisor
  val system = ActorSystem("HelloSystem")
  // spawn actor:
  val helloActor = system.actorOf(Props[MessageBroker], name = "helloactor")
  val creator = system.actorOf(Props[MessageBroker], name = "creator")

  val actor1 = system.actorOf(Props[MessageBroker], name = "actor1")
  val actor2 = system.actorOf(Props[MessageBroker], name = "actor2")
  val actor3 = system.actorOf(Props[MessageBroker], name = "actor3")
  val actor4 = system.actorOf(Props[MessageBroker], name = "actor4")

  val topicBroadcaster = system.actorOf(Props[TopicBroadcaster], name = "topicbroadcaster")

  creator ! Subscribe(actor1)
  creator ! Subscribe(actor2)
  creator ! Subscribe(actor3)
  creator ! Subscribe(actor4)
  creator ! Publish("hello actors 1 2 3 4!")

  creator ! Unsubscribe(actor1)
  creator ! Publish("hello actors 2 3 4!")

  topicBroadcaster ! SubscribeToTopic("news", actor1)
  topicBroadcaster ! SubscribeToTopic("news", actor2)
  topicBroadcaster ! Broadcast("news", "incendiu la UTM")
  topicBroadcaster ! UnsubscribeFromTopic("news", actor2)
  topicBroadcaster ! Broadcast("news", "hello proTV")


  var d1 = new Thread(new Task(system, topicBroadcaster))
  d1.run()
  
  
}