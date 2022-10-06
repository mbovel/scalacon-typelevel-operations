//> using scala "3.2.0"
//> using options "-Xprint:typer"
import scala.compiletime.ops.int.{+}
import scala.compiletime.ops.string.{CharAt, Length, Substring}
import scala.Tuple._

type ArgTypes[S <: String] <: Tuple = S match
  case "" => EmptyTuple
  case _ =>
    CharAt[S, 0] match
      case '%' =>
        CharAt[S, 1] match
          case 'd' => Int *: ArgTypes[Substring[S, 2, Length[S]]]
          case 's' => String *: ArgTypes[Substring[S, 2, Length[S]]]
      case _ => ArgTypes[Substring[S, 1, Length[S]]]

def printf(s: String)(t: ArgTypes[s.type]): Unit = ()

def test() =
  printf("%s is %d")("Ada", 36) // works
  summon[ArgTypes["%s is %d"] =:= (String, Int)]
  // printf("%s is %d")(36, "Ada") // fails
