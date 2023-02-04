public class COr extends Component {

    COr(String name) {
        super(name, 2, 1);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void run() {
        boolean value1 = inputPins[0].getState();
        boolean value2 = inputPins[1].getState();
        if (value1 == true || value2 == true){
            outputPins[0].setState(true);
            return;
        }
        outputPins[0].setState(false);
        
    }
    
}
