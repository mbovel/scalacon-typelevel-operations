//> using scala "3.nightly"
//> using options "-Xprint:typer"

import scala.Tuple._

def test() =
  Route("user" **: stringValue **: "post" **: intValue **: EmptyTuple)(
    (userName, postId) => println(userName)
  )

case class Route(partDefs: Tuple)(f: RouteArgTypes[partDefs.type] => Unit)

extension (a: Any) infix def **:(b: Tuple): a.type *: b.type = a *: b

class StringConverter[T](a: (x: String) => T):
  val convert = a

val intValue = StringConverter(x => x.toInt)
type IntValue = intValue.type

val stringValue = StringConverter(x => x)
type StringValue = stringValue.type

type RouteArgTypes[R <: Tuple] <: Tuple = R match
  case EmptyTuple => EmptyTuple
  case h *: t =>
    h match
      case StringConverter[r] =>
        r *: RouteArgTypes[t]
      case String =>
        RouteArgTypes[t]

@main def test2() =
  val matched: (String, Int) = matchRoute(
    List("user", "ada", "post", "42"),
    "ada" **: stringValue **: "post" **: intValue **: EmptyTuple
  )
  println(matched)

def matchRoute[R <: Tuple](parts: List[String], partDefs: R): RouteArgTypes[R] =
  partDefs match
    case _: EmptyTuple => EmptyTuple
    case t: (_ *: _) =>
      t.head match
        case c: StringConverter[_] =>
          c.convert(parts.head) *: matchRoute(parts.tail, t.tail)
        case _: String =>
          matchRoute(parts.tail, t.tail)
