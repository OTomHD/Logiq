import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ComponentTEST {
    @Test
    @DisplayName("Componet Test")
    void compTest1(){
        Component comp = new CAnd(0,0);
        for (Pin pin : comp.getInPins()) {
            assertEquals(false, pin.getState(),"all input pins should be false");
        }
        for (Pin pin : comp.getOutPins()) {
            assertEquals(false, pin.getState(),"all output pins should be false");
        }
    }

    @Test
    @DisplayName("And Test")
    void compTest2(){
        Component comp = new CAnd(0,0);
        assertEquals(false, comp.getOutPins()[0].getState(),"output pin should be false");
        comp.getInPins()[0].setState(true);
        comp.run();
        assertEquals(false,comp.getOutPins()[0].getState(), "output pin should be false");
        comp.getInPins()[1].setState(true);
        comp.run();
        assertEquals(true,comp.getOutPins()[0].getState(), "output pin should be true");
    }

    @Test
    @DisplayName("Or Test")
    void compTest3(){
        Component comp = new COr(0,0);
        assertEquals(false, comp.getOutPins()[0].getState(),"output pin should be false");
        comp.getInPins()[0].setState(true);
        comp.run();
        assertEquals(true,comp.getOutPins()[0].getState(), "output pin should be false");
        comp.getInPins()[1].setState(true);
        comp.run();
        assertEquals(true,comp.getOutPins()[0].getState(), "output pin should be true");
    }

    @Test
    @DisplayName("Not Test")
    void compTest4(){
        Component comp = new CNot(0,0);
        comp.run();
        assertEquals(true, comp.getOutPins()[0].getState(),"output pin should be true");
        comp.getInPins()[0].setState(true);
        comp.run();
        assertEquals(false,comp.getOutPins()[0].getState(), "output pin should be false");
    }

}
