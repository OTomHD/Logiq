import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Controller {

   private App app = App.getInstanceApp();
    @FXML
    private Pane view;

    public void and(ActionEvent event){
        String compID = app.addComponent(Components.AND);
        view.getChildren().add(new UIcomponent(compID,Color.GREEN,2,1));
    }
    public void or(ActionEvent event){
        String compID = app.addComponent(Components.OR);
        view.getChildren().add(new UIcomponent(compID,Color.ORANGE,2,1));
    }
    public void not(ActionEvent event){
        String compID = app.addComponent(Components.NOT);
        view.getChildren().add(new UIcomponent(compID,Color.RED,1,1));
    }
    public void split(ActionEvent event){
        System.out.println("split");
    }
}