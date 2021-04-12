import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions

class ReaderTest {
  @Test
  internal fun wrongInput() {
    // for now just read lines, don't check validity
    val expected: List<String> = listOf("This is a", "wrong input", "for this challenge");
    val actual: List<String> = reader("wrongInput")

    Assertions.assertEquals(actual, expected);
  }
}
