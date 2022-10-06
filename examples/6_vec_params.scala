//> using scala "3.2.0"
//> using options "-Xprint:typer"

import scala.compiletime.ops.int.*

enum Vec[Len <: Int, +T]:
  case Nil extends Vec[0, Nothing]
  case NotNil[N <: Int, T]() extends Vec[N, T]

  def ::[S >: T](x: S): Vec[Len + 1, T] = ???
  def tail: Vec[Len - 1, T] = ???
  def head: T = ???

  def map[S](f: T => S): Vec[Len, S] =
    this match
      case Vec.Nil => Vec.Nil
      case _       => f(this.head) :: this.tail.map(f)
