//> using scala "3.2.0"
//> using options "-Xprint:typer"
//> using file "3_vec_params_simple.scala"

def test2() =
  val size: Int = ???
  val v = Vec[size.type, Int]()
  v.zip(42 :: v.tail)
  // Error:
  //   Found: Vec[(size : Int) - (1 : Int) + (1 : Int), Int]
  //   Required: Vec[(size : Int), Any]
