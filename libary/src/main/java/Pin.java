public class Pin {
    boolean state;

    Pin(){
        this.state = false;
    }
    Pin(boolean state){
        this.state = state;
    }

    public boolean getState() {
        return state;
    }
    public void setState(boolean state) {
        this.state = state;
    }
}