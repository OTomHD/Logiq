/**
 * CNode
 */
public class CNode extends Component{

    CNode(String name,int output) {
        super(name, 1, output);
    }

    @Override
    public void run() {
        boolean value = inputPins[0].getState();
        for (Pin pin:outputPins) {
            if (pin == null){
                System.err.println("Null Pin");
                return;
            }
            pin.setState(value);
        }
    }
}