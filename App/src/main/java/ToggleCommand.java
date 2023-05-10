public class ToggleCommand extends Command {

    int simPinNumber;

    public ToggleCommand(int simPinNumber){
        super();
        this.simPinNumber = simPinNumber;
    }



    @Override
    public void execute() {
        App.getInstanceApp().toggle(simPinNumber);
    }
    
}
