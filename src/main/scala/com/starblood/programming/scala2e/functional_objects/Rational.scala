class Rational(n: Int, d: Int) {
	require(d != 0)

	private val g = gcd(n.abs, d.abs)
	val numer: Int = n / g
	val denom: Int = d / g
	
	def this(n: Int) = this(n, 1)

	def add(that: Rational): Rational = {
		new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
	}

	def + (that: Rational): Rational = {
		new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
	}

	def * (that: Rational): Rational = new Rational(numer * that.numer, denom * that.denom)
	def * (that: Int): Rational = new Rational(numer * that, denom)

	def / (that: Rational): Rational = new Rational(numer * that.denom, denom * that.numer)
	def / (i: Int): Rational = new Rational(numer, denom * i)

	override def toString = numer + "/" + denom

	private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

}

object Rational {
	def main(args: Array[String]) {
		implicit def intToRational(x: Int) = new Rational(x)
		val x = new Rational(16, 42)
		val x2 = new Rational(50, 42)

		println(x add x2)
		println(x + x2)
		println(x * x2)
		println((2 * x))
	}
}
