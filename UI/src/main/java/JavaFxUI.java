import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class JavaFxUI extends Application{

    App app = App.getInstanceApp();
    
    @Override
    public void start(Stage stage){

        Group root = new Group();
        Scene mainScene = new Scene(root,1920,1080,Color.DARKGRAY);
                        
        UComponent component = new UComponent(2, 1);
        root.getChildren().add(component);
        component = new UComponent(1, 1);
        root.getChildren().add(component);
        component = new UComponent(2, 1);
        root.getChildren().add(component);

        
        stage.setScene(mainScene);
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
