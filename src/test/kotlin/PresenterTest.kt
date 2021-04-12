import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Test

class PresenterTest {
  @Test
  internal fun givenSingleRobot_thenPrintsSingleLine() {
    val sequence = listOf(
      Pair(Point(1,1), Orientation.E)
    )
    val presenter = spyk<Presenter.Companion>()

    presenter.showResults(sequence)

    verify {
      // spy on custom write method instead println
      presenter.write("1 1 E")
    }
  }
}
