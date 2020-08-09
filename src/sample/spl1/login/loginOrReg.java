package sample.spl1.login;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class loginOrReg {

    public void logOrReg(Stage primaryStage) throws Exception {


        Text headning = new Text("EMOTION DETECTOR");
        headning.setScaleX(6);
        headning.setScaleY(6);
        headning.setTranslateX(650);
        headning.setTranslateY(100);
        headning.setFill(Color.rgb(237, 134, 18));
        headning.setFont(Font.font(Font.getFontNames().get(12), FontPosture.ITALIC, 12));


        headning.setCache(true);


        Button logIn = new Button("");
        logIn.setGraphic(new ImageView("Pictures/1x/login3.jpg"));

        logIn.setPadding(Insets.EMPTY);
        logIn.setTranslateX(680);
        logIn.setTranslateY(220);

        logIn.setStyle("-fx-background-color: \n" +
                "        linear-gradient(#14FF14, #14FF14),\n" +
                "        linear-gradient(#14FF14, #14FF14),\n" +
                "        linear-gradient(#14FF14, #14FF14),\n" +
                "        linear-gradient(#ffe657 0%, #f8c202 50%, #14FF14 100%),\n" +
                "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n" +
                "-fx-background-radius: 5em; " +
                "-fx-min-width: 150px; " +
                "-fx-min-height: 150px; " +
                "-fx-max-width: 150px; " +
                "-fx-max-height: 150px;" +
                "    -fx-text-fill: #654b00;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 2.1em;\n" +
                "    -fx-padding: 10 10 10 10;");

        logIn.setTextFill(Color.BLACK);
        Image background = new Image(getClass().getClassLoader().getResource("Pictures/1x/emotion(16-9).png").toString(), true);

        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        Canvas canvas = new Canvas(1400, 750);
        Button registration = new Button("");

        registration.setTranslateX(930);
        registration.setTranslateY(220);
        registration.setGraphic(new ImageView("Pictures/1x/register.png"));

        registration.setTextFill(Color.WHITE);
        registration.setStyle("-fx-background-color: \n" +
                "        linear-gradient(#14FF14, #14FF14),\n" +
                "        linear-gradient(#14FF14, #14FF14),\n" +
                "        linear-gradient(#14FF14, #14FF14),\n" +
                "        linear-gradient(#ffe657 0%, #f8c202 50%, #14FF14 100%),\n" +
                "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n" +
                "-fx-background-radius: 5em; " +
                "-fx-min-width: 150px; " +
                "-fx-min-height: 150px; " +
                "-fx-max-width: 150px; " +
                "-fx-max-height: 150px;" +
                "    -fx-text-fill: #654b00;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 2.1em;\n" +
                "    -fx-padding: 10 10 10 10;");

        Pane root = new Pane();


        root.setBackground(bg);
        root.getChildren().addAll(canvas, logIn, registration);
        Scene scene = new Scene(root, 1400, 760);
        scene.setFill(Color.BLACK);

        primaryStage.setScene(scene);
        primaryStage.show();


        logIn.setOnAction(e -> {
            try {
                logIn log = new logIn();
                log.login(primaryStage);
            } catch (Exception excep) {
                excep.printStackTrace();
            }
        });


        registration.setOnAction(e -> {
            try {
                RegistrationFrormApplication reg=new RegistrationFrormApplication();
                reg.registration(primaryStage);

            } catch (Exception excep) {
                excep.printStackTrace();
            }
        });



    }
}

