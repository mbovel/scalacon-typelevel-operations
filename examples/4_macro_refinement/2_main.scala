transparent inline def refine(inline file: String) = Macro.read(file)

def test() =
  val x = refine("hello.txt")
