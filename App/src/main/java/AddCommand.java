public class AddCommand extends Command {

    String componentName;
    ComponentType type;
    int x;
    int y;

    public AddCommand(String name, ComponentType type, int xPos, int yPos){
        super();
        this.componentName = name;
        this.type = type;
        this.x = xPos;
        this.y = yPos;

    }

    @Override
    public void execute() {
        App.getInstanceApp().addComponent(type, x, y, componentName);
    }

    
}
