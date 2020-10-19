package sample.spl1;



//import demo.sphinx.helloworld.HelloWorld;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sample.spl1.languageClassification.biLingual;
import sample.spl1.visualOut.accessToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class thirdPage extends secondPage{
    public void app(Stage stage,String Name) throws FileNotFoundException {


        Image Ab = null;

        Ab = new Image(new FileInputStream("src/Pictures/fb.png"));
        ImageView about = new ImageView(Ab);
        Button socialMedia = new Button(null, about);
        socialMedia.setBackground(null);

        socialMedia.setTranslateX(280);
        socialMedia.setTranslateY(180);



        Image Ab2 = null;

        Ab2 = new Image(new FileInputStream("src/Pictures/art.png"));
        ImageView about2 = new ImageView(Ab2);
        Button article = new Button(null, about2);
        article.setBackground(null);

        article.setTranslateX(530);
        article.setTranslateY(180);



        Image Ab3 = null;

        Ab3 = new Image(new FileInputStream("src/Pictures/wl.png"));
        ImageView about3 = new ImageView(Ab3);
        Button liveUrl = new Button(null, about3);
        liveUrl.setBackground(null);

        liveUrl.setTranslateX(780);
        liveUrl.setTranslateY(180);

        Image Ab4 = null;

        Ab4 = new Image(new FileInputStream("src/Pictures/anal.png"));
        ImageView about4 = new ImageView(Ab4);
        Button analysis = new Button(null, about4);
        analysis.setBackground(null);

        analysis.setTranslateX(640);
        analysis.setTranslateY(400);




        Image Ab5 = null;

        Ab5 = new Image(new FileInputStream("src/Pictures/line.png"));
        ImageView about5 = new ImageView(Ab5);
        Button text = new Button(null, about5);
        text.setBackground(null);

        text.setTranslateX(180);
        text.setTranslateY(480);


        Button back = getButton();
        analysis.setOnAction(e -> {
            try {
                AnalysisPage ap=new AnalysisPage(stage,Name);
                ap.analysis();

            } catch (Exception excep) {
                excep.printStackTrace();
            }
        });



        Pane roots = new Pane();



        back.setOnAction(esb->{
            try {
                secondPage goBack = new secondPage();
                goBack.TheSecond(stage);
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });


        article.setOnAction(e->{
            try {
                String articleFile="article"+Name;
                biLingual bl =new biLingual(stage,articleFile);
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });



        Image background = new Image(getClass().getClassLoader().getResource("Pictures/emoBg2.png").toString(), true);
        Pane root = new Pane();

        root.getChildren().addAll(socialMedia,back,article,liveUrl,analysis,text);

        socialMedia.setOnAction(e -> {
            try
            {
               accessToken at=new accessToken();
               String socFile="fb"+Name;
            String  tok= at.token(stage,socFile,0);
            }catch (Exception ea)
            {
                System.out.println("Fourth Page Problem");
            }

        });
        liveUrl.setOnAction(e -> {
            WebPage wb=new WebPage();
            String webFile="web"+Name;
            wb.web(stage,webFile);
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
                "        linear-gradient(#FFFFFF, #FFFFFF),\n" +
                "        linear-gradient(\t#FFFFFF, #efaa22),\n" +
                "        linear-gradient(#ffe657 0%, #3CF53C 50%, #1ED71E 100%),\n" +
                "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 30;\n" +
                "    -fx-background-insets: 0,1,2,3,0;\n" +
                "    -fx-text-fill: #654b00;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 2.1em;\n" +
                "    -fx-padding: 10 20 10 20;");
        return b;
    }

    public Button setStyle2 ( Button b)
    {
        b.setStyle("-fx-background-color: \n" +
                "        linear-gradient(\t#FFFFFF, \t#FFFFFF),\n" +
                "        linear-gradient(#FFFFFF, #FFFFFF),\n" +
                "        linear-gradient(\t#FFFFFF, #FFFFFF),\n" +
                "        linear-gradient(#FFFFFF 0%, #FAFAD2 50%, #FFFFFF 100%),\n" +
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

