import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PinTEST {
    @Test
    @DisplayName("State Test 1")
    void pinTest1(){
        Pin pin = new Pin(0, null);
        assertEquals(false, pin.getState(), "Pin state should be false");
    }

    @Test
    @DisplayName("State Test 2")
    void pinTest2(){
        Pin pin = new Pin(0, null, true);
        assertEquals(true, pin.getState(), "Pin state should be true");
    }

    @Test
    @DisplayName("State Test 3")
    void pinTest3(){
        Pin pin = new Pin(0, null, true);
        pin.setState(false);
        assertEquals(false, pin.getState(), "Pin state should be false");
    }

    @Test
    @DisplayName("Connect Test")
    void pinTest4(){
        Pin pin = new Pin(0, null, true);
        Pin pin2 = new Pin(1, null, false);
        pin.connect(pin2);
        assertEquals(pin.getState(), pin2.getState(), "Pins state should match");
        assertEquals(true, pin.getState(), "Pins should be true");
        pin.setState(false);
        assertEquals(pin.getState(), pin2.getState(), "Pins state should match");
        assertEquals(false, pin.getState(), "Pins should be false");
    }

    @Test
    @DisplayName("Disconnect Test")
    void pinTest5(){
        Pin pin = new Pin(0, null, true);
        Pin pin2 = new Pin(1, null, false);
        pin.connect(pin2);
        pin.disconnect();
        assertEquals(null, pin.getConnected(), "Pin 1 should disconnect");
        assertEquals(null, pin2.getConnected(), "Pin 2 should disconnect");
    }


}
