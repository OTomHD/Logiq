public class CNot extends Component{

    CNot(int x, int y) {
        super(1, 1, x , y);
    }

    @Override
    public void run() {
        boolean value = inputPins[0].getState();
        if (value) {
            outputPins[0].setState(false);
            return;
        }
        outputPins[0].setState(true);
        
    }
    
}
