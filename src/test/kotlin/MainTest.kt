import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Test

class MainTest {
  @Test
  internal fun givenSimpleInput_thenProducesASimpleOutput() {
    val presenter = spyk<Presenter.Companion>()

    // no dead positions, just one robot
    main(arrayOf("simpleInput"))

    verify {
      presenter.write("2 3 S")
    }
  }
}
