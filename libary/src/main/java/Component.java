import java.io.Serializable;

public abstract class Component implements Serializable, Runnable{

    private Pin[] inputPins;
    private Pin[] outputPins;

    private Point position;
    private String id=null;
    private ComponentType compType;

    Component(int input, int output,int x, int y, ComponentType type){
        this.position = new Point(x, y);

        this.inputPins = new Pin[input];
        this.outputPins = new Pin[output];
        this.compType = type;
        for (int i = 0; i < input; i++) {
            this.inputPins[i] = new Pin(i,PinType.INPUT,this);
        }
        for (int i = 0; i < output; i++) {
            this.outputPins[i] = new Pin(i,PinType.OUTPUT,this);
        }
    }

    @Override
    public abstract void run();

    public int largestPinArray(){
        return inputPins.length > outputPins.length ? inputPins.length : outputPins.length;
    }

    public Pin[] getOutPins(){
        return outputPins;
    }
    public Pin[] getInPins(){
        return inputPins;
    }

    public Point getPosition(){
        return position;
    }

    public ComponentType getComponentType(){
        return compType;
    }

    public void setID(String newID){ id = newID; }
    public String getID(){ return id; }
}
