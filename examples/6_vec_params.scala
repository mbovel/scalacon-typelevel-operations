//> using options "-Xprint:typer"

// Needs to be compiled with Dotty at https://github.com/dotty-staging/dotty/tree/mb/linear-artihmetic-ops-normal-form-2.

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
