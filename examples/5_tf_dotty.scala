//> using scala "3.2.0"

// Inspired by https://github.com/MaximeKjaer/tf-dotty

import scala.compiletime.ops.int.*

sealed trait Shape
infix case class #:[H <: Int & Singleton, T <: Shape](h: H, t: T) extends Shape
object Nil extends Shape
type Nil = Nil.type

def mult[X <: Int, Y <: Int](x: X, y: Y) = (x * y).asInstanceOf[X * Y]

type Size[X <: Shape] <: Int = X match
  case Nil            => 1
  case #:[head, tail] => head * Size[tail]

def size[S <: Shape](s: S): Size[S] = s match
  case _: Nil               => 1
  case cons: #:[head, tail] => mult(cons.h, size(cons.t))

type Reduce[S <: Shape, Axes <: Shape] = ReduceLoop[S, Axes, 0]

type ReduceLoop[S <: Shape, Axes <: Shape, I <: Int] <: Shape = S match
  case Nil => Nil
  case #:[head, tail] =>
    Contains[Axes, I] match
      case true  => ReduceLoop[tail, Axes, I + 1]
      case false => #:[head, ReduceLoop[tail, Axes, I + 1]]

type Contains[S <: Shape, N] = S match
  case Nil            => false
  case #:[N, tail]    => true
  case #:[head, tail] => Contains[tail, N]

class Tensor[T, S <: Shape]():
  def add(t: Tensor[T, S]) = this
  def mean[A <: Shape](axes: A): Tensor[T, Reduce[S, A]] =
    Tensor[T, Reduce[S, A]]()
  def reshape[T, B <: Shape](b: B)(using Size[S] =:= Size[B]): Tensor[T, B] =
    Tensor[T, B]()

@main def test() =
  val shape = #:(5, #:(6, #:(2, Nil)))
  summon[Size[shape.type] <:< 60]
  summon[Reduce[shape.type, 0 #: Nil] <:< 6 #: 2 #: Nil]
  summon[Reduce[shape.type, 0 #: 2 #: Nil] <:< 6 #: Nil]
  summon[Reduce[shape.type, 1 #: 6 #: Nil] <:< 5 #: 2 #: Nil]

  val t1 = Tensor[Int, 5 #: 6 #: 2 #: Nil]
  val t2 = Tensor[Int, 5 #: 6 #: 2 #: Nil]
  val t3 = t1.add(t2)
  val t4 = t1.mean(#:(0, #:(2, Nil)))
  val t5 = t1.reshape(#:(2, #:(6, #:(5, Nil))))
