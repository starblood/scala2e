package control_abstraction

/**
 * Created by john.shim on 2014. 12. 6..
 */
object FileMatcher {
  private def filesHere = (new java.io.File(".")).listFiles()

  private def filesMatching(matcher: String => Boolean) =
    for (file <- filesHere ; if matcher(file.getName))
      yield file

  def filesEnding(query: String) = filesMatching(_.endsWith(query))
  def filesContaining(query: String) = filesMatching(_.contains(query))
  def filesRegex(query: String) = filesMatching(_.matches(query))

  def main(args: Array[String]) {
    filesEnding("sbt").foreach(println)
  }
}

