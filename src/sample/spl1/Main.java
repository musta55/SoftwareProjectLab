package sample.spl1;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main extends Application {

    double x=280;
    int flag=0;
    @Override
    public void start(Stage primaryStage) throws Exception{

        Pane root = new Pane();

        Image play = new Image(new FileInputStream("src/Pictures/start.png"));
        ImageView playB = new ImageView(play);

        Button BtPlay = new Button(null,playB);
        BtPlay.setBackground(null);
        BtPlay.setTranslateX(500);
        BtPlay.setTranslateY(300);
        BtPlay.setOnAction(e->{
            secondPage SP = new secondPage();
            try {
                SP.TheSecond(primaryStage);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        Canvas c = new Canvas(1400,750);
        GraphicsContext gc = c.getGraphicsContext2D();

        Image happy = new Image(new FileInputStream("src/Pictures/gif/happy.gif"));
        ImageView ivH = new ImageView(happy);
        ivH.setX(200);
        ivH.setY(420);
        ivH.setScaleX(0.7);
        ivH.setScaleY(0.7);
        ivH.setPreserveRatio(true);

        Image sad = new Image(new FileInputStream("src/Pictures/gif/sad.gif"));
        ImageView ivS = new ImageView(sad);
        ivS.setX(450);
        ivS.setY(420);
        ivS.setScaleX(0.7);
        ivS.setScaleY(0.7);
        ivS.setPreserveRatio(true);

        Image disgust = new Image(new FileInputStream("src/Pictures/gif/disgust.gif"));
        ImageView ivD = new ImageView(disgust);
        ivD.setX(700);
        ivD.setY(420);
        ivD.setScaleX(0.7);
        ivD.setScaleY(0.7);
        ivD.setPreserveRatio(true);

        Image angry = new Image(new FileInputStream("src/Pictures/gif/angry.gif"));
        ImageView ivA = new ImageView(angry);
        ivA.setX(950);
        ivA.setY(420);
        ivA.setScaleX(0.7);
        ivA.setScaleY(0.7);
        ivA.setPreserveRatio(true);

        Image scale = new Image(new FileInputStream("src/Pictures/scale.png"));
        gc.drawImage(scale,280,650);
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                gc.clearRect(0,0,1400,750);
                gc.drawImage(scale,x,650);
                if(flag==0)
                {
                    x+=5;
                    if(x>=1050)
                        flag=1;
                }
                else if(flag==1)
                {
                    x-=5;
                    if(x<=280)
                        flag=0;
                }
            }
        }.start();

        Image background = new Image(new FileInputStream("src/Pictures/frontPage.png"));

        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        root.setBackground(bg);
        root.getChildren().addAll(c,BtPlay,ivH,ivS,ivA,ivD);

        Scene scene = new Scene(root,1400,750);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
