public abstract class Component{

    public Pin[] inputPins;
    public Pin[] outputPins;

    public int posX;
    public int posY;
    
    private String id=null;

    Component(int input, int output,int x, int y){
        posX = x;
        posY = y;
        this.inputPins = new Pin[input];
        this.outputPins = new Pin[output];
        for (int i = 0; i < input; i++) {
            this.inputPins[i] = new Pin(i,this);
        }
        for (int i = 0; i < output; i++) {
            this.outputPins[i] = new Pin(i,this);
        }
    }

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

    public void setX(int x){ posX = x; }
    public void setY(int y){ posY = y; }
    public int getX(){ return posX; }
    public int getY(){ return posY; }

    public void setID(String newID){ id = newID; }
    public String getID(){ return id; }
}
