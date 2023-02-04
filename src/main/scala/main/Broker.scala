package main

import akka.actor._
import scala.collection.mutable.ListBuffer


case class Subscribe(sender_adress: ActorRef)
case class Unsubscribe(sender_adress: ActorRef)
case class Publish(message: String)
case class Topic(topic: String)
case class UnTopic(topic: String)
case class Relay(topic: String, body: String)

// Task 3 DIY Subscribe and Broadcast w/ no Unsubscribe
class MessageBroker extends Actor {
  // create mutable list
  var subscribers = ListBuffer[ActorRef]()  
  var topics = ListBuffer[String]()

  // receive act
  def receive = {
    case message: String =>
      println(s"$self received String: $message")


    case Subscribe(sender_adress: ActorRef) =>
      println(s"$sender_adress has Subscribed to $self")
      subscribers += sender_adress

    
    case Unsubscribe(sender_adress: ActorRef) => 
      println(s"$sender_adress has Unsubscribed from $self")
      subscribers -= sender_adress
      

    case Publish(message) =>
      // echo message to subscibers
      for (sub <- subscribers) {
        sub ! message
      }

    
    case Topic(topic: String) => 
      println(s"$self has SubscribeToTopic $topic")
      topics += topic

    case UnTopic(topic: String) => 
      println(s"$self has UnsubscribeFromTopic $topic")
      topics -= topic
    

    case Relay(topic: String, body: String) =>
      if (topics.contains(topic)) println(s"$self received $topic Broadcast: $body")
      
    
      


  }
}