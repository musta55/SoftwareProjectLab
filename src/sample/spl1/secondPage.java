package sample.spl1;

import demo.sphinx.helloworld.HelloWorld;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.spl1.languageClassification.Language;
import sample.spl1.login.loginOrReg;


public class secondPage {

    public void TheSecond(Stage stage) {


        Text headning = new Text("Entry Section");
        headning.setFont(Font.font(Font.getFontNames().get(12), FontPosture.REGULAR, 14));
        headning.setScaleX(5);
        headning.setScaleY(5);
        headning.setTranslateX(630);
        headning.setTranslateY(70);
        headning.setFill(Color.rgb(237, 134, 18  ));


        Button pro = new Button("Experiment");
        pro.setTranslateX(610);
        pro.setTranslateY(60);
        setStyle(pro);
        pro.setPrefSize(200, 80);
        pro.setTextFill(Color.WHITE);


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



        Button back = new Button("");
        back.setGraphic(new ImageView("Pictures/backArrow - Copy.png"));
        back.setTranslateX(0);
        back.setTranslateY(340);
        back.setPrefSize(1, 5);
        back.setTextFill(Color.YELLOW);

        application.setOnAction(e -> {

            try {
                loginOrReg lr=new loginOrReg();
                lr.logOrReg(stage);

            } catch (Exception excep) {
                excep.printStackTrace();
            }
        });
        others.setOnAction(e -> {

                try {
                    HelloWorld hl=new HelloWorld();
                    hl.speech(stage);

                }
                catch (Exception excep)
                {
                    excep.printStackTrace();
                }
            });





        Image background = new Image(getClass().getClassLoader().getResource("Pictures/emotion(16-9)-01.jpg").toString(), true);
        Pane root = new Pane();

        root.getChildren().addAll(back,pro,application,others);
        pro.setOnAction(e -> {
            try {
                Language PMenu = new Language();
                PMenu.TheThird(stage);
            } catch (Exception excep) {
                excep.printStackTrace();
            }
        });

        back.setOnAction(e->{
            try {
                Main goBack = new Main();
                goBack.start(stage);
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });

        Canvas canvas = new Canvas(1400,750);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(background,0,0);
        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        root.setBackground(bg);


        Scene scene = new Scene(root, 1400, 750);
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