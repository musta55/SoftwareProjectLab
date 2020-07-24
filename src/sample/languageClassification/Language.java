package sample.languageClassification;

import demo.sphinx.helloworld.HelloWorld;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import sample.spl1.*;
import sample.spl1.emotioncal.EmotionCalculation;

public class Language extends secondPage{
    public void TheThird(Stage primaryStage)
    {

        Text headning = new Text("User Input");
        headning.setFont(Font.font(Font.getFontNames().get(12), FontPosture.REGULAR, 14));
        headning.setScaleX(5);
        headning.setScaleY(5);
        headning.setTranslateX(630);
        headning.setTranslateY(70);
        headning.setFill(Color.rgb(237, 134, 18 ));


        Button ab = new Button("Bengali Language");
        ab.setTranslateX(70);
        ab.setTranslateY(150);
        setStyle(ab);
        ab.setPrefSize(320, 120);
        ab.setTextFill(Color.WHITE);

        Button abi = new Button("Bilingual");
        abi.setTranslateX(70);
        abi.setTranslateY(550);
        setStyle(abi);
        abi.setPrefSize(320, 120);
        abi.setTextFill(Color.WHITE);

        Button exp = new Button("English Language");
        exp.setTranslateX(70);
        exp.setTranslateY(350);
        setStyle(exp);
        exp.setPrefSize(320, 120);
        exp.setTextFill(Color.WHITE);


        Button back = new Button("");
        back.setGraphic(new ImageView("Pictures/backArrow - Copy.png"));
        back.setTranslateX(0);
        back.setTranslateY(340);
        back.setPrefSize(1, 5);
        back.setTextFill(Color.YELLOW);

        Button pro = new Button("Experiment");
        pro.setTranslateX(610);
        pro.setTranslateY(60);
       // setStyle(pro);
        pro.setPrefSize(200, 80);
        pro.setTextFill(Color.WHITE);
        pro.setStyle("-fx-background-color: \n" +
                "        linear-gradient(#ffd65b, #e68400),\n" +
                "        linear-gradient(#ffef84, #f2ba44),\n" +
                "        linear-gradient(#ffea6a, #efaa22),\n" +
                "        linear-gradient(#ffe657 0%, #f8c202 50%, #ffffff 100%),\n" +
                "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 30;\n" +
                "    -fx-background-insets: 0,1,2,3,0;\n" +
                "    -fx-text-fill: #654b00;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 2.1em;\n" +
                "    -fx-padding: 10 20 10 20;");

        Button others = new Button("Others");
        others.setTranslateX(1180);
        others.setTranslateY(60);
        setStyle(others);
        others.setPrefSize(200, 80);
        others.setTextFill(Color.WHITE);

        Button application = new Button("Application");
        application.setTranslateX(895);
        application.setTranslateY(60);
        setStyle(application);
        application.setPrefSize(200, 80);
        application.setTextFill(Color.WHITE);

        application.setOnAction(e -> {

            try {

                RegistrationFrormApplication reg=new RegistrationFrormApplication();
                reg.registration(primaryStage);

            } catch (Exception excep) {
                excep.printStackTrace();
            }
        });
        others.setOnAction(e -> {

            try {
                HelloWorld hl=new HelloWorld();
                hl.speech(primaryStage);

            }
            catch (Exception excep)
            {
                excep.printStackTrace();
            }
        });

        pro.setOnAction(e -> {
            try {
                Language PMenu = new Language();
                PMenu.TheThird(primaryStage);
            } catch (Exception excep) {
                excep.printStackTrace();
            }
        });


        Image background = new Image(getClass().getClassLoader().getResource("Pictures/1x/emotion(16-9)-01.jpg").toString(), true);
        Pane root = new Pane();
        root.getChildren().addAll(exp,back,ab,abi,pro,application,others);
        //       TextField nameInput=new TextField();


        abi.setOnAction(e -> {
          biLingual bl =new biLingual(primaryStage);
          {
          }
        });


        ab.setOnAction(e -> {
            Bengali bang =new Bengali();
            {
                bang.ban(primaryStage);
            }

        });


        exp.setOnAction(e->{

            English en =new English();
            {
                en.Eng(primaryStage);
            }

        });


        back.setOnAction(e->{
            try {
                secondPage goBack = new secondPage();
                goBack.TheSecond(primaryStage);
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });

        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        root.setBackground(bg);


        Scene scene = new Scene(root, 1400, 750);
        primaryStage.setScene(scene);
        //primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public Button setStyle ( Button b)
    {
        b.setStyle("-fx-background-color: \n" +
                "        linear-gradient(\t#FFFFFF, \t#FFFFFF),\n" +
                "        linear-gradient(#ffef84, #f2ba44),\n" +
                "        linear-gradient(\t#FFFFFF, #efaa22),\n" +
                "        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n" +
                "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 30;\n" +
                "    -fx-background-insets: 0,1,2,3,0;\n" +
                "    -fx-text-fill: #654b00;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 2.1em;\n" +
                "    -fx-padding: 10 20 10 20;");
        return b;
    }
}