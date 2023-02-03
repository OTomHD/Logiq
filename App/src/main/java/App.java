import java.util.ArrayList;

class App {
    private static App app = null;
    private static Simulation sim ;
    private static long lastStep;
    private static UI ui;
    private static boolean NotClosed;

    public static void main(String[] args) {
        app = getInstanceApp();

        sim = new Simulation(4, 4);
        if (args.length > 1) {
            sim = new Simulation(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        }

        ui.start();

        while (NotClosed) {
            app.step();
        }
    }

// #~~~~~~~~~~~~~~~~~~~~~~Component
    public void componentSetPin(int compID, int pinID, boolean value){
        if (!checkValidComponent(compID, sim.components)){ return; }
        if (!checkValidPin(pinID, sim.components.get(compID).inputPins)){ return;}

        sim.components.get(compID).inputPins[pinID].setState(value);
    }

    public void addComponent(String name, int in, int out){
        Component comp = new Component(name, in, out);
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
        return app;
    }

    public void close(){
        NotClosed = false;
    }

    private void step(){
        if((System.currentTimeMillis() - lastStep) >= 1000) {
            sim.step();
            lastStep = System.currentTimeMillis();
        }
        ui.render();
    }

    private App(){
        NotClosed = true;
    }
}