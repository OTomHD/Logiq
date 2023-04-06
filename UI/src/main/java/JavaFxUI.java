import java.io.IOException;
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

        stage.setScene(new Scene(root,1920,1080,Color.DARKGRAY));
        stage.setTitle("LOGIQ - By SGTQUICK");
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.getIcons().add(new Image("icon/icon.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
