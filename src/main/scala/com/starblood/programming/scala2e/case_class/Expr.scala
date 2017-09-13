package case_class

/**
 * Created by john.shim on 2014. 12. 14..
 */
abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr


object ExprDemo {
  def main(args: Array[String]) {
    val v = Var("x")
    val op = BinOp("+", Number(1), v)
  }
}
