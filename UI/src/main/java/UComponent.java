import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class UComponent extends Region{
    private int size = 25;


    public UComponent(int input, int output){
        int height = 0;
        if(input>output){
            height = input*size;
        }else{
            height = output*size;
        }

        this.getChildren().add(new Rectangle(75, height, Color.AQUA));

        for (int i = 1; i <= input; i++) {
            DrawPin(0, i);
        }

        for (int i = 1; i <= output; i++) {
            DrawPin(75, i);
        }

        setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                setCursor(Cursor.MOVE);
            }
            
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                setCursor(Cursor.DEFAULT);
            }
            
        });

        setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setTranslateX(event.getSceneX());
                setTranslateY(event.getSceneY());
            }
        });
        
    }

    public void DrawPin(double x, int i){
        UPin pin = new UPin(x, (i*size)-(size/2), size/3, Color.WHITE,"Test");
            pin.setOnMouseDragged(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    event.consume();
                }
            });
            pin.setOnMouseEntered(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    setCursor(Cursor.OPEN_HAND);
                }
                
            });
            pin.setOnMouseExited(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    setCursor(Cursor.DEFAULT);
                }
                
            });
            
            this.getChildren().add(pin);
    }

}