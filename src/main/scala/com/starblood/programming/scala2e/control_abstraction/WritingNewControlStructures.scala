package control_abstraction

import java.io.{PrintWriter, File}

/**
 * Created by john.shim on 2014. 12. 7..
 */
object WritingNewControlStructures {
  def withPrintWriter(file: File, op: PrintWriter => Unit): Unit = {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }

  def withPrintWriter2(file: File)(op: PrintWriter => Unit): Unit = {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }

  def main(args: Array[String]) {
    // using two arguments
    withPrintWriter(
      new File("date.txt"),
      writer => writer.println("hello")
    )

    // using curring
    withPrintWriter2(new File("date2.txt")) {
      writer => writer.write("hello2")
    }
  }
}
