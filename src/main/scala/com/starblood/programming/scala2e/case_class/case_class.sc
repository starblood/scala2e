abstract class Term
case class Var(name: String) extends Term
case class Fun(arg: String, body: Term) extends Term
case class App(f: Term, v: Term) extends Term

Fun("x", Fun("y", App(Var("x"), Var("y"))))

val x = Var("x")
println(x.name)
val x1 = Var("x")
val x2 = Var("x")
val y1 = Var("y")
println("" + x1 + " == " + x2 + " => " + (x1 == x2))
println("" + x1 + " == " + x2 + " => " + (x1.equals(x2)))
println("" + x1 + " == " + y1 + " => " + (x1 == y1))
object TermTest extends scala.App {
  def printTerm(term: Term) {
    term match {
      case Var(n) =>
        print(n)
      case Fun(x, b) =>
        print("^" + x + ".")
        printTerm(b)
      case App(f, v) =>
        print("(")
        printTerm(f)
        print(" ")
        printTerm(v)
        print(")")
    }
  }

  def isIdentityFun(term: Term): Boolean = term match {
    case Fun(x, Var(y)) if x == y => true
    case _ => false
  }

  val id = Fun("x", Var("x"))
  val t = Fun("x", Fun("y", App(Var("x"), Var("y"))))
  printTerm(t)
  println
  println(isIdentityFun(id))
  println(isIdentityFun(t))
}

class Hello(x: Int) {
  var xx = x
  override def toString = s"hello, ${xx}"
}

object Hello {
  def apply(x: Int) = new Hello(x)
  def apply(x: Int, y: Int) = new Hello(x+y)

  def main(args: Array[String]) {

  }
}

val hello1 = Hello(1)
val hello2 = Hello(1,2)