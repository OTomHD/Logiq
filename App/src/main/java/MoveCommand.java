public class MoveCommand extends Command {
    String sign = "CMD -> String -> int -> int\n";
    String example = "move ComponentName ComponentX ComponentY - Example\n";


    String componentName;
    int x;
    int y;

    public MoveCommand(String name, int xPos, int yPos){
        super();
        this.componentName = name;
        this.x = xPos;
        this.y = yPos;
    }


    @Override
    public void execute() {
        Component component = CommandHelper.findComponent(componentName);
        if(component == null){
            System.err.println("[MoveCommand] - Component was null");
            return;
        }
        component.getPosition().setX(x);
        component.getPosition().setY(y);
    }

    
}
