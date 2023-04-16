import java.util.UUID;

enum ComponentType{
    AND,
    OR,
    NOT
}

class App {
    private static App app = null;
    private static Simulation sim ;
    private static UI view = new RaylibUI();


    public static void main(String[] args) {
        view.load();
        while(!view.shouldClose()){
            step();
        }
        close();
        
        
    }

    public void debugSetup(){
        app.addComponent(ComponentType.AND, 100, 10);
        app.addComponent(ComponentType.OR, 100, 200);
        app.addComponent(ComponentType.NOT, 100, 400);
    }

// #~~~~~~~~~~~~~~~~~~~~~~Component

    public String addComponent(ComponentType type, double posX, double posY){
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
                System.err.println(type+": not an implemented component type");
                return null;
        }
        comp.setID(UUID.randomUUID().toString());
        sim.addComponent(comp);
        return comp.getID();
        
    }



// #~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Class
    public static App getInstanceApp() {
        if (app == null){
            app = new App();
            sim = new Simulation(8, 8);
        }
        return app;
    }

    public static Simulation getSimulation(){
        return sim;
    }


    public static void step(){
        view.render(getSimulation().getComponents());
        sim.step();
    }

    public static void close(){
        view.close();
    }

    private App(){
    }
}