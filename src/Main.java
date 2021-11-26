import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    public void start(Stage primaryStage){
        Manager manager = new Manager();
        primaryStage = manager.getPrimaryStage();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
