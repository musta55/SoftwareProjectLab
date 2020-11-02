package sample.spl1.languageClassification;

//import demo.sphinx.helloworld.HelloWorld;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.spl1.secondPage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Language extends secondPage{
    public void TheThird(Stage primaryStage) throws FileNotFoundException {



        Text text = new Text();

        text.setX(340);
        text.setY(140);
        text.setFont(Font.font("Abyssinica SIL", FontWeight.BOLD, FontPosture.REGULAR,60));
        text.setFill(Color.WHITE);// setting colour of the text to blue
        text.setStroke(Color.WHEAT); // setting the stroke for the text
        text.setStrokeWidth(1); // setting stroke width to 2
        text.setText("Select Your Language");


        Image Ab = new Image(new FileInputStream("src/Pictures/bangla.png"));
        ImageView about = new ImageView(Ab);
        Button ab = new Button(null,about);
        ab.setBackground(null);

        ab.setTranslateX(380);
        ab.setTranslateY(200);

        Image Ab2 = new Image(new FileInputStream("src/Pictures/bilingual.png"));
        ImageView about2 = new ImageView(Ab2);
        Button abi = new Button(null,about2);
        abi.setBackground(null);

        abi.setTranslateX(380);
        abi.setTranslateY(600);


        Image Ab3 = new Image(new FileInputStream("src/Pictures/english.png"));
        ImageView about3 = new ImageView(Ab3);
        Button eng = new Button(null,about3);
        eng.setBackground(null);

        eng.setTranslateX(380);
        eng.setTranslateY(400);



        Button back = new Button("");
        back.setGraphic(new ImageView("Pictures/backArrow - Copy.png"));
        back.setTranslateX(0);
        back.setTranslateY(340);
        back.setPrefSize(1, 5);
        back.setTextFill(Color.YELLOW);




        Image background = new Image(getClass().getClassLoader().getResource("Pictures/newbg.png").toString(), true);
        Pane root = new Pane();
        root.getChildren().addAll(eng,back,ab,abi,text);
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


        eng.setOnAction(e->{

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