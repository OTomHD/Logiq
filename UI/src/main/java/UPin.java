import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class UPin extends Region {
    
    String id;

    UPin(double x,double y,double r,Paint fill,String id){
        this.id = id;
        
        Circle e = new Circle(x, y, r, fill);
        e.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Pin Drag");
                event.consume();
            }
        });
        e.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Pin Enter");
                setCursor(Cursor.OPEN_HAND);
            }
            
        });
        e.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Pin Exit");
                setCursor(Cursor.MOVE);
            }
            
        });
        this.getChildren().add(e);
    }

    
}
