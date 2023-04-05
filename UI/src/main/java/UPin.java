import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class UPin extends Region {
    
    String id;

    UPin(double x,double y,double r,Paint fill,String id){
        this.id = id;
        Circle e = new Circle(x, y, r, fill);
        this.getChildren().add(e);
    }
}
