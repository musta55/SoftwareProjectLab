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
import sample.spl1.emotioncal.EmotionCalculation;
import sample.visualOut.Progression;
import sample.visualOut.overAll;

import java.io.IOException;
import java.util.List;

public class fourthPage {

public void runs(Stage stages,String accessToken,String Name)
{

   // String accessToken = "EAAMF6lCN2rABAL6ZCqIhEAzejYUMuQyccpK5G9Kq1rsvZAghc6Bac8OeVpzL0seJ6BpmaN2FNx5QkIjs7yom44YzruiPdwJISFImQsMAXGt9RG2THGAnaX9WBeBZCq7KBBSQku7pAlXobTZCZCKHThvIGAlePRNXRvekSC2kYJgZDZD";
 //  String accessToken="EAAMF6lCN2rABANG7fFJVwktoiJjKiZCDP7k1v4uZB48GRH1J2GCU1HAJMPc6389TS6EwTdp9ilfqZBZABNGobGhy3bH0zhYh5x2LQ55SicVEOtVmLO8poNvPVpZB3F3aSperHycu8VZAEQY4jfNxVsWrG7ZBZCP1L6bnYTvqrXb5EEIumHvpZBAFT79hR0r67ZCGoZD";
    FacebookClient fbClient = new DefaultFacebookClient(accessToken);
   Button proa = new Button("Total Emotion");
        proa.setTranslateX(550);
        proa.setTranslateY(370);
        setStyle(proa);
        proa.setPrefSize(300, 80);
        proa.setTextFill(Color.WHITE);

        Button progression = new Button("Emotional Progress");
        progression.setTranslateX(550);
        progression.setTranslateY(70);
        setStyle(progression);
        progression.setPrefSize(300, 80);
        progression.setTextFill(Color.WHITE);


    Button Status = new Button("Emotion & Reaction");
    Status.setTranslateX(550);
    Status.setTranslateY(220);
    setStyle(Status);
    Status.setPrefSize(300, 80);
    Status.setTextFill(Color.WHITE);


        Button backs = new Button("Back");
        backs.setTranslateX(550);
        backs.setTranslateY(520);
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
                try {
                    over.overall(stages,accessToken);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
    );

    Status.setOnAction(e->
            {
                firstPost fp=new firstPost(accessToken);
                fp.firstpost(stages);
            }
    );



    Scene scene = new Scene(roots, 1400, 700);
    stages.setScene(scene);
    //stages.setFullScreen(true);
    stages.show();
    Image background = new Image(getClass().getClassLoader().getResource("emotionSide.png").toString(), true);



    Canvas canvas = new Canvas(1400,750);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    //gc.drawImage(background,0,0);
    BackgroundImage bi = new BackgroundImage(background,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
    Background bg = new Background(bi);
    roots.setBackground(bg);

    roots.getChildren().addAll(backs,proa,progression,Status);

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
                "        radial-gradient(center 50% 50%, radius 100%, #2471A3    , #17202A);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 2.1em;");
        return b;
    }

}
