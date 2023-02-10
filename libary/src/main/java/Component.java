public abstract class Component{

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
        for (int i = 0; i < output; i++) {
            this.outputPins[i] = new Pin();
        }
    }

    public abstract void run();

    public Pin getConnectedTo(int pin){ return this.outputPins[pin];}
    public String getName(){return this.name;}
    public void connect(int ID, Pin to){ this.outputPins[ID] = to;}
}
