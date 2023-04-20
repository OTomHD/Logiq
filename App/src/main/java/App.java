import java.lang.Thread.State;

class App {
    private static App app = App.getInstanceApp();
    private static Simulation sim ;
    private static UI view = new RaylibUI();
    private static CommandHelper helper = CommandHelper.getInstance();
    private static boolean shouldClose = false;


    public static void main(String[] args) {
        view.load();
        helper.setThread(new Thread(helper)); 
        while(!view.shouldClose() && !shouldClose){
            step();
            if(helper.getThread().getState() == State.NEW || helper.getThread().getState() == State.TERMINATED){
                helper.getThread().start();
            }
        }
        internalClose();
    }

//~~~~~~~~~~~~~~~~~~~~~~Commands~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public void toggle(int pinIndex){
        getSimulation().getInPins()[pinIndex].setState(!getSimulation().getInPins()[pinIndex].getState());
    }

    public String addComponent(ComponentType type, int posX, int posY, String name){
        Component comp;;
        switch (type) {
            case AND:
                comp = new CAnd(posX, posY);
                break;
            case OR:
                comp = new COr(posX, posY);
                break;
            case NOT:
                comp = new CNot(posX, posY);
                break;
            default:
                System.err.println("[App] - "+type+": not an implemented component type");
                return null;
        }
        comp.setID(name);
        sim.addComponent(comp);
        return comp.getID();
        
    }

    public void connect(Pin pin1, Pin pin2){
        if(pin1.getType() == PinType.INPUT){
            pin1.connect(pin2);
        }else{
            pin2.connect(pin1);
        }
    }


    public void moveComponent(Component component, int newXPos, int newYPos){
        component.getPosition().setX(newXPos);
        component.getPosition().setY(newYPos);
    }

//~~~~~~~~~~~~Simulation~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public Simulation getSimulation(){
        return sim;
    }


    private static void step(){
        sim.step();
        view.render(app.getSimulation().getComponents(),app.getSimulation().getInPins(),app.getSimulation().getOutPins());
        
    }

//~~~~~~~~~~~~~Class~~~~~~~~~~~~~~~~~//

    public boolean ShouldClose(){
        return shouldClose;
    }

    private static void internalClose(){
        shouldClose = true;
        view.close();
        helper.getThread().interrupt();
    }

    public void close(){
        shouldClose = true;
    }

    public static App getInstanceApp() {
        if (app == null){
            app = new App();
        }
        return app;
    }

    private App(){
        sim =  new Simulation(8, 8);
    }
}