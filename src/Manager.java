import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//Cette classe permet la gestion des scènes et en particulier de générer la page d'accueil.

public class Manager {
    private Stage mainStage = new Stage();

    public Manager(){
        mainStage.setTitle("Runner");
        Scene welcomeScene = createWelcomeScene();
        mainStage.setScene(welcomeScene);
    }

    public Scene createWelcomeScene(){
        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 400,true);

        StaticThing backGroundMenu = new StaticThing("background.png");
        ImageView backGroundMenuImage = backGroundMenu.getStaticImage();
        backGroundMenuImage.setFitWidth(800);
        backGroundMenuImage.setFitHeight(400);

        StaticThing backGroundMenu2 = new StaticThing("background2.png");
        ImageView backGroundMenuImage2 = backGroundMenu2.getStaticImage();
        backGroundMenuImage2.setFitWidth(131);
        backGroundMenuImage2.setFitHeight(204);
        backGroundMenuImage2.setX(400);
        backGroundMenuImage2.setY(150);

        StaticThing backGroundMenu3 = new StaticThing("background3.png");
        ImageView backGroundMenuImage3 = backGroundMenu3.getStaticImage();
        backGroundMenuImage3.setFitWidth(100);
        backGroundMenuImage3.setFitHeight(170);
        backGroundMenuImage3.setX(600);
        backGroundMenuImage3.setY(190);

        Text text = new Text();
        text.setText("Run baby, run !");
        text.setFont(Font.font ("Verdana", FontWeight.BOLD, 50));
        text.setFill(Color.WHITE);
        text.setStrokeWidth(2);
        text.setStroke(Color.BLACK);

        text.setCache(true);
        text.setX(280);
        text.setY(75);

        Button createPlayButton = new Button("PLAY");
        createPlayButton.setFont(Font.font ("Verdana", 20));
        Button createHelpButton = new Button("HELP");
        createHelpButton.setFont(Font.font ("Verdana", 20));
        Button createCreditsButton = new Button("CREDITS");
        createCreditsButton.setFont(Font.font ("Verdana", 20));
        Button createExitButton = new Button("EXIT");
        createExitButton.setFont(Font.font ("Verdana", 20));

        createPlayButton.setLayoutX(130);
        createPlayButton.setLayoutY(100);
        createHelpButton.setLayoutX(130);
        createHelpButton.setLayoutY(170);
        createCreditsButton.setLayoutX(130);
        createCreditsButton.setLayoutY(240);
        createExitButton.setLayoutX(130);
        createExitButton.setLayoutY(310);

        createPlayButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
                mainStage.setScene(createGameScene());
            }
        });
        createExitButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
                mainStage.close();
            }
        });
        createCreditsButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
                mainStage.setScene(createCreditsScene());
            }
        });
        createHelpButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
                mainStage.setScene(createHelpScene());
            }
        });

        root.getChildren().addAll(backGroundMenuImage,backGroundMenuImage2,backGroundMenuImage3,createPlayButton,
                createCreditsButton,
                createExitButton,
                createHelpButton,text);
        return scene;
    }

    public GameScene createGameScene() {
        Group root = new Group();
        GameScene gameScene = new GameScene(root, 800, 400);
        return gameScene;
    }

    public Scene createCreditsScene() {
        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 400,true);

        StaticThing backGroundCredits = new StaticThing("background.png");
        ImageView backGroundCreditsImage = backGroundCredits.getStaticImage();
        backGroundCreditsImage.setFitWidth(800);
        backGroundCreditsImage.setFitHeight(400);

        Button createReturnButton = new Button("BACK");
        createReturnButton.setFont(Font.font ("Verdana", 20));
        createReturnButton.setLayoutX(350);
        createReturnButton.setLayoutY(300);

        createReturnButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
                mainStage.setScene(createWelcomeScene());
            }
        });

        Text text = new Text();
        text.setText("Credits");
        text.setFont(Font.font ("Verdana", FontWeight.BOLD, 50));
        text.setFill(Color.WHITE);
        text.setStrokeWidth(2);
        text.setStroke(Color.BLACK);
        text.setX(290);
        text.setY(75);

        Text credits = new Text();
        credits.setText("ENSEA's 2nd Year Students JAVA LAB\n\nGAME PROGRAMMER: Matthieu TURCO\n\nGROUP: 2G3 TD1 TP1");
        credits.setFont(Font.font ("Verdana", FontWeight.NORMAL, 20));
        credits.setFill(Color.WHITE);
        credits.setX(210);
        credits.setY(150);

        root.getChildren().addAll(backGroundCreditsImage,createReturnButton,text,credits);

        return scene;
    }

    public Scene createHelpScene() {
        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 400,true);

        StaticThing backGroundHelp = new StaticThing("background.png");
        ImageView backGroundHelpImage = backGroundHelp.getStaticImage();
        backGroundHelpImage.setFitWidth(800);
        backGroundHelpImage.setFitHeight(400);

        Button createReturnButton = new Button("BACK");
        createReturnButton.setFont(Font.font ("Verdana", 20));
        createReturnButton.setLayoutX(350);
        createReturnButton.setLayoutY(300);

        createReturnButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
                mainStage.setScene(createWelcomeScene());
            }
        });

        Text text = new Text();
        text.setText("Help");
        text.setFont(Font.font ("Verdana", FontWeight.BOLD, 50));
        text.setFill(Color.WHITE);
        text.setStrokeWidth(2);
        text.setStroke(Color.BLACK);
        text.setX(325);
        text.setY(75);

        Text help = new Text();
        help.setText("GOAL:\n\nRun as long as possible while avoiding foes\n\nEvery time you hit a foe, you lose a life. You have 3 lives before losing the game.\n\nYou can kill foes.\n\nJUMP: Press SPACE\n\nSHOOT: Press ENTER");
        help.setFont(Font.font ("Verdana", FontWeight.NORMAL, 13));
        help.setFill(Color.WHITE);
        help.setX(170);
        help.setY(120);

        root.getChildren().addAll(backGroundHelpImage,createReturnButton,text,help);

        return scene;
    }

    public Stage getPrimaryStage() {
        return mainStage;
    }
}
