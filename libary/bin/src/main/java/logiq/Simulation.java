package logiq;
import java.util.ArrayList;

public class Simulation {
    Pin[] inputPins;
    ArrayList<Component> components = new ArrayList<Component>();
    Pin[] outputPins;

    Simulation(int inSize, int outSize){

        this.inputPins = new Pin[inSize];
        for (int index = 0; index < inputPins.length; index++) {
            inputPins[index] = new Pin();
        }

        this.outputPins = new Pin[outSize];
        for (int index = 0; index < outputPins.length; index++) {
            outputPins[index] = new Pin();
        }
        
    }

    public void step(){
        for (Component component:components) {
            Thread thread = new Thread(component);
            thread.start();
        }
    }

    public void addComponent(Component component){
        components.add(component);}

    public void setPin(int id, boolean state){
        this.inputPins[id].setState(state);
    }

    public boolean[] getInPins(){
        int size = inputPins.length;
        boolean[] output = new boolean[size];
        for (int i = 0; i < output.length; i++) {
            output[i] = inputPins[i].isHIGH();
        }
        return output;
    }

    public boolean[] getOutPins(){
        int size = outputPins.length;
        boolean[] output = new boolean[size];
        for (int i = 0; i < output.length; i++) {
            output[i] = outputPins[i].isHIGH();
        }
        return output;
    }
}
