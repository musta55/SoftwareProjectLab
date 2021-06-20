package sample.spl1.additionalClass;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.spl1.Main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class algorithm {


    public Button getButton() throws FileNotFoundException {
        Image i = new Image(new FileInputStream("Pictures/backArrow - Copy.png"));
        ImageView iv = new ImageView(i);
        Button back = new Button("",iv);
        back.setTranslateX(0);
        back.setTranslateY(340);
        back.setPrefSize(1, 5);
        back.setTextFill(Color.YELLOW);
        return back;
    }

    public void algo(Stage stage) throws FileNotFoundException {

        Image background = new Image(new FileInputStream("src/Pictures/algorithm.png"));
        Button back = getButton();
        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        Canvas canvas = new Canvas(400, 70);

        back.setOnAction(e -> {
            try {
                Main goBack = new Main();
                goBack.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        Pane root = new Pane();
        root.getChildren().addAll(back);
        root.setBackground(bg);
        Scene scene = new Scene(root, 1200, 750);
        stage.setX(100);
        stage.setY(0);
        scene.setFill(Color.BLACK);

        stage.setScene(scene);
        stage.show();

    }
}
