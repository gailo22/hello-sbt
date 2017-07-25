import akka.actor.{ActorSystem, Props}
import com.gailo22.hello.HelloActor

object Main extends App {

  val system = ActorSystem("HelloSystem")
  val helloActor = system.actorOf(Props(new HelloActor("John")), name = "helloactor")
  helloActor ! "hello"
  helloActor ! "sawadee"

}
