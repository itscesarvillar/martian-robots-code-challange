fun main(args: Array<String>) {
  val resource = if (args.isNotEmpty()) args[0] else "input"
  Controller.handleMission(resource)
}

class Controller {
  companion object {
    private fun init(resource: String): Input {
      return reader(resource)
    }
    private fun mission(missionBriefing: Input): List<Triple<Point, Orientation, Boolean>> {
      val results: MutableList<Triple<Point, Orientation, Boolean>> = mutableListOf()
      val radio = Radio()

      for ((spawnPosition, instructions) in missionBriefing.robotsSequence) {
        val robot = Robot(radio, spawnPosition)
        for (instruction in instructions) {
          robot.execute(instruction)
        }
        results.add(Triple(robot.position.point, robot.position.orientation, false))
      }
      return results;
    }
    fun end(results: List<Triple<Point, Orientation, Boolean>>) {
      Presenter.showResults(results)
    }

    fun handleMission(mapFromResource: String) {
      val instructions = this.init(mapFromResource)
      val results = this.mission(instructions)
      this.end(results)
    }
  }
}
