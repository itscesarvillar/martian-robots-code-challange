import io.mockk.spyk
import io.mockk.verify
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

  @Test
  internal fun couldReportItsPositionBeforeMoving() {
    val radio = spyk<Radio>()
    val spawn = Position(Point(2,2), Orientation.N)
    val robot = Robot(radio, spawn)
    robot.execute(Instruction.R)

    verify {
      radio.uplink(spawn)
    }
  }

  @Test
  internal fun movesAccordinglyToInstructions() {
    val radio = Radio()
    val spawn = Position(Point(2,2), Orientation.N)
    val robot = Robot(radio, spawn)
    robot.execute(Instruction.R)
    Assertions.assertEquals(Position(Point(2,2), Orientation.E), robot.position)
    robot.execute(Instruction.R)
    Assertions.assertEquals(Position(Point(2,2), Orientation.S), robot.position)
    robot.execute(Instruction.R)
    Assertions.assertEquals(Position(Point(2,2), Orientation.W), robot.position)
    robot.execute(Instruction.L)
    Assertions.assertEquals(Position(Point(2,2), Orientation.S), robot.position)
    robot.execute(Instruction.F)
    Assertions.assertEquals(Position(Point(2,1), Orientation.S), robot.position)
    robot.execute(Instruction.L)
    robot.execute(Instruction.F)
    Assertions.assertEquals(Position(Point(3,1), Orientation.E), robot.position)
  }

  @Test
  internal fun sensesTheScentOfAncientRobots_thenIgnoresDangerPoints() {
    val radio = Radio()
    // actually radio is real-time so it can be contemporaneous with each other
    // that is, this sentences could be place between robot instruction executions
    radio.addDeadPosition(Position(Point(2,2), Orientation.N))
    radio.addDeadPosition(Position(Point(2,2), Orientation.W))
    val spawn = Position(Point(2,2), Orientation.N)
    val robot = Robot(radio, spawn)

    robot.execute(Instruction.F)
    Assertions.assertEquals(Position(Point(2,2), Orientation.N), robot.position)
    robot.execute(Instruction.L)
    robot.execute(Instruction.F)
    Assertions.assertEquals(Position(Point(2,2), Orientation.W), robot.position)
  }
}
