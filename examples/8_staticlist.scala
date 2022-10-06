import scala.compiletime.ops.int.*

// Not included in the talk

sealed abstract class StaticList[+T]

object Nil extends StaticList[Nothing]
type Nil = Nil.type

abstract class Cons[T] extends StaticList[T]:
  val head: T
  val tail: StaticList[T]

def cons[T](h: T, t: StaticList[T]) = new Cons[T]:
  val head: h.type = h
  val tail: t.type = t

@main def test =
  val xs = cons("a", cons("b", Nil))
