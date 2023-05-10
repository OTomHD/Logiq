import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CommandHelper implements Runnable {

    private static CommandHelper helper = CommandHelper.getInstance();
    private Thread thread = null;

    private ArrayList<String> commandBuffer = new ArrayList<String>();

    public static Component findComponent(String name){
        Component component = null;
        for (Component potentialComponent : App.getInstanceApp().getSimulation().getComponents()) {
            if (name.equals(potentialComponent.getID())) {
                component = potentialComponent;
                return component;
            }
        }
        return component;
    }


    public static Pin findPin(String name, int pinIndex, PinType type){
        Pin pin = null;
        if(name.equals("Sim")) { // Simulation Pins
            if(type == PinType.INPUT){
                pin = App.getInstanceApp().getSimulation().getInPins()[pinIndex];
            }else{
                pin = App.getInstanceApp().getSimulation().getOutPins()[pinIndex];
            }
        }else{              // Component Pins
            Component component = findComponent(name);
            if(component == null){
                System.out.println("No component Found called: " + name);
                return pin;
            }
            if(type == PinType.INPUT){
                pin = component.getInPins()[pinIndex];
            }else{
                pin = component.getOutPins()[pinIndex];
            }
        }
        return pin;
    }

     private void parseCommands(){
        if (commandBuffer.isEmpty()) {
            return;
        }
        for (String stringCommand : commandBuffer) {
            String[] command = stringCommand.split(" ");

            Command cmd;
            switch (command[0].toLowerCase()) {
                case "toggle":
                    if (!(command.length == 2)) {
                        System.err.println("Wrong argument count, is:"+(command.length-1)+" Should be:1");
                        continue;
                    }

                    int simPinNumber;
                    try {
                        simPinNumber = Integer.parseInt(command[1]);
                    } catch (NumberFormatException e) {
                        System.err.println("[CommandHelper/toggle] - Incompatiable type for variable SimulationInputPinNumber");
                        continue;
                    }
                    if (simPinNumber>8 || simPinNumber<0) {
                        System.err.println("[CommandHelper/toggle] - No Simulation pin with id"+simPinNumber);
                        continue;
                    }
                    cmd = new ToggleCommand(simPinNumber);
                    break;




                case "add":
                    if (!(command.length == 5)) {
                        System.out.println("Wrong argument count, is:"+(command.length-1)+" Should be:4");
                        continue;
                    }

                    String componentName = command[1];

                    
                    ComponentType type;
                    try {
                        type = ComponentType.valueOf(command[2].toUpperCase());
                    } catch (Exception e) {
                        System.err.println("[CommandHelper/add] - invalid Argument ComponentType");
                        continue;
                    }
                    

                    int x;
                    try {
                        x = Integer.parseInt(command[3]);
                    } catch (NumberFormatException e) {
                        System.err.println("[CommandHelper/add] - Incompatiable type for variable ComponentX");
                        continue;
                    }
            

                    int y;
                    try {
                        y = Integer.parseInt(command[4]);
                    } catch (NumberFormatException e) {
                        System.err.println("[CommandHelper/add] - Incompatiable type for variable ComponentY");
                        continue;
                    }
                    cmd = new AddCommand(componentName, type, x, y);
                    break;




                case "connect":
                    if (!(command.length == 7)) {
                        System.err.println("Wrong argument count, is:"+(command.length-1)+" Should be:6");
                        continue;
                    }

                    String fromComponentName = command[1];
            
                    int fromPinNumber;
                    try {
                        fromPinNumber = Integer.parseInt(command[2]);
                    } catch (NumberFormatException e) {
                        System.err.println("[CommandHelper/connect] - Incompatiable type for variable FromPinNumber");
                        continue;
                    }
            
                    PinType fromPinType;
                    try {
                        fromPinType = PinType.valueOf(command[3].toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.err.println("[CommandHelper/connect] - Incompatiable type for variable ToPinNumber");
                        continue;
                    }
            
            
                    String toComponentName = command[4];
            
                    int toPinNumber;
                    try {
                        toPinNumber = Integer.parseInt(command[5]);
                    } catch (NumberFormatException e) {
                        System.err.println("[CommandHelper/connect] - Incompatiable type for variable ToPinNumber");
                        continue;
                    }
                    
                    PinType toPinType;
                    try {
                        toPinType = PinType.valueOf(command[6].toUpperCase());
                    } catch (IllegalArgumentException e) {
                    System.err.println("[CommandHelper/connect] - Incompatible type for variable ToPinType");
                    continue;
                    }

                    cmd = new ConnectCommand(fromComponentName, fromPinNumber, fromPinType, toComponentName, toPinNumber, toPinType);
                    break;




                case "move":
                    if (!(command.length == 4)) {
                        System.err.println("Wrong argument count, is:"+(command.length-1)+" Should be:3");
                        continue;
                    }


                    String componentNameMove = command[1];

                    int xMove;
                    try {
                        xMove = Integer.parseInt(command[2]);
                    } catch (NumberFormatException e) {
                        System.err.println("[CommandHelper/move] - Incompatiable type for variable ComponentX");
                        continue;
                    }
            
                    int yMove;
                    try {
                        yMove = Integer.parseInt(command[3]);
                    } catch (NumberFormatException e) {
                        System.err.println("[CommandHelper/move] - Incompatiable type for variable ComponentY");
                        continue;
                    }

                    cmd = new MoveCommand(componentNameMove, xMove, yMove);
                    break;
                
                case "save":
                    if (!(command.length == 2)) {
                        System.err.println("Wrong argument count, is:"+(command.length-1)+" Should be:1");
                        continue;
                    }

                    String filePathSave = command[1];

                    cmd = new SaveCommand(filePathSave);
                    break;

                case "load":
                    if (!(command.length == 2)) {
                        System.err.println("Wrong argument count, is:"+(command.length-1)+" Should be:1");
                        continue;
                    }

                    String filePathLoad = command[1];
                    
                    cmd = new LoadCommand(filePathLoad);
                    break;
                case "help":
                    System.out.println("toggle : Toggle a simulation input Pin (Index starts at 0)");
                    System.out.println("add : Adds a component to the simulation");
                    System.out.println("connect : connect two pins together (use Sim to access Simulation pins)");
                    System.out.println("move : Move a component to a new location in the simulation");
                    System.out.println("save : Save the current Simulation to file path");
                    System.out.println("load : Load and replace current Simulation from file path");

                    System.out.println("help : Shows this menu");
                    continue;
                default:
                    System.err.println("[CommandHelper] - "+command[0]+" Non-existant/Unimplemented command");
                    System.err.println("Use help to see available commands");
                    continue;
            }
            cmd.execute();
        }
        commandBuffer.clear();
    }

    @Override
    public void run() {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String stringCommand;
        while(!App.getInstanceApp().ShouldClose()){
            
            try{
                System.out.print(":> ");
                while (!input.ready() && !App.getInstanceApp().ShouldClose()) {
                    Thread.sleep(200);
                    parseCommands();
                }
                if (App.getInstanceApp().ShouldClose()) {
                    continue;
                }
                stringCommand = input.readLine();
                if(stringCommand.isBlank()){
                    continue;
                }
                commandBuffer.add(stringCommand);
            }catch(InterruptedException e){
                System.out.println("Closeing command line...");
                continue;
            }catch (IOException e) {
                System.err.println("[CommandHelper] - IOException");
                e.printStackTrace();
            }
            
        }
    }
//~~~~~~~~~~~~~~~~~~~Class~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public static CommandHelper getInstance(){
        if (helper == null){
            helper = new CommandHelper();
        }
        return helper;
    }

    private CommandHelper(){

    }

    public void setThread(Thread thread){
        this.thread = thread;
    }
    public Thread getThread(){
        return this.thread;
    }

    
}
