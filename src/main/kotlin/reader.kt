import java.lang.IllegalArgumentException

fun mapOrientation(value: String): Orientation {
  return when (value) {
    "N" -> Orientation.N
    "S" -> Orientation.S
    "E" -> Orientation.E
    "W" -> Orientation.W
    else -> throw IllegalArgumentException()
  }
}
fun mapInstruction(value: String): Instruction {
  return when (value) {
    "L" -> Instruction.L
    "R" -> Instruction.R
    "F" -> Instruction.F
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
    val sequence = lines.drop(1)
    val sequenceIterator = sequence.iterator()
    val spawnInstructionPairs: MutableList<Pair<Position, List<Instruction>>> = mutableListOf()
    while (sequenceIterator.hasNext()) { // Check there is no instructions left, inside try to get two, else throw exception
      // spawn point or initial position
      val spawnPointRaw = sequenceIterator.next().split(' ')
      val (x, y, orientationInput) = spawnPointRaw
      // TODO: could be checked x and y is less than upperRightGridPoint, otherwise LOST in an outbound point will be printed... not big deal
      val spawnPoint = Point(x.toInt(), y.toInt())
      val orientation: Orientation = mapOrientation(orientationInput)

      // movements or instructions
      val instructions = sequenceIterator.next()
        .split("")
        .filter { item -> item.length > 0}
        .map { instructionInput -> mapInstruction(instructionInput)}

      // mix them
      spawnInstructionPairs.add(Pair(Position(spawnPoint, orientation), instructions))
    }
    return Input(upperRightGridPoint, spawnInstructionPairs)
  }
  catch (e: Exception) {
    throw IllegalArgumentException("invalid input please read docs")
  }
}
