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
import sample.spl1.languageClassification.biLingual;
import sample.spl1.login.RegistrationFrormApplication;
import sample.spl1.visualOut.accessToken;

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



        Button socialMedia = new Button("");
        socialMedia.setTranslateX(300);
        socialMedia.setTranslateY(170);
        setStyle2(socialMedia);
      //  socialMedia.setPrefSize(300, 80);
        socialMedia.setTextFill(Color.WHITE);
        socialMedia.setGraphic(new ImageView("Pictures/1x/social_facebook_button_blue.png"));

        Button article = new Button("");
        article.setTranslateX(50);
        article.setTranslateY(400);
        setStyle2(article);
      //  article.setPrefSize(300, 80);
        article.setTextFill(Color.WHITE);
        article.setGraphic(new ImageView("Pictures/1x/images.png"));


        Button liveUrl = new Button("");
        liveUrl.setTranslateX(50);
        liveUrl.setTranslateY(170);
        setStyle2(liveUrl);
       // liveUrl.setPrefSize(300, 80);
        liveUrl.setTextFill(Color.WHITE);
        liveUrl.setGraphic(new ImageView("Pictures/1x/482401.png"));


        Button analysis = new Button("");
        analysis.setTranslateX(850);
        analysis.setTranslateY(400);
        setStyle2(analysis);
        //  article.setPrefSize(300, 80);
        analysis.setTextFill(Color.WHITE);
        analysis.setGraphic(new ImageView("Pictures/1x/analysis.png"));


        Button pro = new Button("Experiment");
        pro.setTranslateX(610);
        pro.setTranslateY(60);
        setStyle(pro);
        pro.setPrefSize(200, 70);
        pro.setTextFill(Color.WHITE);


        Button others = new Button("Others");
        others.setTranslateX(1180);
        others.setTranslateY(60);
        setStyle(others);
        others.setPrefSize(200, 70);
        others.setTextFill(Color.WHITE);

        Button application = new Button("Application");
        application.setTranslateX(895);
        application.setTranslateY(60);
        setStyle(application);
        application.setPrefSize(200, 70);
        application.setTextFill(Color.WHITE);



        Button back = new Button("");
        back.setGraphic(new ImageView("Pictures/backArrow - Copy.png"));
        back.setTranslateX(0);
        back.setTranslateY(340);
        back.setPrefSize(1, 5);
        back.setTextFill(Color.YELLOW);

        analysis.setOnAction(e -> {
            try {
                AnalysisPage ap=new AnalysisPage(stage,Name);
                ap.analysis();

            } catch (Exception excep) {
                excep.printStackTrace();
            }
        });


        application.setOnAction(e -> {

            try {

                RegistrationFrormApplication reg=new RegistrationFrormApplication();
                reg.registration(stage);

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

        pro.setOnAction(e -> {
            try {
                Language PMenu = new Language();
                PMenu.TheThird(stage);
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
                biLingual bl =new biLingual(stage,Name);
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });



        Image background = new Image(getClass().getClassLoader().getResource("Pictures/1x/emotion(16-9)-0-3.jpg").toString(), true);
        Pane root = new Pane();

        root.getChildren().addAll(socialMedia,back,article,liveUrl,pro,others,application,analysis);

        socialMedia.setOnAction(e -> {
            try
            {
               accessToken at=new accessToken();
            String  tok= at.token(stage,Name,0);
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
                "        linear-gradient(#FFFFFF 0%, #FFFFFF 50%, #FFFFFF 100%),\n" +
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

