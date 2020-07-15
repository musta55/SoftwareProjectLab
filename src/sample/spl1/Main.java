package sample.spl1;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.net.URL;

//import javafx.fxml.FXMLLoader;


public class Main extends Application {
    private ImageIcon danPic;
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


        Text headning = new Text("EMOTION DETECTOR");
        headning.setScaleX(6);
        headning.setScaleY(6);
        headning.setTranslateX(650);
        headning.setTranslateY(100);
        headning.setFill(Color.rgb(21, 27, 81   ));
        headning.setFont(Font.font(Font.getFontNames().get(12), FontPosture.REGULAR, 14));



        headning.setCache(true);




        Button Start = new Button("Let's Start");
        Start.setTranslateX(530);
        Start.setTranslateY(220);

        Start.setTextFill(Color.WHITE);
        Start.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 63% to 0% 100%, #000000 0%, #000000 100%),\n" +
                "        #000000,\n" +
                "        #000000,\n" +
                "        radial-gradient(center 50% 50%, radius 70%, #2471A3    , #17202A);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 2.1em;");
        Start.setPrefSize(280,120);
//        try {
         Image background = new Image(getClass().getClassLoader().getResource("lovw.jpg").toString(), true);
//
       BackgroundImage bi = new BackgroundImage(background,
              BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
              BackgroundPosition.DEFAULT,
               BackgroundSize.DEFAULT);
        Background bg = new Background(bi);

    //    Image background = new Image(getClass().getResource("9.jpg").toExternalForm());
            Canvas canvas = new Canvas(1400,750);
       //     GraphicsContext gc = canvas.getGraphicsContext2D();
        //   gc.drawImage(background,0,0);

        Button speech = new Button("From Speech");
        speech.setTranslateX(830);
        speech.setTranslateY(220);

        speech.setTextFill(Color.WHITE);
        speech.setStyle("-fx-padding: 8 15 15 15;\n" +
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
        speech.setPrefSize(280,120);



            Pane root = new Pane();

        root.setBackground(bg);
            root.getChildren().addAll(canvas,Start,headning);
            Scene scene = new Scene(root,1400,760);
            scene.setFill(Color.BLACK);

            primaryStage.setScene(scene);
            primaryStage.show();




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
  }


    public static void main(String[] args) {
        launch(args);
    }
}