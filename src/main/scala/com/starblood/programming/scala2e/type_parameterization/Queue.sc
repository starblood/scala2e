class SlowAppendQueue[T] (elems: List[T]) { // not efficient
  def head = elems.head
  def tail = new SlowAppendQueue[T](elems.tail)
  def enqueue(x: T) = new SlowAppendQueue[T](elems ::: List(x))
}

class SlowHeadQueue[T] (smele: List[T]) { // not efficient
  // smele is elems reversed
  def head = smele.tail
  def tail = smele.init
  def enqueue(x: T) = new SlowHeadQueue[T](x :: smele)
}

trait Queue[T] {
  def head: T
  def tail: Queue[T]
  def enqueue(x: T): Queue[T]
}

object Queue {
  def apply[T](xs: T*): Queue[T] = new QueueImpl[T](xs.toList, Nil)

  private class QueueImpl[T] (private val leading: List[T],
                              private val trailing: List[T]) extends Queue[T] {
    def mirror = if (leading.isEmpty) new QueueImpl(trailing.reverse, Nil) else this
    def head: T = mirror.leading.head
    def tail: QueueImpl[T] = { val q = mirror ; new QueueImpl(q.leading.tail, q.trailing)}
    def enqueue(x: T) = new QueueImpl(leading, x :: trailing)
  }
}

val q1 = Queue[String]("a")
// val q2: Queue[Any] = q1 should support covariance

class Cell[T](init: T) {
  private[this] var current = init
  def get = current
  def set(x:T) { current = x}
}

val c1 = new Cell[String]("abc")
//val c2: Cell[Any] = c1 error
