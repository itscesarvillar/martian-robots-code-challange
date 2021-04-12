import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Test

class PresenterTest {
  @Test
  internal fun givenSingleRobot_thenPrintsSingleLine() {
    val sequence = listOf(
      Triple(Point(1,1), Orientation.E, false)
    )
    val presenter = spyk<Presenter.Companion>()

    presenter.showResults(sequence)

    verify {
      // spy on custom write method instead println
      presenter.write("1 1 E")
    }
  }

  @Test
  internal fun givenSomeRobotsSomeOfThemLost_thenPrintsMultipleLinesSomeWithLost() {
    val sequence = listOf(
      Triple(Point(1,1), Orientation.E, false),
      Triple(Point(3,3), Orientation.N, true),
      Triple(Point(2,3), Orientation.S, false)
    )

    val presenter = spyk<Presenter.Companion>()

    presenter.showResults(sequence)

    verify {
      presenter.write("1 1 E\n3 3 N LOST\n2 3 S")
    }
  }
}
