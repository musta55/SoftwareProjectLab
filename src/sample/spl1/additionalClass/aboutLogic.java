package sample.spl1.additionalClass;


import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.spl1.Main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class aboutLogic {

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
    public void aboutlogic(Stage stage) throws FileNotFoundException {


        Image Ab = null;

        Ab = new Image(new FileInputStream("src/Pictures/algo.png"));
        ImageView about = new ImageView(Ab);
        Button algo = new Button(null, about);
        algo.setBackground(null);

        algo.setTranslateX(500);
        algo.setTranslateY(120);


        Image Ab2 = null;

        Ab2 = new Image(new FileInputStream("src/Pictures/person.png"));
        ImageView about2 = new ImageView(Ab2);
        Button person = new Button(null, about2);
        person.setBackground(null);

        person.setTranslateX(500);
        person.setTranslateY(320);

        person.setOnAction(e -> {
    person per=new person();
    per.Person(stage);

        });



        Image Ab3 = null;

        Ab3 = new Image(new FileInputStream("src/Pictures/fuzzy.png"));
        ImageView about3 = new ImageView(Ab3);
        Button fuzzy = new Button(null, about3);
        fuzzy.setBackground(null);

        fuzzy.setTranslateX(500);
        fuzzy.setTranslateY(520);

        Button back = getButton();


        fuzzy.setOnAction(e -> {

            try {
//                    HelloWorld hl=new HelloWorld();
//                    hl.speech(stage);

               fuzzyabout fa=new fuzzyabout();
               fa.fuzz(stage);

            } catch (Exception excep) {
                excep.printStackTrace();
            }
        });

        Image background = new Image(getClass().getClassLoader().getResource("Pictures/newbg.png").toString(), true);
        Pane root = new Pane();

        root.getChildren().addAll(back, algo, fuzzy,person);
        int scroll=0;



        algo.setOnAction(e -> {
            try {
               algorithm al=new algorithm();
               al.algo(stage);
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