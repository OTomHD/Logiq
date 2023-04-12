import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class Controller {

   // private App app = App.getInstanceApp();
    @FXML
    private Pane view;

    public void and(ActionEvent event){
        System.out.println("AND");
    }
    public void or(ActionEvent event){
        System.out.println("OR");
    }
    public void not(ActionEvent event){
        System.out.println("NOT");
    }
}