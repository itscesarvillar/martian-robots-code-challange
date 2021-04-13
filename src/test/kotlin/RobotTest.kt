import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class RobotTest {
  @Test
  internal fun acceptsRadioAndPositionInConstructor_thenPositionCanBeConsultedLater() {
    val radio = Radio()
    val spawn = Position(Point(2,2), Orientation.N)
    val robot = Robot(radio, spawn)
    Assertions.assertEquals(spawn, robot.position)
  }
}
