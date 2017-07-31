package com.gailo22.hello.cats

trait Monoid[A] {
  def mappend(a1: A, a2: A): A
  def mzero: A
}

object Monoid {

  implicit val intMonoid: Monoid[Int] = new Monoid[Int] {
    def mappend(a1: Int, a2: Int): Int = a1 + a2
    def mzero: Int = 0
  }

  implicit val stringMonoid: Monoid[String] = new Monoid[String] {
    def mappend(a1: String, a2: String): String = a1 + a2
    def mzero: String = ""
  }

  val multiMonoid: Monoid[Int] = new Monoid[Int] {
    def mappend(a: Int, b: Int): Int = a * b
    def mzero: Int = 1
  }

}

object main extends App {

  import Monoid._

  def sum[A](xs: List[A])(implicit m: Monoid[A]): A =
    xs.foldLeft(m.mzero)(m.mappend)

  def sum2[A: Monoid](xs: List[A]) = {
    val m = implicitly[Monoid[A]]
    xs.foldLeft(m.mzero)(m.mappend)
  }

  val s = sum(List(1,2,3,4,5))
  val s2 = sum2(List(1,2,3,4,5,6))
  println(s)
  println(s2)

  val s3 = sum2(List("a", "b", "c"))
  println(s3)

  val s4 = sum(List(1,2,3))(multiMonoid)
  println(s4)
}
