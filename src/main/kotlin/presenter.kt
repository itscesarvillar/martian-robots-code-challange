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
    fun showResults(results: List<Triple<Point, Orientation, Boolean>>) {
      val formatedLines = mutableListOf<String>()
      for (result in results) {
        var formatedLine = "${result.first.x} ${result.first.y} ${mapOrientation(result.second)}"
        if (result.third) {
          formatedLine += " LOST"
        }
        formatedLines.add(formatedLine)
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
