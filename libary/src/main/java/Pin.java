public class Pin {
    boolean state;
    Pin connectedTo = null;
    Component parent;

    Pin(boolean state, Component parent){
        this.state = state;
        this.parent = parent;
    }
    Pin(boolean state){
        this(state,null);
    }
    Pin(Component parent){
        this(false, parent);
    }
    Pin(){
        this(false,null);
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
    
    public void setState(boolean state) {
        this.state = state;
        if(!(connectedTo == null)){
            connectedTo.setState(state);
        }
    }
}