package com.gailo22.hello.cats

sealed trait Json
final case class JsObject(get: Map[String, Json]) extends Json
final case class JsString(get: String) extends Json
final case class JsNumber(get: Double) extends Json

trait JsonWriter[A] {
  def write(value: A): Json
}

final case class Person(name: String, email: String)

object JsonWriterInstances {
  implicit val stringJsonWriter = new JsonWriter[String] {
    def write(value: String): Json =
      JsString(value)
  }

  implicit val personJsonWriter = new JsonWriter[Person] {
    def write(value: Person): Json =
      JsObject(Map(
        "name" -> JsString(value.name),
        "email" -> JsString(value.email)
      ))
  }
}
