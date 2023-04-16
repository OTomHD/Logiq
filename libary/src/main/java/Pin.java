public class Pin {
    boolean state;
    Pin connectedTo = null;

    Pin(){
        this.state = false;
    }
    Pin(boolean state){
        this.state = state;
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
    
    public void setState(boolean state) {
        this.state = state;
        if(!(connectedTo == null)){
            connectedTo.setState(state);
        }
    }
}