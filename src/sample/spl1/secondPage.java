package sample.spl1;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.spl1.additionalClass.aboutLogic;
import sample.spl1.additionalClass.experimentDescription;
import sample.spl1.languageClassification.Language;
import sample.spl1.login.logIn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class secondPage {

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
    public void TheSecond(Stage stage) throws FileNotFoundException {


        Image Ab = null;

        Ab = new Image(new FileInputStream("src/Pictures/experiment.png"));
        ImageView about = new ImageView(Ab);
        Button pro = new Button(null, about);
        pro.setBackground(null);

        pro.setTranslateX(500);
        pro.setTranslateY(120);


        Image Ab2 = null;

        Ab2 = new Image(new FileInputStream("src/Pictures/app.png"));
        ImageView about2 = new ImageView(Ab2);
        Button application = new Button(null, about2);
        application.setBackground(null);

        application.setTranslateX(500);
        application.setTranslateY(520);

        application.setOnAction(e -> {

            logIn log = new logIn();
            try {
                log.login(stage);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });



        Image Ab3 = null;

        Ab3 = new Image(new FileInputStream("src/Pictures/spch.png"));
        ImageView about3 = new ImageView(Ab3);
        Button other = new Button(null, about3);
        other.setBackground(null);

        other.setTranslateX(500);
        other.setTranslateY(320);

        Button back = getButton();


        other.setOnAction(e -> {

            try {
//                    HelloWorld hl=new HelloWorld();
//                    hl.speech(stage);

                aboutLogic al=new aboutLogic();
                al.aboutlogic(stage);

            } catch (Exception excep) {
                excep.printStackTrace();
            }
        });

        Image background = new Image(new FileInputStream("src/Pictures/newbg.png"));
        Pane root = new Pane();

        root.getChildren().addAll(back, pro, application,other);
        int scroll=0;

        pro.setOnMouseEntered(e->{
            try {
                experimentDescription ex=new experimentDescription();
                ex.experimentdescription();

            } catch (Exception excep) {
                excep.printStackTrace();
            }
        });


        pro.setOnAction(e -> {
            try {
                Language PMenu = new Language();
                PMenu.TheThird(stage);
            } catch (Exception excep) {
                excep.printStackTrace();
            }
        });

        back.setOnAction(e -> {
            try {
                Main goBack = new Main();
                goBack.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Canvas canvas = new Canvas(1400, 750);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(background, 0, 0);
        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        root.setBackground(bg);


        Scene scene = new Scene(root, 1408, 752);
        stage.setScene(scene);
        //stage.setFullScreen(true);
        stage.show();
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