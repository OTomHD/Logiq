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

/*     private void parseCommands(){
        if (newCommmands.isEmpty()) {
            return;
        }
        for (String stringCommand : newCommmands) {
            
        }
    } */

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
                System.err.println("IOException");
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
