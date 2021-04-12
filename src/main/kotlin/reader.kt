import java.lang.IllegalArgumentException

data class Input(val upperRightGridPoint: Point)
// this is going to be reusable somewhere else, so it's a refactor thing
data class Point(val x: Int, val y: Int)
enum class Orientation { N, S, E, W }
fun mapOrientation(value: String): Orientation {
  return when (value) {
    "N" -> Orientation.N
    "S" -> Orientation.S
    "E" -> Orientation.E
    "W" -> Orientation.W
    else -> throw IllegalArgumentException()
  }
}


fun reader(resource: String): Input {
  fun getResourceAsText(path: String): String {
    return object {}.javaClass.getResource(path).readText()
  }
  val lines: List<String> = getResourceAsText(resource).split('\n').filter { line -> line.length > 0}
  val (x, y) = lines.first().split(' ')
  // we could check x or y is not greater than 50, but why should we restrict mars planet size?
  try {
    val upperRightGridPoint = Point(x.toInt(), y.toInt())
    val instructions = lines.drop(1)
    val instructionsIterator = instructions.iterator()
    // var instructionsPair: List<Pair<String, String>> = mutableListOf()
    while (instructionsIterator.hasNext()) { //TODO refactor, check 2 in advance
      // spawn point
      val spawnPointRaw = instructionsIterator.next().split(' ')
      val (x, y, orientationInput) = spawnPointRaw
      // TODO: could be checked x and y is less than upperRightGridPoint
      val spawnPoint = Point(x.toInt(), y.toInt())
      val orientation: Orientation = mapOrientation(orientationInput)

      // movements
    }

    return Input(upperRightGridPoint)
  }
  catch (e: Exception) {
    throw IllegalArgumentException("invalid input please read docs")
  }
}
