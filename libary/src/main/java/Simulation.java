import java.io.Serializable;
import java.util.ArrayList;

public class Simulation implements Serializable {
    Pin[] inputPins;
    ArrayList<Component> components = new ArrayList<Component>();
    Pin[] outputPins;

    Simulation(int inSize, int outSize){

        this.inputPins = new Pin[inSize];
        for (int index = 0; index < inputPins.length; index++) {
            inputPins[index] = new Pin(index, PinType.INPUT);
        }

        this.outputPins = new Pin[outSize];
        for (int index = 0; index < outputPins.length; index++) {
            outputPins[index] = new Pin(index, PinType.OUTPUT);
        }
        
    }

    public Pin[] getInPins(){
        return inputPins;
    }
    public Pin[] getOutPins(){
        return outputPins;
    }

    public ArrayList<Component> getComponents(){
        return this.components;
    }

    public void step(){
        ArrayList<Thread> threadList =  new ArrayList<Thread>();
        for (Component component:components) {
            Thread thread = new Thread(component);
            thread.start();
            threadList.add(thread);
        }
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Component Thread was Interrupted");
                e.printStackTrace();
            }
        }

    }

    public void addComponent(Component component){
        components.add(component);}
    
    public void removeComponent(Component component){
        if(components.contains(component)){
            components.remove(component);
        }else{
            System.err.println("[Simulation] ~ Component not in component List");
        }
    }
}
