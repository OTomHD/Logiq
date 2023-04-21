public class LoadCommand extends Command {
    String sign = "";
    String example = "";

    String filePath;

    public LoadCommand(String filePath){
        this.filePath = filePath;
    }

    @Override
    public void execute() {
        App.getInstanceApp().load(filePath);
    }
    
}
