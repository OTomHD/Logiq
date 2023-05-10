import java.io.Serializable;

public class Pin implements Serializable{
    boolean state;
    Pin connectedTo = null;
    Component parent;
    int index;
    Point position;
    PinType pinType;


    Pin(int index,PinType type,boolean state, Component parent){
        this.index = index;
        this.state = state;
        this.parent = parent;
        this.pinType = type;
        int x = this.pinType==PinType.INPUT ? 0 : 1;
        int y = this.index+1;
        position =  new Point(x, y);
    }
    Pin(int index,PinType type,boolean state){
        this(index,type,state,null);
    }
    Pin(int index,PinType type,Component parent){
        this(index,type,false, parent);
    }
    Pin(int index,PinType type){
        this(index,type,false,null);
    }


    public void connect(Pin inputPin){
        if (!(connectedTo == null)) {
            disconnect();
        }
        setConnected(inputPin);
        connectedTo.setConnected(this);
        connectedTo.setState(this.getState());
    }

    public void disconnect(){
        connectedTo.setState(false);
        connectedTo.setConnected(null);
        setConnected(null);
    }

    public Pin getConnected(){
        return connectedTo;
    }
    
    public void setConnected(Pin pin){
        connectedTo = pin;
    }

    public boolean getState() {
        return state;
    }

    public Component getParent(){
        return parent;
    }

    public int getIndex(){
        return index;
    }
    
    public void setState(boolean state, int... r) {
        this.state = state;
        if(!(connectedTo == null) && !(r.length>0)){
            connectedTo.setState(state, 1);
        }
    }

    public Point getPosition(){
        return position;
    }

    public PinType getType(){
        return pinType;
    }
}