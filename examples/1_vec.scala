//> using options "-Xprint:typer"
import scala.compiletime.ops.int.+
def vec(s: Int) = Vec(s).asInstanceOf[Vec {val size: s.type }]
def add(a: Int, b: Int) = (a + b).asInstanceOf[a.type + b.type]

case class Vec(size: Int):
  def sum(that: Vec {val size: Vec.this.size.type}) = vec(size)
  def concat(that: Vec) = vec(add(size, that.size))

val v: Vec {val size: 13} = vec(6).concat(vec(7)).sum(vec(13))
