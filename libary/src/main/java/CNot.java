public class CNot extends Component{

    CNot(int x, int y) {
        super(1, 1, x , y, ComponentType.NOT);
    }

    @Override
    public void run() {
        boolean value = getInPins()[0].getState();
        getOutPins()[0].setState(!(value));   
    }
    
}
