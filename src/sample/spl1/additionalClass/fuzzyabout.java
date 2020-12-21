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

public class fuzzyabout {


    public Button getButton()
    {
        Button back = new Button("");
        back.setGraphic(new ImageView("Pictures/backArrow - Copy.png"));
        back.setTranslateX(0);
        back.setTranslateY(340);
        back.setPrefSize(1, 5);
        back.setTextFill(Color.YELLOW);
        return back;
    }

    public void fuzz(Stage stage)
    {


        Image background = new Image(getClass().getClassLoader().getResource("Pictures/fuzzification.png").toString(), true);


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
        root.setBackground(bg);
        Scene scene = new Scene(root, 1200, 770);
        stage.setX(200);
        stage.setY(70);
        scene.setFill(Color.BLACK);
        root.getChildren().addAll(back);

        stage.setScene(scene);
        stage.show();

    }
}
