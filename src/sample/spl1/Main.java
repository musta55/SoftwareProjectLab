package sample.spl1;

import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;

import static javafx.stage.StageStyle.TRANSPARENT;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//
//        String path = "F:\\Hindi songs/jackpot.mp3";
//
//        //Instantiating Media class
//        Media media = new Media(new File(path).toURI().toString());
//
//        //Instantiating MediaPlayer class
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//
//        //by setting this property to true, the audio will be played
//        mediaPlayer.setAutoPlay(true);
//        primaryStage.setTitle("Playing Audio");
      //  primaryStage.show();

        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));



        Text headning = new Text("EMOTION DETECTOR");
        headning.setScaleX(6);
        headning.setScaleY(6);
        headning.setTranslateX(650);
        headning.setTranslateY(100);
        headning.setFill(Color.DARKBLUE);
        headning.setFont(Font.font(Font.getFontNames().get(12), FontPosture.REGULAR, 14));

     //   headning.setEffect(ds);

        headning.setCache(true);

//
//        headning.setFont(Font.font(null, FontWeight.BOLD, 14));
//
//        Reflection r = new Reflection();
//        r.setFraction(0.8f);
//
//        headning.setEffect(r);

     //   headning.setTranslateY(400);






        Button Start = new Button("LET'S GO");
        Start.setTranslateX(530);
        Start.setTranslateY(220);

        Start.setTextFill(Color.WHITE);
        Start.setStyle("-fx-padding: 8 15 15 15;\n" +
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
        Start.setPrefSize(280,120);
//        try {
           Image background = new Image(getClass().getClassLoader().getResource("emotion(16-9).png").toString(), true);
    //    Image background = new Image(getClass().getResource("sample/spl1/emotion.jpg").toExternalForm());
            Canvas canvas = new Canvas(1400,750);
//            GraphicsContext gc = canvas.getGraphicsContext2D();
//            gc.drawImage(background,0,0);

            Pane root = new Pane();
        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        root.setBackground(bg);
            root.getChildren().addAll(canvas,Start,headning);
            Scene scene = new Scene(root,1400,760);
            scene.setFill(Color.BLACK);

            primaryStage.setScene(scene);
            //primaryStage.setFullScreen(true);
     //   primaryStage.initStyle(TRANSPARENT);
            primaryStage.show();
//        }catch (Exception e)
//        {
//            System.out.println("Picture url Problem");
//        }
      //  Image fusics = new Image("sample/spl1/emoji.png");


        Start.setOnAction(e->{
            try {
                secondPage SP = new secondPage();
                SP.TheSecond(primaryStage);
            }
            catch (Exception excep)
            {
                excep.printStackTrace();
            }
        });

     //   gc.drawImage(fusics,450,150);


    }


    public static void main(String[] args) {
        launch(args);
    }
}