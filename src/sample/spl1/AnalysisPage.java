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
import javafx.stage.Stage;
import sample.spl1.languageClassification.Language;
import sample.spl1.emotioncal.EmotionProfile;
import sample.spl1.login.RegistrationFrormApplication;
import sample.spl1.visualOut.accessToken;

public class AnalysisPage {
    private Stage stage;
    private String Name;
    private String accessToken;
    public  AnalysisPage (Stage stage,String Name)
    {
        this.stage=stage;
        this.Name=Name;

    }
    public void analysis()
    {

        Button reactionPrediction = new Button("Reaction Prediction");
        reactionPrediction.setTranslateX(660);
        reactionPrediction.setTranslateY(170);
        setStyle2(reactionPrediction);
        reactionPrediction.setPrefSize(500, 150);
        reactionPrediction.setTextFill(Color.WHITE);
        reactionPrediction.setGraphic(new ImageView("Pictures/1x/reaction.jpg"));

        Button visualization = new Button("Emotion Visualization");
        visualization.setTranslateX(660);
        visualization.setTranslateY(370);
        setStyle2(visualization);
        visualization.setPrefSize(500, 150);
        visualization.setTextFill(Color.WHITE);
        visualization.setGraphic(new ImageView("Pictures/1x/visualizaton.png"));

        Button finalReport = new Button("Final Feedback");
        finalReport.setTranslateX(660);
        finalReport.setTranslateY(570);
        setStyle2(finalReport);
        finalReport.setPrefSize(500, 150);
        finalReport.setTextFill(Color.WHITE);
        finalReport.setGraphic(new ImageView("Pictures/1x/result.jpg"));


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
                fourthPage goBack = new fourthPage();
                goBack.runs(stage,accessToken,Name);
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });






        Image background = new Image(getClass().getClassLoader().getResource("Pictures/1x/green.png").toString(), true);
        Pane root = new Pane();

        root.getChildren().addAll(visualization,back,reactionPrediction,finalReport,pro,others,application);


        visualization.setOnAction(e->{
            try {
                EmotionProfile emp=new EmotionProfile(Name,stage);
                emp.profileScore(0,"");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        reactionPrediction.setOnAction(e -> {
            {
                accessToken at=new accessToken();
                 accessToken= at.token(stage,Name,1);
            }
        });

        finalReport.setOnAction(e -> {
            accessToken at=new accessToken();
            accessToken= at.token(stage,Name,2);
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
                "        linear-gradient(#FFFFFF 0%, #7FFF00 50%, #FFFFFF 100%),\n" +
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
