public class saveCommand extends Command{
    String sign = "CMD -> String\n";
    String example = "save FilePath - example\n";

    String filePath;

    public saveCommand(String filePath){
        this.filePath = filePath;
    }

    @Override
    public void execute() {
        App.getInstanceApp().save(filePath);
    }
    
}
