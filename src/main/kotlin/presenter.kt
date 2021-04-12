fun mapOrientation(value: Orientation): String {
  return when (value) {
    Orientation.N -> "N"
    Orientation.S -> "S"
    Orientation.E -> "E"
    Orientation.W -> "W"
  }
}
class Presenter {
  companion object {
    fun showResults(results: List<Pair<Point, Orientation>>) {
      val formatedLines = mutableListOf<String>()
      for (result in results) {
        formatedLines.add("${result.first.x} ${result.first.y} ${mapOrientation(result.second)}")
        // lines might be redundant, we can call write directly
      }
      write(formatedLines.joinToString("\n"))
    }

    // instead of spying the standard lib
    fun write(charseq: String) {
      println(charseq)
    }
  }
}
