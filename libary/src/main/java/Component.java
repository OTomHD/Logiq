public abstract class Component{

    public Pin[] inputPins;
    public Pin[] outputPins;

    public double posX;
    public double posY;
    
    private String id=null;

    Component(int input, int output){
        posX = 0;
        posY = 0;
        this.inputPins = new Pin[input];
        this.outputPins = new Pin[output];
        for (int i = 0; i < input; i++) {
            this.inputPins[i] = new Pin();
        }
        for (int i = 0; i < output; i++) {
            this.outputPins[i] = new Pin();
        }
    }

    Component(int input, int output,double x, double y){
        posX = x;
        posY = y;
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


    public Pin[] getOutPins(){
        return outputPins;
    }
    public Pin[] getInPins(){
        return inputPins;
    }

    public void setX(double x){ posX = x; }
    public void setY(double y){ posY = y; }
    public double getX(){ return posX; }
    public double getY(){ return posY; }

    public void setID(String newID){ id = newID; }
    public String getID(){ return id; }
}
