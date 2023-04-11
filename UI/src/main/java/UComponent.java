import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class UComponent extends Group{
    private int size = 50;
    private int width = size*3;
    private double xDelta = 0;
    private double yDelta = 0;


    public UComponent(int input, int output, String color){
        int height = 0;
        if(input>output){
            height = input*size;
        }else{
            height = output*size;
        }

        Rectangle base = new Rectangle(width, height, Color.valueOf(color));
        base.setArcHeight(20);
        base.setArcWidth(20);

        base.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setCursor(Cursor.MOVE);
                event.consume();
            }
        });
        base.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setCursor(Cursor.DEFAULT);
                event.consume();
            }
        });
        base.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                xDelta = getLayoutX() - event.getX();
                yDelta = getLayoutY() - event.getY();
            }
            
        });
        base.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setTranslateX(event.getSceneX() + xDelta);
                setTranslateY(event.getSceneY() + yDelta);
                event.consume();
            }
        });
        this.getChildren().add(base);


        for (int i = 1; i <= input; i++) {
            DrawPin(0, i);
        }
        for (int i = 1; i <= output; i++) {
            DrawPin(width, i);
        }
    }

    public void DrawPin(double x, int i){
        UPin pin = new UPin(x, (i*size)-(size/2), size/3, Color.WHITE,"Test");
            this.getChildren().add(pin);
    }

}