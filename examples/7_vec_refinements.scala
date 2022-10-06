//> using options "-Xprint:typer"

// Needs to be compiled with Dotty at https://github.com/dotty-staging/dotty/tree/mb/linear-artihmetic-ops-normal-form-2.

import scala.compiletime.ops.int.*

class Vec[+T](private val l: List[T]):
  val size = l.size

  def ::[S >: T](x: S): Vec[S] { val size: SizedList.this.size.type + 1 } = ???
  def tail: Vec[T] { val size: SizedList.this.size.type - 1 } = ???
  def head: T = ???
  def map[S](f: T => S): Vec[S] { val size: SizedList.this.size.type } =
    nilOr(f(this.head) :: this.tail.map(f))

  def zip[S](
      that: Vec[S] { val size: SizedList.this.size.type }
  ): Vec[(T, S)] { val size: SizedList.this.size.type } =
    nilOr((this.head, that.head) :: this.tail.zip(that.tail))

  // Manual GDAT-like reasoning
  private def nilOr[T](
      f: => T
  ): Vec[Nothing] { val size: 0 & SizedList.this.size.type } | T =
    if size == 0 then asInstanceOf
    else f

val SizedNil = new SizedList(Nil):
  override val size: 0 = 0

/*object SizedNil:
  def unapply[T](l: SizedList[T]): Option[SizedList[Nothing] {val size: 0 & l.size.type}] =
    if l.size == 0 then Some(l.asInstanceOf)
    else None*/
