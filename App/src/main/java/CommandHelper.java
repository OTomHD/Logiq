import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class CommandHelper implements Runnable {

    private static CommandHelper helper = CommandHelper.getInstance();
    private Thread thread = null;

    private Scanner input = new Scanner(System.in);
    private ArrayList<String> newCommmands = new ArrayList<String>();

    public void printAllCommands(){
        for (String command : newCommmands) {
            System.out.println(command);
        }
    }

     private void parseCommands(){
        if (newCommmands.isEmpty()) {
            return;
        }
        for (String stringCommand : newCommmands) {
            String[] command = stringCommand.split(" ");
            Command cmd;
            switch (command[0].toLowerCase()) {
                case "toggle":
                    if(!(command.length == 2)){
                        System.out.println("Incorrect amount of arguments for connect command needed 1");
                        System.out.println("Example - toggle SimulationInputPinNumber");
                        return;
                    }
                    cmd = new ToggleCommand();
                    break;

                case "add":
                    if(!(command.length == 5)){
                        System.out.println("Incorrect amount of arguments for add command needed 4");
                        System.out.println("Example - add ComponentName ComponentType ComponentX ComponentY");
                        return;
                    }
                    cmd = new AddCommand();
                    break;

                case "connect":
                    if(!(command.length == 5)){
                        System.out.println("Incorrect amount of arguments for connect command needed 4");
                        System.out.println("Example - connect ComponentFromName FromPinNumber ComponentToName ToPinNumber");
                        return;
                    }
                    cmd = new ConnectCommand();
                    break;

                case "move":
                    if(!(command.length == 4)){
                        System.out.println("Incorrect amount of arguments for move command needed 3");
                        System.out.println("Example - move ComponentName ComponentX ComponentY");
                        return;
                    }
                    cmd = new MoveCommand();
                    break;
                    
                default:
                    System.err.println("[CommandHelper] - "+command[0]+" Non-existant/Unimplemented command");
                    break;
            }
        }
    }

    @Override
    public void run() {
        while(!App.getInstanceApp().ShouldClose()){
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            String stringCommand;
            try{
                while (!input.ready() && !App.getInstanceApp().ShouldClose()) {
                    Thread.sleep(200);
                }
                stringCommand = input.readLine();
                if(stringCommand.isBlank()){
                    continue;
                }
                newCommmands.add(stringCommand);
                System.out.println("New Command Added");
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
