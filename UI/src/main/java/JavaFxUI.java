import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class JavaFxUI extends Application{

    App app = App.getInstanceApp();

    ArrayList<Component> comps = app.getComponents();

    @Override
    public void start(Stage stage){
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Scenes/Main.fxml"));
        } catch (IOException e) {
            System.err.println("Unable to load fxml file");
            root = new Group();
            e.printStackTrace();
        }
        stage = setUp(stage, new Scene(root,1920,1080,Color.DARKGRAY));
        System.out.println(root.getChildrenUnmodifiable()); 
        
        
        stage.show();

    }

    private Group drawComponents(Group root){
        for (Component comp : comps) {
            UComponent node = new UComponent(comp.inputPins.length, comp.outputPins.length);
            root.getChildren().add(node);
        }
        return root;
    }

    private void testSetUp(){
        app.addComponent("node");
        app.addComponent("and");
        app.addComponent("or");
        app.addComponent("not");
    }

    private Stage setUp(Stage stage, Scene scene){
        stage.setScene(scene);
        stage.setTitle("LOGIQ - By SGTQUICK");
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.getIcons().add(new Image("icon/icon.png"));
        return stage;
    }

    public static void main(String[] args) {
        launch();
    }
}
