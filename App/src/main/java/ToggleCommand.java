public class ToggleCommand extends Command {
    String sign = "CMD -> int\n";
    String example = "toggle SimulationPinNumber - Example\n";


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
