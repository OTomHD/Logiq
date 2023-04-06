import java.util.ArrayList;



class App {
    private static App app = null;
    private Simulation sim ;
    private static long lastStep;


// #~~~~~~~~~~~~~~~~~~~~~~Component
    public void componentSetPin(int compID, int pinID, boolean value){
        if (!checkValidComponent(compID, sim.components)){ return; }
        if (!checkValidPin(pinID, sim.components.get(compID).inputPins)){ return;}

        sim.components.get(compID).inputPins[pinID].setState(value);
    }

    public void addComponent(String type){
        Component comp;;
        switch (type.toLowerCase()) {
            case "node":
                comp = new CNode("NODE", 2);
                break;
            case "and":
                comp = new CAnd("AND");
                break;
            case "or":
                comp = new COr("OR");
                break;
            case "not":
                comp = new CNot("NOT");
                break;
            default:
                System.err.println(type+": not an implemented component type");
                return;
        }

        sim.addComponent(comp);
        
    }

    public void connect(int fromCompID, int fromPinID, int toCompID, int toPinID){
        sim.components.get(toCompID).inputPins[toPinID] = sim.components.get(fromCompID).outputPins[fromPinID];
    }

    public void connect(int simPinID, int compID,int compPinID, boolean input){
        if(input){
            sim.components.get(compID).inputPins[compPinID] = sim.inputPins[simPinID];
            return;
        }
        sim.outputPins[simPinID] = sim.components.get(compID).outputPins[compPinID];
    }


// #~~~~~~~~~~~~~~~~~~~Pins
    public void simulationSetPin(int id, boolean value){
        if (checkValidPin(id, sim.inputPins)) {
            sim.setPin(id, value);
        }
        return;
    }

    public Boolean getOutPin(int id){
        if (checkValidPin(id, sim.outputPins)) {
            return sim.outputPins[id].getState();
        }
        return null;
    }

    public Boolean getInPin(int id){
        if (checkValidPin(id, sim.inputPins)) {
            return sim.inputPins[id].getState();
        }
        return null;
    }

    public ArrayList<Component> getComponents(){
        return sim.components;
    }

    private boolean checkValidPin(int id, Pin[] pins){
        if (id >= pins.length){
            System.err.println("Not valid Pin ID: " + id);
            return false;
        }
        return true;
    }

    private boolean checkValidComponent(int id, ArrayList<Component> components){
        if (id >= components.size()){
            return false;
        }
        return true;
    }
// #~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Class
    public static App getInstanceApp() {
        if (app == null){
            app = new App();
        }
        app.sim = new Simulation(1, 1);
        return app;
    }


    public void step(){
        sim.step();
    }

    private App(){
    }
}