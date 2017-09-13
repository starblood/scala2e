trait RationalTrait {
  val numerArg: Int
  val denomArg: Int
  require(denomArg != 0)
  private val g = gcd(numerArg, denomArg)
  val numer = numerArg / g
  val denom = denomArg / g
  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)

  override def toString = numer +"/"+ denom
}

val x = 2

/*  compile error
    An implementing val definition in a subclass, by contrast,
    is evaluated only after the superclass has been initialized.
  new RationalTrait {
  val denomArg = 1
  val numerArg = 2 * x
}*/

new {
  val numerArg = 1
  val denomArg = 2 * x
} with RationalTrait

object twoThirds extends {
  val numerArg = 2
  val denomArg = 3
} with RationalTrait

twoThirds

/*  Because pre-initialized fields are initialized before
    the superclass constructor is called,
    their initializers cannot refer to the object that’s being constructed.
new {
  val numerArg = 1
  val denomArg = this.numerArg * 2
} with RationalTrait*/

// 1/2 + 1/3 = (1 * 3 + 1 * 2) / (2 * 3)
class RationalClass(n: Int, d: Int) extends {
  val numerArg = n
  val denomArg = d
} with RationalTrait {
  def + (that: RationalClass) = new RationalClass(
    numerArg * that.denom + that.numer * denom,
    denom * that.denom
  )
}

object Demo{
  val x = {println("initializing x"); println("done")}
}

Demo // initialized
Demo.x
object Demo2 {
  lazy val x = {println("initializing x"); println("done")}
}
Demo2
Demo2.x // initialized

trait LazyRationalTrait {
  val numerArg: Int
  val denomArg: Int
  lazy val numer =  numerArg / g
  lazy val denom = denomArg / g

  private lazy val g = {
    require(denomArg != 0)
    gcd(numerArg, denomArg)
  }
  override def toString = numer + "/" + denom
  private def gcd(a: Int, b: Int): Int = {
    if (b == 0) a.abs else gcd(b, a % b)
  }
}

val x2 = 2

new LazyRationalTrait {
  val numerArg = 1 * x2
  val denomArg = 2 * x2
}
/*
  1. First, a fresh instance of LazyRationalTrait gets created, and the initialization code of LazyRationalTrait is run.
     This initialization code is empty—none of the fields of LazyRationalTrait is as yet initialized.
  2. Next, the primary constructor of the anonymous subclass defined by the new expression is executed.
     This involves the initialization of numerArg with 2 and denomArg with 4.
  3. Next, the toString method is invoked on the constructed object by the interpreter,
     so that the resulting value can be printed.
  4. Next, the numer field is accessed for the first time by the toString method in trait LazyRationalTrait,
     so its initializer is evaluated.
  5. The initializer of numer accesses the private field, g, so g is evaluated next.
     This evaluation accesses numerArg and denomArg, which were defined in Step 2.
  6. Next,the toString method accesses the value of denom, which causes denom’s evaluation.
     The evaluation of denom accesses the values of denomArg and g.
     The initializer of the g field is not re-evaluated,
     because it was already evaluated in Step 5.
  7. Finally, the result string "1/2" is constructed and printed.
 */