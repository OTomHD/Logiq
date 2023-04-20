import java.util.ArrayList;

public abstract class Command {
    private ArrayList<String> args;

    private static String sign = "Not set";
    private static String example = "Not set";

    public abstract void execute();

    public ArrayList<String> getArgs(){
        return args;
    }

    public static String getHelp(){
        String output="";
        output.concat(sign);
        output.concat(example);
        return output;
    }
}
