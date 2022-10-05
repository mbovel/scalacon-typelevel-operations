import scala.quoted.*

object Macro {
  transparent inline def read(file: String): Any =
    ${ impl('file) }

  private def impl(file: Expr[String])(using Quotes): Expr[Any] = {
    import quotes.reflect.*
    val refinementType = Refinement(TypeRepr.of[Object], "hello", TypeRepr.of[String]).asType

    refinementType match
      case '[t] => '{ Selectable.asInstanceOf[t] }
  }
}
