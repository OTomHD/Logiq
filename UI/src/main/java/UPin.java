import java.util.UUID;

import javafx.beans.property.DoubleProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class UPin extends Circle {
    

    UPin(double x,double y,double r,Paint fill){
        super(x, y, r, fill);
        setId(UUID.randomUUID().toString());

        // Allow drag and drop between nodes
        setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard db = startDragAndDrop(TransferMode.ANY);
                ClipboardContent cb = new ClipboardContent();
                cb.putString(getId());
                db.setContent(cb);
                System.out.println(this);
                event.consume();
            }
        });

        setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                if(event.getDragboard().hasString()){
                    event.acceptTransferModes(TransferMode.ANY);
                }
            }
        });

        setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                String sourceID = event.getDragboard().getString();
                UPin source = null;
                UComponent sourceComp = null;
                ObservableList<Node> comps = getParent().getParent().getChildrenUnmodifiable();
                for (Node node : comps) {
                    UComponent comp;
                    if (node instanceof UComponent){
                        comp = (UComponent) node;
                    }
                    else {continue;}
                    for (Node pin : comp.getChildren()) {
                        if (pin.getId() == sourceID){
                            source = (UPin) pin;
                            sourceComp = comp;
                        }
                    }
                }
                if (source != null) {
                    Pane view = (Pane) sourceComp.getParent();
                }
            }
        });

        
        // Set mouse icon for mouse over and not over
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setCursor(Cursor.OPEN_HAND);
                event.consume();
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setCursor(Cursor.MOVE);
                event.consume();
            }
            
        });
    }

}
