class Robot (private val radio: Radio, spawnPosition: Position) {
  val position = spawnPosition

  fun execute () {
    radio.uplink(this.position)
    // then moves
  }
}
