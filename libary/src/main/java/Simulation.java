import java.io.Serializable;
import java.util.ArrayList;

public class Simulation implements Serializable {
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
            component.run();
        }
    }

    public void addComponent(Component component){
        components.add(component);}
    
    public void subComponent(Component component){
        if(components.contains(component)){
            components.remove(component);
        }
    }
}
