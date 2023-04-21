public class AddCommand extends Command {
    String sign = "CMD -> String -> String -> int -> int\n";
    String example = "add ComponentName ComponentType ComponentX ComponentY - Example\n";


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
