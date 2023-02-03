public class Component{

    String name;
    Pin[] inputPins;
    Pin[] outputPins;

    Component(String name, int input, int output){
        this.name = name;
        this.inputPins = new Pin[input];
        this.outputPins = new Pin[output];
        for (int i = 0; i < input; i++) {
            this.outputPins[i] = new Pin();
        }
    }

    public void run() {
        boolean value = inputPins[0].getState();
        for (Pin pin:outputPins) {
            if (pin == null){
                System.err.println("Null Pin");
                return;
            }
            pin.setState(value);
        }
    }

    public Pin getConnectedTo(int pin){
        return this.outputPins[pin];
    }
    public String getName(){return this.name;}

    public void connect(int ID, Pin to){
        this.outputPins[ID] = to;
    }
}
