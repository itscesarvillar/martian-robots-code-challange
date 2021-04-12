import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ReaderTest {
  @Test
  internal fun givenWrongInput_thenThrowsIllegalArgumentException() {
    Assertions.assertThrows(IllegalArgumentException::class.java) {
      reader("wrongInput")
    }
  }

  @Test
  internal fun givenWrongOrientationInput_thenThrowsIllegalArgumentException() {
    Assertions.assertThrows(IllegalArgumentException::class.java) {
      reader("wrongOrientationInput")
    }
  }
}
