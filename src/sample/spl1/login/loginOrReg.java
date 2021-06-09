package sample.spl1.login;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class loginOrReg {

    public void logOrReg() throws Exception {

        Stage stage=new Stage();

        Text headning = new Text("1.Click the Link\n2.Log In To Your Facebook\n3.Create a new app\n4.From Tools click GRAPH API EXPLORER\n5.Generate access Token \n6.Copy the token & paste it here \n7.Enjoy");
        headning.setScaleX(6);
        headning.setScaleY(8);
        headning.setX(650);
        headning.setY(400);
        headning.setFill(Color.WHITE);
        headning.setFont(Font.font(Font.getFontNames().get(12), FontPosture.ITALIC, 7));


        headning.setCache(true);

        Image background = new Image(new FileInputStream("src/Pictures/newbg.png"));

        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        Canvas canvas = new Canvas(1400, 750);

        Pane root = new Pane();
         root.setBackground(bg);
        root.getChildren().addAll(canvas,headning);
        Scene scene = new Scene(root, 1400, 760);
        scene.setFill(Color.BLACK);

        stage.setScene(scene);
        stage.show();

    }
}

