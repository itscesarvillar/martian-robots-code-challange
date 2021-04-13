import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Test

class MainTest {
  @Test
  internal fun givenSimpleInput_thenProducesASimpleOutput() {
    val controller = spyk<Controller.Companion>()

    // no dead positions, just one robot
    controller.handleMission("simpleInput")

    verify {
      controller.end(listOf(Pair(Position(Point(1,2), Orientation.N), false)))
    }
  }

  @Test
  internal fun onceUponATimeMarsWasFinite_thenRobotsGotLostBeforeExecutingTheirInstructions() {
    val controller = spyk<Controller.Companion>()

    controller.handleMission("lostSimpleInput")

    verify {
      controller.end(listOf(Pair(Position(Point(0,2), Orientation.W), true)))
    }
  }

  @Test
  internal fun givenALostRobot_thenHelpsOther() {
    val controller = spyk<Controller.Companion>()

    controller.handleMission("helpingRobotInput")

    verify {
      controller.end(listOf(
        Pair(Position(Point(0,2), Orientation.W), true),
        Pair(Position(Point(0,1), Orientation.S), false)
      ))
    }
  }

  @Test
  internal fun given3Robots_thenOneLost2Survivors() {
    // the same input in docs
    // 1 1 E
    // 3 3 N LOST
    // 2 3 S
    val controller = spyk<Controller.Companion>()

    controller.handleMission("sampleInput")

    verify {
      controller.end(listOf(
        Pair(Position(Point(1,1), Orientation.E), false),
        Pair(Position(Point(3,3), Orientation.N), true),
        Pair(Position(Point(2,3), Orientation.S), false)
      ))
    }
  }
}
