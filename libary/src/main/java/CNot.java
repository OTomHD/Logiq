public class CNot extends Component{

    CNot(String name) {
        super(name, 1, 1);
    }

    @Override
    public void run() {
        boolean value = inputPins[0].getState();
        if (value) {
            outputPins[0].setState(false);
        }
        outputPins[0].setState(false);
        
    }
    
}
