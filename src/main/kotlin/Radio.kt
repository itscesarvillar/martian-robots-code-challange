class Radio {
  var lastMessage: Position? = null
    private set

  fun uplink(position: Position) {
    this.lastMessage = position
  }
}
