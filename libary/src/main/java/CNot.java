public class CNot extends Component{

    CNot() {
        super(1, 1);
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
