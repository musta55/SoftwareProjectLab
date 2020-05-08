package sample.spl1;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Post;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.visualOut.Progression;
import sample.visualOut.overAll;
import sample.visualOut.visualoutputfb;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import sample.spl1.emotioncal.EmotionCalculation;
public class fourthPage {
    
public void runs(Stage stages)
{

    String accessToken = "EAAJfo73qhKQBAIZAaqTJfb0SNJKQfl7hEYV5FZAR8jJZCTCehjlBll24KiqYwT4eAZBkFQgZBfbMcw3ZA9WkhzKR5T8UoSoBG0Y7Afkqg5L1GTKPV0Fz8Wb2bUDDWS826t7NaZBdF10dYAjzwBZANrnOBbUehBtxxcVuJgBmRR26iwZDZD ";
        Button proa = new Button("Overall Emotion");
        proa.setTranslateX(550);
        proa.setTranslateY(70);
        setStyle(proa);
        proa.setPrefSize(300, 80);
        proa.setTextFill(Color.WHITE);

        Button progression = new Button("Progress");
        progression.setTranslateX(550);
        progression.setTranslateY(220);
        setStyle(progression);
        progression.setPrefSize(300, 80);
        progression.setTextFill(Color.WHITE);



        Button backs = new Button("Back");
        backs.setTranslateX(550);
        backs.setTranslateY(370);
        setStyle(backs);
        backs.setPrefSize(300, 80);
        backs.setTextFill(Color.WHITE);

        Pane roots = new Pane();



        backs.setOnAction(esb->{
            try {
                secondPage goBack = new secondPage();
                goBack.TheSecond(stages);
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });

    progression.setOnAction(e->
            {
                Progression statusProg =new Progression();
                statusProg.statusProgress(stages,accessToken);

            }
    );




    proa.setOnAction(e->
            {
                overAll  over =new overAll();
                over.overall(stages,accessToken);


            }
    );



    Scene scene = new Scene(roots, 1400, 700);
    stages.setScene(scene);
    //stages.setFullScreen(true);
    stages.show();
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
    roots.setBackground(bg);

    roots.getChildren().addAll(backs,proa,progression);

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
