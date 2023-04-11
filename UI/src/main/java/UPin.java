import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class UPin extends Circle {
    
    String id;

    UPin(double x,double y,double r,Paint fill,String id){
        super(x, y, r, fill);
        this.id = id;
        setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Pin Drag");
                event.consume();
            }
        });
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Pin Enter");
                setCursor(Cursor.OPEN_HAND);
                event.consume();
            }
            
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Pin Exit");
                setCursor(Cursor.MOVE);
                event.consume();
            }
            
        });
    }

    
}
