package sample.spl1;



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
import sample.languageClassification.Language;
import sample.languageClassification.biLingual;

import java.io.FileWriter;

public class thirdPage {
    public void app(Stage stage,String Name)
    {


        Text headning = new Text("Entry Section");
        headning.setFont(Font.font(Font.getFontNames().get(12), FontPosture.REGULAR, 14));
        headning.setScaleX(5);
        headning.setScaleY(5);
        headning.setTranslateX(630);
        headning.setTranslateY(70);
        headning.setFill(Color.rgb(21, 27, 81  ));



        Button socialMedia = new Button("Facebook Post");
        socialMedia.setTranslateX(500);
        socialMedia.setTranslateY(370);
        setStyle(socialMedia);
        socialMedia.setPrefSize(300, 80);
        socialMedia.setTextFill(Color.WHITE);


        Button article = new Button("Your Article");
        article.setTranslateX(500);
        article.setTranslateY(170);
        setStyle(article);
        article.setPrefSize(300, 80);
        article.setTextFill(Color.WHITE);

        Button liveUrl = new Button("Web Page");
        liveUrl.setTranslateX(500);
        liveUrl.setTranslateY(570);
        setStyle(liveUrl);
        liveUrl.setPrefSize(300, 80);
        liveUrl.setTextFill(Color.WHITE);


        Button back = new Button("Back");
        back.setTranslateX(50);
        back.setTranslateY(20);
        back.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #0B2058 0%, #030B21 100%),\n" +
                "        #030B21,\n" +
                "        #0B2058,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #2471A3    , #17202A);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 1.5em;");
        back.setPrefSize(80, 40);
        back.setTextFill(Color.WHITE);

        article.setOnAction(e->{
            try {
                biLingual bl =new biLingual();
                {
                    bl.biLanguage(stage,Name);
                }


            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });



        Image background = new Image(getClass().getClassLoader().getResource("emotionSide.png").toString(), true);
        Pane root = new Pane();

        root.getChildren().addAll(socialMedia,back,article,headning,liveUrl);

        back.setOnAction(e->{
            try {
                Main goBack = new Main();
                goBack.start(stage);
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });



        socialMedia.setOnAction(e -> {
            try
            {
                fourthPage fPage=new fourthPage();
                fPage.runs(stage);
            }catch (Exception ea)
            {
                System.out.println("Fourth Page Problem");
            }

        });
        liveUrl.setOnAction(e -> {
            WebPage wb=new WebPage();
            wb.web(stage,Name);

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

