//> using scala "3.2.0"
//> using options "-Xprint:typer"

import scala.compiletime.ops.int.*

class Vec[Len <: Int, +T]:
  def ::[S >: T](x: S): Vec[Len + 1, T] = ???
  def tail: Vec[Len - 1, T] = ???
  def drop[N <: Int & Singleton](n: N): Vec[Len - N, T] = ???
  def head: T = ???
  def zip[S](that: Vec[Len, S]): Vec[Len, (T, S)] = ???
  def concat[S >: T, ThatLen <: Int](
      that: Vec[ThatLen, S]
  ): Vec[Len + ThatLen, S] = ???

def test() =
  val a = Vec[4, String]()
  val b = Vec[2, String]()
  val c = Vec[5, Int]()
  val d = a.concat(b).zip(42 :: c)
  val e = a.concat(b).zip(42 :: c)
  val f = a.drop(2).concat(b).zip(c.tail)
