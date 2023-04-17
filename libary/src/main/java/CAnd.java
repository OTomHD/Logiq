public class CAnd extends Component {

    CAnd(int x, int y) {
        super(2, 1,x, y, ComponentType.AND);
    }

    @Override
    public void run() {
        boolean value1 = getInPins()[0].getState();
        boolean value2 = getInPins()[1].getState();
        if (value1 == true && value2 == true){
            getOutPins()[0].setState(true);
            return;
        }
        getOutPins()[0].setState(false);
    }
    
}
