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
      controller.end(listOf(Triple(Point(1,2), Orientation.N, false)))
    }
  }

  @Test
  internal fun onceUponATimeMarsWasFinite_thenRobotsGotLostBeforeExecutingTheirInstructions() {
    val controller = spyk<Controller.Companion>()

    controller.handleMission("lostSimpleInput")

    verify {
      controller.end(listOf(Triple(Point(0,2), Orientation.W, true)))
    }
  }
}
