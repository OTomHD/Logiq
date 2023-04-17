public class Pin {
    boolean state;
    Pin connectedTo = null;
    Component parent;
    int index;

    Pin(int index,boolean state, Component parent){
        this.index = index;
        this.state = state;
        this.parent = parent;
    }
    Pin(int index,boolean state){
        this(index,state,null);
    }
    Pin(int index,Component parent){
        this(index,false, parent);
    }
    Pin(int index){
        this(index,false,null);
    }




    public void connect(Pin inputPin){
        connectedTo = inputPin;
        connectedTo.setState(this.getState());
    }

    public Pin getConnected(){
        return connectedTo;
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
    
    public void setState(boolean state) {
        this.state = state;
        if(!(connectedTo == null)){
            connectedTo.setState(state);
        }
    }
}