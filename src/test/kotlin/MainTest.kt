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
}
