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


        Rectangle base = new Rectangle(75, height, Color.AQUA);
        base.setArcHeight(20);
        base.setArcWidth(20);

        base.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Comp Enter");
                setCursor(Cursor.MOVE);
            }
        });
        base.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Comp Exit");
                setCursor(Cursor.DEFAULT);
            }
        });
        base.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Comp Drag2");
                setTranslateX(event.getSceneX());
                setTranslateY(event.getSceneY());
            }
        });
        this.getChildren().add(base);


        for (int i = 1; i <= input; i++) {
            DrawPin(0, i);
        }
        for (int i = 1; i <= output; i++) {
            DrawPin(75, i);
        }
    }

    public void DrawPin(double x, int i){
        UPin pin = new UPin(x, (i*size)-(size/2), size/3, Color.WHITE,"Test");
            this.getChildren().add(pin);
    }

}