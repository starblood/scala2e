package type_parameterization

/**
 * Created by john.shim on 2014. 12. 14..
 */
object UpperBoundDemo {
  def orderedMergeSort[T <: Ordered[T]](xs: List[T]): List[T] = {
    def merge(xs: List[T], ys: List[T]): List[T] =
      (xs, ys) match {
        case (Nil, _) => ys
        case (_, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (x < y) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }
    val n = xs.length / 2
    if (n == 0) xs
    else {
      val (ys, zs) = xs splitAt n
      merge(orderedMergeSort(ys), orderedMergeSort(zs))
    }
  }

  def main(args: Array[String]) {
    val people = List(
      new Person("Larry", "Wall"),
      new Person("Anders", "Hejlsberg"),
      new Person("Guido", "van Rossum"),
      new Person("Alan", "Kay"),
      new Person("Yukihiro", "Matsumoto")
    )
    val sortedPeople = orderedMergeSort(people)

    people.foreach(println)
    println("--- sorted ---")
    sortedPeople.foreach(println)
  }
}

class Person(val firstName: String, val lastName: String) extends Ordered[Person] {
  override def compare(that: Person): Int = {
    val lastNameComparison = lastName.compareToIgnoreCase(that.lastName)
    if (lastNameComparison != 0) {
      lastNameComparison
    } else {
      firstName.compareToIgnoreCase(that.firstName)
    }
  }
  override def toString = firstName + "," + lastName
}

