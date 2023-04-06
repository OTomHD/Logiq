import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class Controller {

    private App app = App.getInstanceApp();
    @FXML
    private Pane view;

    public void node(ActionEvent event){
        UComponent node = new UComponent(1, 2,"GRAY");
        view.getChildren().add(node);
        app.addComponent("node");
    }
    public void and(ActionEvent event){
        UComponent node = new UComponent(2, 1,"GREEN");
        view.getChildren().add(node);
        app.addComponent("and");
    }
    public void or(ActionEvent event){
        UComponent node = new UComponent(2, 1, "ORANGE");
        view.getChildren().add(node);
        app.addComponent("or");
    }
    public void not(ActionEvent event){
        UComponent node = new UComponent(1, 1, "RED");
        view.getChildren().add(node);
        app.addComponent("not");
    }
}