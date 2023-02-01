public class Component implements Runnable{

    String name;
    Pin[] inputPins;
    Pin[] outputPins;

    Component(String name, int input, int output){
        this.name = name;
        this.inputPins = new Pin[input];
        this.outputPins = new Pin[output];
        for (int i = 0; i < input; i++) {
            this.inputPins[i] = new Pin();
        }
    }

    @Override
    public void run() {
        boolean value = inputPins[0].isHIGH();
        for (Pin pin:outputPins) {
            if (pin == null){
                return;
            }
            pin.setState(value);
        }
    }

    public Pin getConnectedTo(int pin){
        return this.outputPins[pin];
    }
    public String getName(){return this.name;}

    public void connect(int from, Pin to){
        this.outputPins[from] = to;
    }
}
