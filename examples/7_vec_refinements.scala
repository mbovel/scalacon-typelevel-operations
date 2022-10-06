//> using scala "3.2.0"
//> using options "-Xprint:typer"

import scala.compiletime.ops.int.*

class SizedList[+T](private val l: List[T]):
  val size = l.size

  def ::[S >: T](
      x: S
  ): SizedList[S] { val size: SizedList.this.size.type + 1 } = ???
  def tail: SizedList[T] { val size: SizedList.this.size.type - 1 } = ???
  def head: T = ???
  def map[S](f: T => S): SizedList[S] { val size: SizedList.this.size.type } =
    nilOr(f(this.head) :: this.tail.map(f))

  def zip[S](
      that: SizedList[S] { val size: SizedList.this.size.type }
  ): SizedList[(T, S)] { val size: SizedList.this.size.type } =
    nilOr((this.head, that.head) :: this.tail.zip(that.tail))

  private def nilOr[T](
      f: => T
  ): SizedList[Nothing] { val size: 0 & SizedList.this.size.type } | T =
    if size == 0 then asInstanceOf
    else f

val SizedNil = new SizedList(Nil):
  override val size: 0 = 0

/*object SizedNil:
  def unapply[T](l: SizedList[T]): Option[SizedList[Nothing] {val size: 0 & l.size.type}] =
    if l.size == 0 then Some(l.asInstanceOf)
    else None*/
