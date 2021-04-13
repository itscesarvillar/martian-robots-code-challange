class Radio {
  val deadPositions: MutableList<Position> = mutableListOf()

  var lastMessage: Position? = null
    private set

  fun uplink(position: Position) {
    this.lastMessage = position
  }

  fun addDeadPosition(position: Position) {
    this.deadPositions.add(position)
  }
}
