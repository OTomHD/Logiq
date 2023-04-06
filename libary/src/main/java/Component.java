public abstract class Component{

    Pin[] inputPins;
    Pin[] outputPins;

    Component(int input, int output){
        this.inputPins = new Pin[input];
        this.outputPins = new Pin[output];
        for (int i = 0; i < input; i++) {
            this.inputPins[i] = new Pin();
        }
        for (int i = 0; i < output; i++) {
            this.outputPins[i] = new Pin();
        }
    }

    public abstract void run();

    public Pin getConnectedTo(int pin){ return this.outputPins[pin];}
    public void connect(int ID, Pin to){ this.outputPins[ID] = to;}
}
