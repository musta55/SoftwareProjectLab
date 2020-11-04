package sample.spl1.additionalClass;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class algorithm {

    public void algo()
    {
        Stage stage=new Stage();

        Image background = new Image(getClass().getClassLoader().getResource("Pictures/algorithm.png").toString(), true);

        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        Canvas canvas = new Canvas(400, 70);

        Pane root = new Pane();
        root.setBackground(bg);
        Scene scene = new Scene(root, 1200, 750);
        stage.setX(200);
        stage.setY(70);
        scene.setFill(Color.BLACK);

        stage.setScene(scene);
        stage.show();

    }
}
