class Robot(private val radio: Radio, spawnPosition: Position) {
  var position = spawnPosition

  fun execute(instruction: Instruction) {
    radio.uplink(this.position)
    when (instruction) {
      Instruction.R -> this.moveRight()
      Instruction.L -> this.moveLeft()
      Instruction.F -> if (this.isSafePosition(this.position)) { this.moveForward() }
    }
  }

  private fun moveRight() {
    val newOrientation = when (this.position.orientation) {
      Orientation.N -> Orientation.E
      Orientation.S -> Orientation.W
      Orientation.E -> Orientation.S
      Orientation.W -> Orientation.N
    }
    this.position = Position(this.position.point, newOrientation)
  }

  private fun moveLeft() {
    val newOrientation = when (this.position.orientation) {
      Orientation.N -> Orientation.W
      Orientation.S -> Orientation.E
      Orientation.E -> Orientation.N
      Orientation.W -> Orientation.S
    }
    this.position = Position(this.position.point, newOrientation)
  }

  private fun moveForward() {
    val delta = when (this.position.orientation) {
      Orientation.N -> Pair(0,1)
      Orientation.S -> Pair(0,-1)
      Orientation.E -> Pair(1,0)
      Orientation.W -> Pair(-1,0)
    }
    val (x,y) = this.position.point
    this.position = Position(Point(x + delta.first, y + delta.second), this.position.orientation)
  }

  private fun isSafePosition(position: Position): Boolean {
    return !this.radio.deadPositions.contains(position)
  }
}
