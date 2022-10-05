class MySelectable extends Selectable:
    def selectDynamic(name: String): Any =
        name match
            case "foo" => 1
            case "bar" => "hey"

val s = MySelectable().asInstanceOf[MySelectable {val foo: Int; val bar: String}]

@main def test() =
    println(s.foo)
    println(s.bar)
