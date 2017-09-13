abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

val v = Var("x")
val op = BinOp("+", Number(1), v)

op.copy(operator = "-")

val list = List(1, 0, 3)

list match {
  case List(1, _*) => println ("found")
  case _ => println ("not found")
}

