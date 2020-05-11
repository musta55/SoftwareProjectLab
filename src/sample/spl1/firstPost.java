package sample.spl1;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Post;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.spl1.emotioncal.EmotionCalculation;

import java.io.FileNotFoundException;
import java.util.List;

public class firstPost {
    public void firstpost(Stage stages)
    {
        Pane stat = new Pane();
        Scene scen = new Scene(stat, 2000, 900);
        String accessToken = "EAAJfo73qhKQBAPTqcW4SDLfFVmOnd3WSpfsapFSaIXcq7tjaaGeLkPU3pykfu64thCEfPipr8jRJYZCdvPv5ZBJi2hsjpZBw0vvZCCKklcYZB6uZBJZBbf1Ri0eoZCJrjX65Q2Ew8lW7ZCZBfXiWkh9CXqZC2Im8ahp7JjoN4zu61ZAN17uQBs6t9gCgVlTmFTIDEUhMOZBq0ZCnBmtukdY77PEB3U ";


        Button status1 = new Button("Status 1");
        status1.setTranslateX(300);
        status1.setTranslateY(320);
        status1.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #0B2058 0%, #030B21 100%),\n" +
                "        #030B21,\n" +
                "        #0B2058,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #143389, #09236B);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 1.5em;");
        status1.setPrefSize(100, 30);
        status1.setTextFill(Color.WHITE);

        int numbers=0;
        TextArea textField = new TextArea();
        textField.setLayoutX(150);
        textField.setLayoutY(180);
        textField.setPrefRowCount(5);
        textField.setPrefColumnCount(6);
        textField.setWrapText(true);
        textField.setMinSize(1125,150);


        Button button = new Button("Enter");
        button.setTextFill(Color.WHITE);
        setStyle(button);
        button.setTranslateX(620);
        button.setTranslateY(350);
        button.setPrefSize(150,70);
        button.setOnAction(action -> {

                    try {
                        postOne po = new postOne();
                        po.postone(stages, accessToken, Integer.parseInt(textField.getText()));
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }
                }
            );
//        status1.setOnAction(es->
//                {
//
//
//                }
//
//
//
//        );

        stages.setScene(scen);
        //    stages.setFullScreen(true);
        stages.show();
        stages.setTitle("STATUS 1 With Reaction");
        stat.getChildren().addAll(button,textField);
        Image background = new Image(getClass().getClassLoader().getResource("emotion(16-9).png").toString(), true);



        Canvas canvas = new Canvas(1600,900);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //gc.drawImage(background,0,0);
        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        stat.setBackground(bg);


    }
    public Button setStyle ( Button b)
    {
        b.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #0B2058 0%, #030B21 100%),\n" +
                "        #030B21,\n" +
                "        #0B2058,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #143389, #09236B);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 2.1em;");
        return b;
    }

}
