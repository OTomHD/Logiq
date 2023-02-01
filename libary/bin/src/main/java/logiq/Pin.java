package logiq;
public class Pin {
    boolean HIGH;

    Pin(){
        this.HIGH = false;
    }
    Pin(boolean state){
        this.HIGH = state;
    }

    public boolean isHIGH() {
        return HIGH;
    }
    public void setState(boolean state) {
        this.HIGH = state;
    }
}