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
}
