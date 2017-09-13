class Animal
class Mammal extends Animal
class Giraffe extends Mammal
class Tiger extends Mammal
class Dolphin extends Animal

case class ListNode[+T](h: T, t: ListNode[T]) {
  def head: T = h

  def tail: ListNode[T] = t

  def prepend[U >: T](elem: U): ListNode[U] =
    ListNode(elem, this)
}


val empty: ListNode[Null] = ListNode(null, null)
val strList: ListNode[String] = empty.prepend("hello")
  .prepend("world")
val anyList: ListNode[Any] = strList.prepend(12345)


val giraffeList = ListNode[Giraffe](new Giraffe, null)
// covariance
val mammalList: ListNode[Mammal] = giraffeList.prepend(new Tiger)
// compile error, need contravariance
//val mammalList2: ListNode[Tiger] = giraffeList.prepend(new Mammal)