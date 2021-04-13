fun main(args: Array<String>) {
  val resource = if (args.isNotEmpty()) args[0] else "input"
  Controller.handleMission(resource)
}

class Controller {
  companion object {
    private fun isOutOfBounds(point: Point, gridSize: Point): Boolean
      = point.x !in 0..gridSize.x || point.y !in 0..gridSize.y

    private fun init(resource: String): Input = reader(resource)

    private fun mission(missionBriefing: Input): List<Pair<Position, Boolean>> {
      val results: MutableList<Pair<Position, Boolean>> = mutableListOf()
      val radio = Radio()

      for ((spawnPosition, instructions) in missionBriefing.robotsSequence) {
        val robot = Robot(radio, spawnPosition)
        val instructionIterator = instructions.iterator()
        while(!this.isOutOfBounds(robot.position.point, missionBriefing.upperRightGridPoint)
          && instructionIterator.hasNext()) {
          val instruction = instructionIterator.next()
          robot.execute(instruction)
        }
        val isRobotLost = this.isOutOfBounds(robot.position.point, missionBriefing.upperRightGridPoint)
        val positionToResult = if (isRobotLost) {
          radio.lastMessage!!
        } else {
          robot.position
        }
        results.add(Pair(positionToResult, isRobotLost))
      }
      return results;
    }
    fun end(results: List<Pair<Position, Boolean>>) {
      Presenter.showResults(results.map{ result -> Triple(result.first.point, result.first.orientation, result.second)})
    }

    fun handleMission(mapFromResource: String) {
      val instructions = this.init(mapFromResource)
      val results = this.mission(instructions)
      this.end(results)
    }
  }
}
