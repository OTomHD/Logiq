import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SimulationTEST {
    
    @Test
    @DisplayName("Simulation Pin Test")
    void simTest1(){
        Simulation sim = new Simulation(8, 8);
        for (Pin pin : sim.getInPins()) {
            assertEquals(false, pin.getState(),"All Simulation Input pins must be false");
        }
        for (Pin pin : sim.getOutPins()) {
            assertEquals(false, pin.getState(),"All simulation output pins must be false");
        }
    }

    @Test
    @DisplayName("Simulation add and remove component test")
    void simTest2(){
        Simulation sim = new Simulation(8, 8);
        Component comp = new CAnd(0, 0);
        sim.addComponent(comp);
        assertTrue(sim.getComponents().contains(comp), "Simulation should contain Component");
        sim.removeComponent(comp);
        assertFalse(sim.getComponents().contains(comp), "Simulation should not contain Component");
    }

}
