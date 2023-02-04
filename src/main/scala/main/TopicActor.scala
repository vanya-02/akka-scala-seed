package main

import akka.actor._
import scala.collection.mutable.ListBuffer


case class SubscribeToTopic(topic: String, ref: ActorRef)
case class UnsubscribeFromTopic(topic: String, ref: ActorRef)
case class Broadcast(topic: String, body: String)

class TopicBroadcaster extends Actor {
  // create mutable list
  var subscribers = ListBuffer[ActorRef]()  

  def receive = {
    case message: String =>
      println(s"$self received String: $message")


    case SubscribeToTopic(topic: String, ref: ActorRef) =>
        subscribers += ref
        ref ! Topic(topic)
    
    case UnsubscribeFromTopic(topic: String, ref: ActorRef) =>
        subscribers -= ref
        ref ! UnTopic(topic)


    case Broadcast(topic: String, body: String) =>
      for (sub <- subscribers) {
        sub ! Relay(topic, body)
      }
    
      
    }

    
}