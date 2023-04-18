import java.util.ArrayList;

public abstract class Command {
    private ArrayList<String> args;


    public abstract void execute();

    public ArrayList<String> getArgs(){
        return args;
    }
}
