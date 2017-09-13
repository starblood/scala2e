package control_abstraction

/**
 * Created by john.shim on 2014. 12. 7..
 */
object ByNameParameters {
  var assertionEnabled = true

  // instead of using predicate: () => Boolean, we can write like below
  def myAssert(predicate: => Boolean) = {
    if (assertionEnabled && !predicate)
      throw new AssertionError
  }

  def boolAssert(predicate: Boolean) = {
    if (assertionEnabled && !predicate)
      throw new AssertionError
  }

  def main(args: Array[String]) {
    myAssert(5 > 3) // by name parameter demo, 3 > 5 evaluated after function call
    boolAssert(5 > 3) // by value parameter demo, 3 > 5 evaluated before function call

    assertionEnabled = false

    val x = 10
    myAssert(x / 0 == 0) // no error
    boolAssert(x / 0 == 0) // error
  }
}
