import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class RadioTest {
  @Test
  internal fun callingUplinkMethod_thenStorageThisMessage() {
    val radio = Radio()
    val message = Position(Point(1,2), Orientation.E)
    radio.uplink(message) // should who uses the radio say who is it?
    Assertions.assertEquals(message, radio.lastMessage)
  }

  @Test
  internal fun hasDeadPositionsPropertyAsScentWithAddMethod() {
    val radio = Radio()
    Assertions.assertEquals(0, radio.deadPositions.size)
    radio.addDeadPosition(Position(Point(0,0), Orientation.S))
    Assertions.assertEquals(1, radio.deadPositions.size)
  }

  /**
   * Although is not required it might be worthy having a parameter in constructor and init block
   * or load method so radio could remember previous experiences
   *
  @Test
  internal fun mightHavePresavedDeadPositions() {
  }
  */
}
