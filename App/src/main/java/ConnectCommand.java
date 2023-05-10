public class ConnectCommand extends Command {

    String fromComponentName;
    String toComponentName;
    int fromPinNumber;
    int toPinNumber;
    PinType fromPinType;
    PinType toPinType;

    public ConnectCommand(String fromComponentName, int fromPinNumber, PinType fromPinType, String toComponentName, int toPinNumber, PinType toPinType){
        super();
        this.fromComponentName = fromComponentName;
        this.fromPinNumber = fromPinNumber;
        this.fromPinType = fromPinType;

        this.toComponentName = toComponentName;
        this.toPinNumber = toPinNumber;
        this.toPinType = toPinType;


    }

    
    @Override
    public void execute() {
        Pin fromPin = CommandHelper.findPin(fromComponentName, fromPinNumber, fromPinType);
        Pin toPin = CommandHelper.findPin(toComponentName, toPinNumber, toPinType);
        if(fromPin == null || toPin == null){
            System.err.println("One pin was null");
            return;
        }
        App.getInstanceApp().connect(fromPin, toPin);
    }
    
}
