package sample.spl1.additionalClass;

import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class experimentDescription {


    public void experimentdescription() throws Exception {

        Stage stage=new Stage();

        Text headning = new Text("Learn how emotion detector tool works");
        headning.setScaleX(4);
        headning.setScaleY(4);
        headning.setX(150);
        headning.setY(30);
        headning.setFill(Color.BLACK);
        headning.setFont(Font.font(Font.getFontNames().get(12), FontPosture.ITALIC, 4));


        headning.setCache(true);

        Image background = new Image(getClass().getClassLoader().getResource("Pictures/rectangle.png").toString(), true);

        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        Canvas canvas = new Canvas(400, 70);

        Pane root = new Pane();
       root.setBackground(bg);
        root.getChildren().addAll(headning);
        Scene scene = new Scene(root, 400, 50);
        stage.setX(1200);
        stage.setY(200);
        scene.setFill(Color.BLACK);

        stage.setScene(scene);
        stage.show();

        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished( event -> stage.close() );
        delay.play();
    }
}
