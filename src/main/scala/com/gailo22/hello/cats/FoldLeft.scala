package com.gailo22.hello.cats

trait FoldLeft[F[_]] {
  def foldLeft[A, B](xs: F[A], b: B, f: (B, A) => B): B
}

object FoldLeft {
  implicit val foldLeftList: FoldLeft[List] = new FoldLeft[List] {
    def foldLeft[A, B](xs: List[A], b: B, f: (B, A) => B): B =
      xs.foldLeft(b)(f)
  }
}

object main2 extends App {
  import FoldLeft._

  def sum[M[_]: FoldLeft, A: Monoid](xs: M[A]): A = {
    val m = implicitly[Monoid[A]]
    val fl = implicitly[FoldLeft[M]]
    fl.foldLeft(xs, m.mzero, m.mappend)
  }

  val s = sum(List(1,2,3))
  val s2 = sum(List("a", "b", "c"))
  println(s)
  println(s2)
}
