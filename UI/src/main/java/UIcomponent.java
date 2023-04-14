import java.util.ArrayList;
import java.util.UUID;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class UIcomponent extends AnchorPane {

    Point2D delta;
    int size = 75;

    ArrayList<String> Children = new ArrayList<String>();

    public UIcomponent(String compID,Color fill,int input, int output){
        super();
        setId(compID);
        int height = input > output ? input : output;
        createBase(fill, height);
        createPins(input,output);

        

        setTranslateX(800);
        setTranslateY(400);
    }

    private void createPins(int input, int output){
        for (int i = 1; i <= input; i++) {
            addPin(i, 0);
        }
        for (int i = 1; i <= output; i++) {
            addPin(i, size*3);
        }
    }

    private void addPin(int i, int y){
        Circle pin = new Circle(y, (size * i)-size/2, size/3.5, Color.WHITE);
        pin.setId(UUID.randomUUID().toString());

        pin.setOnMouseEntered(new EventHandler<Event>() {
            @Override
            public void handle(Event event) { setCursor(Cursor.HAND);}});

        pin.setOnMouseExited(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {setCursor(Cursor.OPEN_HAND); }});

        

        pin.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard db = pin.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putString(pin.getId());
                db.setContent(content);
                event.consume();
            }
        });

        pin.setOnDragOver(new EventHandler<DragEvent>() {
            @Override public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasString()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                event.consume();
            }
        });

        pin.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                String SourceID = db.getString();
                Circle Source = null;
                for (Node comp : getParent().getChildrenUnmodifiable()) {
                    Parent newComp;
                    if (comp instanceof Parent){
                        newComp = (Parent) comp; 
                    }else{
                        continue;
                    }
                    for (Node node : newComp.getChildrenUnmodifiable()) {
                        if(SourceID == node.getId() && node instanceof Circle){
                            Source = (Circle)node;
                            System.out.println(node.localToScene(getBoundsInLocal()).getCenterX());
                            System.out.println(Source.localToScene(getBoundsInLocal()).getCenterX());
                            Source.getLocalToSceneTransform().
                        }
                    }
                }
                event.consume(); 
            }

            
        });
        getChildren().add(pin);
    }

    private void createBase(Color fill, int height){
        Rectangle base = new Rectangle(size*3,size*height,fill);
        base.setArcHeight(25);
        base.setArcWidth(25);

        // change Mouse icon for situation
        base.setOnMouseEntered(new EventHandler<Event>() { 
            @Override
            public void handle(Event event) {setCursor(Cursor.OPEN_HAND);}});
        base.setOnMouseExited(new EventHandler<Event>() { 
            @Override
            public void handle(Event event) {setCursor(Cursor.DEFAULT);}});


        // Make Node dragable
        base.setOnMousePressed(new EventHandler<MouseEvent>() { 
            @Override
            public void handle(MouseEvent event) {
               delta = new Point2D(event.getX(), event.getY());
               delta.add(event.getX(), event.getY());
            }
        });
        base.setOnMouseDragged(new EventHandler<MouseEvent>() { 
            @Override
            public void handle(MouseEvent event) {
                setTranslateX(event.getSceneX() - delta.getX());
                setTranslateY(event.getSceneY() - delta.getY());
            }
        });
        getChildren().add(base);
    }
}
