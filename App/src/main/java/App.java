import java.util.ArrayList;

class App {
    private static App app = null;
    private static Simulation sim ;
    private static long lastStep;
    private static boolean NotClosed;

    public static void main(String[] args) {
        app = getInstanceApp();

        sim = new Simulation(1, 2);
        if (args.length > 1) {
            sim = new Simulation(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        }

<<<<<<< HEAD
        boolean[] input;
=======
>>>>>>> 77d527e866c90e6c5d71d58b998408f10fd73ba2

        app.addComponent("node");
        app.connect(0, 0, 0, true); // test node
        app.connect(0, 0, 0, false);
        app.connect(1, 0, 0, false);
        app.sim.components.clear();

        app.addComponent("and");
        app.sim.components.clear();

        app.addComponent("or");
        app.sim.components.clear();

        app.addComponent("not");
        app.connect(0, 0, 0, true); // test Not
        app.connect(0, 0, 0, false);
        app.sim.components.clear();

        input = (true,false);
        for (int i = 0; i < sim.inputPins.length; i++) {
            app.simulationSetPin(i, input[i]);
        }
        app.step();
        for (int i = 0; i < sim.outputPins.length; i++) {
            System.out.print(app.getOutPin(i) + ", ");
        }

//        ui.start();
//
//        while (NotClosed) {
//            app.step();
//        }
    }

// #~~~~~~~~~~~~~~~~~~~~~~Component
    public void componentSetPin(int compID, int pinID, boolean value){
        if (!checkValidComponent(compID, sim.components)){ return; }
        if (!checkValidPin(pinID, sim.components.get(compID).inputPins)){ return;}

        sim.components.get(compID).inputPins[pinID].setState(value);
    }

    public void addComponent(String Type){
        Component comp;
        switch (Type) {
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
                System.err.print(Type+": not an implemented component type");
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
<<<<<<< HEAD
        //ui.render();
=======
>>>>>>> 77d527e866c90e6c5d71d58b998408f10fd73ba2
    }

    private App(){
        NotClosed = true;
    }
}