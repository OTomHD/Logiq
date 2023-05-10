public class SaveCommand extends Command{

    String filePath;

    public SaveCommand(String filePath){
        this.filePath = filePath;
    }

    @Override
    public void execute() {
        App.getInstanceApp().save(filePath);
    }
    
}
