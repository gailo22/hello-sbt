import akka.actor.{ActorSystem, Props}

object Main extends App {

  val system = ActorSystem("HelloSystem")
  val helloActor = system.actorOf(Props(new HelloActor("John")), name = "helloactor")
  helloActor ! "hello"
  helloActor ! "sawadee"

}
