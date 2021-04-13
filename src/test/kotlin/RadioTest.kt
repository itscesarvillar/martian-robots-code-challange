import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class RadioTest {
  @Test
  internal fun callingUplinkMethod_thenStorageThisMessage() {
    val radio = Radio()
    val message = Spawn(Point(1,2), Orientation.E) // time to refactor... name and place
    radio.uplink(message) // should who uses the radio say who is it?
    Assertions.assertEquals(message, radio.lastMessage)
  }
}
