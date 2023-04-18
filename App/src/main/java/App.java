import java.util.UUID;

class App {
    private static App app = App.getInstanceApp();
    private static Simulation sim ;
    private static UI view = new RaylibUI();
    private static CommandHelper helper = CommandHelper.getInstance();
    private static boolean shouldClose = false;


    public static void main(String[] args) {
        view.load();
        app.debugSetup();
        helper.setThread(new Thread(helper)); 
        helper.getThread().start();
        while(!view.shouldClose() && !shouldClose){
            step();
        }
        internalClose();
        
        
    }

    public void debugSetup(){
        app.addComponent(ComponentType.OR, 100, 10); // 0
        app.addComponent(ComponentType.OR, 100, 200); // 1
        app.addComponent(ComponentType.NOT, 100, 400);// 2
        

        app.addComponent(ComponentType.AND, 500, 10); // 3
        app.addComponent(ComponentType.OR, 500, 200); // 4
        app.addComponent(ComponentType.OR, 500, 400); // 5

        app.addComponent(ComponentType.NOT, 100, 600); //6

        app.addComponent(ComponentType.AND, 800, 10);  //7
        app.addComponent(ComponentType.AND, 800, 200); //8
        app.addComponent(ComponentType.NOT, 800, 400); // 9

        getSimulation().getInPins()[0].connect(getSimulation().getComponents().get(0).getInPins()[0]);
        getSimulation().getInPins()[1].connect(getSimulation().getComponents().get(0).getInPins()[1]);
        getSimulation().getInPins()[2].connect(getSimulation().getComponents().get(1).getInPins()[0]);
        getSimulation().getInPins()[3].connect(getSimulation().getComponents().get(1).getInPins()[1]);
        getSimulation().getInPins()[4].connect(getSimulation().getComponents().get(2).getInPins()[0]);

        getSimulation().getInPins()[0].setState(true);
        getSimulation().getInPins()[1].setState(true);
        getSimulation().getInPins()[2].setState(true);
        getSimulation().getInPins()[3].setState(true);
        getSimulation().getInPins()[4].setState(false);


        getSimulation().getComponents().get(1).getOutPins()[0].connect(getSimulation().getComponents().get(3).getInPins()[1]);
        getSimulation().getComponents().get(0).getOutPins()[0].connect(getSimulation().getComponents().get(3).getInPins()[0]);
        getSimulation().getComponents().get(2).getOutPins()[0].connect(getSimulation().getComponents().get(4).getInPins()[0]);
        getSimulation().getComponents().get(6).getOutPins()[0].connect(getSimulation().getComponents().get(5).getInPins()[1]);

        getSimulation().getComponents().get(3).getOutPins()[0].connect(getSimulation().getComponents().get(7).getInPins()[0]);
        getSimulation().getComponents().get(4).getOutPins()[0].connect(getSimulation().getComponents().get(8).getInPins()[1]);
        getSimulation().getComponents().get(5).getOutPins()[0].connect(getSimulation().getComponents().get(9).getInPins()[0]);

        getSimulation().getComponents().get(7).getOutPins()[0].connect(getSimulation().getOutPins()[2]);
        getSimulation().getComponents().get(8).getOutPins()[0].connect(getSimulation().getOutPins()[3]);
        getSimulation().getComponents().get(9).getOutPins()[0].connect(getSimulation().getOutPins()[4]);

        
        
        
    }



//~~~~~~~~~~~~~~~~~~~~~~Commands~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public void toggle(Pin pin){
        pin.setState(!pin.getState());
    }

    public String addComponent(ComponentType type, int posX, int posY){
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
        comp.setID(UUID.randomUUID().toString());
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
    public static Simulation getSimulation(){
        return sim;
    }


    private static void step(){
        sim.step();
        view.render(getSimulation().getComponents(),getSimulation().getInPins(),getSimulation().getOutPins());
        
    }

//~~~~~~~~~~~~~Class~~~~~~~~~~~~~~~~~//

    public boolean ShouldClose(){
        return shouldClose;
    }

    private static void internalClose(){
        shouldClose = true;
        view.close();
        helper.printAllCommands();
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