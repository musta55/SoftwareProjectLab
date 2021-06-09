package sample.spl1;

//import demo.sphinx.helloworld.HelloWorld;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.spl1.emotioncal.EmotionProfile;
import sample.spl1.visualOut.finalReport;
import sample.spl1.visualOut.visualoutputfb;

import java.io.*;

public class AnalysisPage {
    private final Stage stage;
    private final String Name;
    private final String tok;
    public  AnalysisPage (Stage stage,String Name,String tok)
    {
        this.stage=stage;
        this.Name=Name;
        this.tok=tok;

    }
    public void analysis() throws IOException {

        Image Ab = null;

        Ab = new Image(new FileInputStream("src/Pictures/pred.png"));
        ImageView about = new ImageView(Ab);
        Button reactionPrediction = new Button(null, about);
        reactionPrediction.setBackground(null);

        reactionPrediction.setTranslateX(100);
        reactionPrediction.setTranslateY(400);

        Image Ab2 = null;

        Ab2 = new Image(new FileInputStream("src/Pictures/dv.png"));
        ImageView about2 = new ImageView(Ab2);
        Button visualization = new Button(null, about2);
        visualization.setBackground(null);

        visualization.setTranslateX(530);
        visualization.setTranslateY(350);



        Image Ab3 = null;

        Ab3 = new Image(new FileInputStream("src/Pictures/ultE.png"));
        ImageView about3 = new ImageView(Ab3);
        Button finalReport = new Button(null, about3);
        finalReport.setBackground(null);

        finalReport.setTranslateX(980);
        finalReport.setTranslateY(270);

        Button back = getButton();



        Pane roots = new Pane();



        back.setOnAction(esb->{
            try {
               thirdPage tp=new thirdPage();
               tp.app(stage,Name);
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });


        Image background = new Image(new FileInputStream("src/Pictures/newbg.png"));
        Pane root = new Pane();

        root.getChildren().addAll(visualization,back,reactionPrediction,finalReport);


        double [] per=new double[10];
        visualization.setOnAction(e->{
            try {
                EmotionProfile emp=new EmotionProfile(Name,stage,tok);
                emp.profileScore(0,"",per,tok);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        File file = new File("tokenfb"+Name);
        System.out.println("Prediction theke ashar por file name :"+file);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st,token="";
        while ((st = br.readLine()) != null)
        {
            System.out.print(st);
            token+=st;
    }


        String finalToken = token;
        reactionPrediction.setOnAction(e -> {
            {
                firstPost fp=new firstPost(finalToken);
                try {
                    fp.firstpost(stage,Name);
                } catch (IOException ed) {
                    ed.printStackTrace();
                }
            }
        });

        double tempTotal=0;
        finalReport.setOnAction(e -> {
            visualoutputfb vf=new visualoutputfb();

            System.out.println("Here name is "+Name);
            File file2 = new File("fb"+Name);
            double[] sentimentTot = vf.outData(file2);


             double finalTempTotal=vf.tempTotal;

            try {
                sample.spl1.visualOut.finalReport fr=new finalReport(stage,tok,sentimentTot,finalTempTotal,Name);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
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
        stage.show();

    }
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
