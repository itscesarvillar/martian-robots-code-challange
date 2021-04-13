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

  @Test
  internal fun givenWrongLengthInput_thenThrowsIllegalArgumentException() {
    Assertions.assertThrows(IllegalArgumentException::class.java) {
      reader("wrongLengthInput")
    }
  }

  @Test
  internal fun givenRightInput_returnsInputObject() {
    val input = reader("sampleInput")
    Assertions.assertEquals(Point(5, 3), input.upperRightGridPoint)

    val sequence = listOf<Pair<Position, List<Instruction>>>(
      Pair(Position(Point(1, 1), Orientation.E), listOf<Instruction>(Instruction.R, Instruction.F, Instruction.R, Instruction.F, Instruction.R, Instruction.F, Instruction.R, Instruction.F)),
      Pair(Position(Point(3, 2), Orientation.N), listOf<Instruction>(Instruction.F, Instruction.R, Instruction.R, Instruction.F, Instruction.L, Instruction.L, Instruction.F, Instruction.F, Instruction.R, Instruction.R, Instruction.F, Instruction.L, Instruction.L)),
      Pair(Position(Point(0, 3), Orientation.W), listOf<Instruction>(Instruction.L, Instruction.L, Instruction.F, Instruction.F, Instruction.F, Instruction.L, Instruction.F, Instruction.L, Instruction.F, Instruction.L))
    )
    Assertions.assertEquals(sequence, input.robotsSequence)
  }
}
