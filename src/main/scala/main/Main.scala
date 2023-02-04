import akka.actor._



import main.MessageBroker
import main.Publish
import main.Subscribe
import main.Unsubscribe

import main.TopicBroadcaster
import main.SubscribeToTopic
import main.UnsubscribeFromTopic
import main.Broadcast



object Main extends App {
  
  // actor supervisor
  val system = ActorSystem("HelloSystem")
  // spawn actor:
  val helloActor = system.actorOf(Props[MessageBroker], name = "helloactor")
  val creator = system.actorOf(Props[MessageBroker], name = "creator")

  val actor1 = system.actorOf(Props[MessageBroker], name = "actor1")
  val actor2 = system.actorOf(Props[MessageBroker], name = "actor2")
  val actor3 = system.actorOf(Props[MessageBroker], name = "actor3")

  val topicBroadcaster = system.actorOf(Props[TopicBroadcaster], name = "topicbroadcaster")

  creator ! Subscribe(actor1)
  creator ! Subscribe(actor2)
  creator ! Subscribe(actor3)
  creator ! Publish("hello actors 1 2 3!")

  creator ! Unsubscribe(actor1)
  creator ! Publish("hello actors 2 3!")

  topicBroadcaster ! SubscribeToTopic("news", actor1)
  topicBroadcaster ! Broadcast("news", "incendiu la UTM")
  topicBroadcaster ! UnsubscribeFromTopic("news", actor1)
  topicBroadcaster ! Broadcast("news", "hello proTV")


  
}