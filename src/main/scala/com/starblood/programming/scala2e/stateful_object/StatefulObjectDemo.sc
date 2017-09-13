class BankAccount {
  private var bal: Int = 0
  def balance: Int = bal
  def deposit(amount: Int): Unit = {
    require(amount > 0)
    bal += amount
  }
  def withdraw(amount: Int): Boolean =
    if (amount > bal) false
    else {
      bal -= amount
      true
    }
}

val account = new BankAccount
account deposit 100
// account deposit -1 error
account withdraw 80
account withdraw 80


class Time {
  private[this] var h = 12
  private[this] var m = 0

  def hour: Int = h
  def hour_=(x: Int): Unit = {
    require(0 <= x && x <= 24)
    h = x
  }

  def minute: Int = m
  def minute_=(x: Int): Unit = {
    require(0 <= x && x <= 60)
    m = x
  }

}