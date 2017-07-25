package com.gailo22.helloschedule

import akka.actor.{ActorSystem, Props}
import scala.concurrent.duration._

object HelloScheduleApp extends App {

  val system = ActorSystem("hellokernel")

  val actor = system.actorOf(Props[HelloSchedule])
  val config = system.settings.config
  val timer = config.getInt("helloSchedule.timer")

  system.actorOf(Props(
    new HelloScheduleCaller(
      timer millis, actor
    )
  ))
}
