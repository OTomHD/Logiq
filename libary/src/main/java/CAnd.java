public class CAnd extends Component {

    CAnd(int x, int y) {
        super(2, 1,x, y);
    }

    @Override
    public void run() {
        boolean value1 = inputPins[0].getState();
        boolean value2 = inputPins[1].getState();
        if (value1 == true && value2 == true){
            outputPins[0].setState(true);
            return;
        }
        outputPins[0].setState(false);
    }
    
}
