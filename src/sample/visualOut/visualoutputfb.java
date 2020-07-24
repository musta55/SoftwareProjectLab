package sample.visualOut;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Post;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import sample.spl1.*;
import sample.spl1.emotioncal.EmotionCalculation;

import static java.lang.Math.abs;

public class visualoutputfb {
    public static String s = null;
    double[] out = new double[10000];
    double[] sentimentPos = new double[1000];
    double[] sentimentNeg = new double[1000];
    double[] sentimentTot = new double[1000];
    double tempTotal;
    public double[] outData()
    {
        Scanner scan=null;
        File file = null;
        file = new File("src/sample/spl1/out.txt");
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int i = 0;
        int m = 0;
        while (scan.hasNextLine()) {
            //
            String currentLine = scan.nextLine();
            // System.out.println("Current Line "+currentLine);
            String[] firstSplits = currentLine.split(" ", 0);
            for (; m < firstSplits.length; m++) {
                //     System.out.println("String is " + firstSplits[m]);
                try {
                    if (firstSplits[m] == "NaN" || firstSplits[m] == "Infinity") ;
                    else {
                        out[m] = Double.parseDouble(firstSplits[m]);
                        System.out.print(out[m] + "          ");
                    }
                } catch (Exception q) {
                    out[m] = 0.000;
                }
                //     System.out.println("Value is " + out[m]);
            }
            // double d = Double.parseDouble();
        }
        for (int s = 0, t = 1; t <= 15; s += 8, t++) {
            sentimentPos[t] = out[s] + out[s + 1] + out[s + 4] + out[s + 6];
        }

        for (int s = 0, t = 1; t <= 15; s += 8, t++) {
            sentimentNeg[t] = out[s + 2] + out[s + 3] + out[s + 5] + out[s + 7];
        }
        for (int t = 1; t <= 15; t++) sentimentTot[t] = sentimentPos[t] - sentimentNeg[t];

        tempTotal = 0;
        for (int t = 1; t <= 15; t++) tempTotal += sentimentTot[t];
        tempTotal /= 10;
        return sentimentTot;
    }
    public static void VisualOutputFacebook(Stage stage, String accessToken) throws FileNotFoundException {

        DecimalFormat df = new DecimalFormat("0.00");
        Scanner scan;
        File file = null;
        file = new File("src/sample/spl1/out.txt");
        scan = new Scanner(file);
        double[] out = new double[10000];
        int i = 0;
        int m = 0;
        while (scan.hasNextLine()) {
            //
            String currentLine = scan.nextLine();
            // System.out.println("Current Line "+currentLine);
            String[] firstSplits = currentLine.split(" ", 0);
            for (; m < firstSplits.length; m++) {
                //     System.out.println("String is " + firstSplits[m]);
                try {
                    if (firstSplits[m] == "NaN" || firstSplits[m] == "Infinity") ;
                    else {
                        out[m] = Double.parseDouble(firstSplits[m]);
                        System.out.print(out[m] + "          ");
                    }
                } catch (Exception q) {
                    out[m] = 0.000;
                }
                //     System.out.println("Value is " + out[m]);
            }
            // double d = Double.parseDouble();
        }
        Group root = new Group();
        Image background = new Image(visualoutputfb.class.getClassLoader().getResource("emotionSide.png").toString(), true);
        Canvas canvas = new Canvas(1800, 900);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(background, 0, 0);
        Scene scene = new Scene(root, 2000, 900);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("EMOTIONAL PROGRESSION OVER STATUS");

        // CategoryAxis xAxis = new CategoryAxis("Emotions\n"+"1.Joy\n"+"2.Surprise\n"+"3.Fear\n"+"4.Sadness\n"+"5.Trust\n"+"6.Disgust\n"+"7.anticipation\n"+"8.Disgust\n");
        NumberAxis yAxis = new NumberAxis("INTENSITY", 0, 100, 10);
        Button back = new Button("Back");
        back.setTranslateX(1100);
        back.setTranslateY(650);
        back.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #8d9092 0%, #717375 100%),\n" +
                "        #8d9092,\n" +
                "        #717375,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #ffffff, #a1a3a6);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 1.1em;");
        back.setPrefSize(60, 30);


        back.setOnAction(e -> {
            try {
                String Name=null;
                fourthPage fp = new fourthPage();
                fp.runs(stage,accessToken,Name);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Button more = new Button("More Analysis");
        more.setTranslateX(1050);
        more.setTranslateY(700);
        more.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #0B2058 0%, #030B21 100%),\n" +
                "        #030B21,\n" +
                "        #0B2058,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #143389, #09236B);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 1.5em;");
        more.setPrefSize(160, 40);
        more.setTextFill(Color.WHITE);

        more.setOnAction(e -> {
            try {

                Button backs = new Button("Back");
                backs.setTranslateX(1100);
                backs.setTranslateY(650);
                backs.setStyle("-fx-padding: 8 15 15 15;\n" +
                        "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                        "    -fx-background-radius: 8;\n" +
                        "    -fx-background-color: \n" +
                        "        linear-gradient(from 0% 93% to 0% 100%, #8d9092 0%, #717375 100%),\n" +
                        "        #8d9092,\n" +
                        "        #717375,\n" +
                        "        radial-gradient(center 50% 50%, radius 100%, #143389, #09236B);\n" +
                        "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                        "    -fx-font-weight: bold;\n" +
                        "    -fx-font-size: 1.1em;");
                backs.setPrefSize(60, 30);


                backs.setOnAction(ea-> {
                    try {
                        Progression statusProg =new Progression();

                      //  String accessToken="EAAMF6lCN2rABAL6ZCqIhEAzejYUMuQyccpK5G9Kq1rsvZAghc6Bac8OeVpzL0seJ6BpmaN2FNx5QkIjs7yom44YzruiPdwJISFImQsMAXGt9RG2THGAnaX9WBeBZCq7KBBSQku7pAlXobTZCZCKHThvIGAlePRNXRvekSC2kYJgZDZD";
                        statusProg.statusProgress(stage,accessToken);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
                double joyOut = 0;

                for (int y = 0; y < 80; y += 8) joyOut += out[y];
                joyOut = joyOut / 10;


                double surpriseOut = 0;
                for (int y = 1; y < 81; y += 8) surpriseOut += out[y];
                surpriseOut = surpriseOut / 10;

                double fearOut = 0;
                for (int y = 2; y < 82; y += 8) fearOut += out[y];
                fearOut = fearOut / 10;

                double sadnessOut = 0;
                for (int y = 3; y < 83; y += 8) sadnessOut += out[y];
                sadnessOut = sadnessOut / 10;

                double trustOut = 0;
                for (int y = 4; y < 84; y += 8) trustOut += out[y];
                trustOut = trustOut / 10;

                double disgustOut = 0;
                for (int y = 5; y < 85; y += 8) disgustOut += out[y];
                disgustOut = disgustOut / 10;

                double anticipationOut = 0;
                for (int y = 6; y < 86; y += 8) anticipationOut += out[y];
                anticipationOut = anticipationOut / 10;

                double angerOut = 0;
                for (int y = 7; y < 87; y += 8) angerOut += out[y];
                angerOut = angerOut / 10;

                //   Image backgrounds = new Image(getClass().getClassLoader().getResource("emotionSide.png").toString(), true);
                Image backgrounds = new Image("emotionSide.png");

                Pane roots = new Pane();

                CategoryAxis xAxises = new CategoryAxis();
                xAxises.setCategories(FXCollections.<String>
                        observableArrayList(Arrays.asList("Joy", "Surprise", "Fear", "Sadness", "Trust", "Disgust", "anticipation", "Anger")));
                xAxises.setLabel("EMOTION");

                NumberAxis yAxises = new NumberAxis();
                yAxises.setLabel("INTENSITY");

                //Creating the Bar chart
                BarChart<String, Number> barChart = new BarChart<>(xAxises, yAxises);
                barChart.setTitle("Comparison between emotions");

                //Prepare XYChart.Series objects by setting data
                XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                series1.setName("EMOTION");
                series1.getData().add(new XYChart.Data<>("Joy", joyOut));
                series1.getData().add(new XYChart.Data<>("Surprise", surpriseOut));
                series1.getData().add(new XYChart.Data<>("Fear", fearOut));
                series1.getData().add(new XYChart.Data<>("Sadness", sadnessOut));
                series1.getData().add(new XYChart.Data<>("Trust", trustOut));
                series1.getData().add(new XYChart.Data<>("Disgust", disgustOut));
                series1.getData().add(new XYChart.Data<>("anticipation", anticipationOut));
                series1.getData().add(new XYChart.Data<>("Anger", angerOut));
                barChart.setTranslateX(400);
                barChart.setTranslateY(120);
                barChart.setScaleX(1.2);
                barChart.setScaleY(1.2);


                barChart.getData().addAll(series1);


                BackgroundImage bi = new BackgroundImage(background,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
                Background bg = new Background(bi);
                roots.setBackground(bg);
                double maxa = -1, maxb = -1;
                int temp = 0, temp2 = 0;
                Double[] freq = new Double[]{joyOut, surpriseOut, fearOut, sadnessOut, trustOut, disgustOut, anticipationOut, angerOut};
                for (int iteration = 0; iteration < 8; iteration++) {

                    if (freq[iteration] > maxa) {
                        maxb = maxa;
                        maxa = freq[iteration];
                        temp = iteration;
                    }

                    else if (freq[iteration] > maxb && freq[iteration] != maxa) {
                        maxb = freq[iteration];
                        temp2 = iteration;
                    }


                }
                System.out.println("Highest is " + temp + "Second is " + temp2);
                String text = null;

                if (temp == 0) {
                    text = "This person has a kind heart and jolly mind.Her post is full of serenity and ecstasy";
                } else if (temp == 1) {
                    text = "As a person,you are curious.Surprising things happening around you.Live with amazement";
                } else if (temp == 2) {
                    text = "Some terrible things is happening in your mind.Stay safe and sound";
                } else if (temp == 3) {
                    text = "Sadness grabs you severely.But remember, heavy hearts , like heavy clouds in the sky, are best relieved by the letting of a little water";
                } else if (temp == 4) {
                    text = "You accept and adapt things happening around you wisely.Have enough courage to trust love one more time and always one more time";
                } else if (temp == 5) {
                    text = "Disgust and boredom is the key of your status";
                } else if (temp == 6) {
                    text = "Keep smiling that reflects on your posts.Spread positivity and be optimistic";
                } else if (temp == 7) {
                    text = "Keep smiling that reflects on your posts.Spread positivity and be optimistic";

                }
                Text texts = new Text(text);

                texts.setFont(Font.font(Font.getFontNames().get(7), FontPosture.REGULAR, 12));
                texts.setScaleX(1);
                texts.setScaleY(1);
                texts.setTranslateX(50);
                texts.setTranslateY(40);
                texts.setFill(Color.DARKCYAN);


                roots.getChildren().addAll(barChart, texts, back);
                Scene scenea = new Scene(roots, 1400, 750);
                // Scene scene = new Scene(root, 1600, 800);
                stage.setScene(scenea);
                //primaryStage.setFullScreen(true);
                stage.show();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        CategoryAxis xAxisq = new CategoryAxis();
        xAxisq.setLabel("Status");

        NumberAxis yAxisq = new NumberAxis();
        yAxisq.setLabel("Emotion Intensity");


        StackedBarChart lineChart = new StackedBarChart(xAxisq, yAxisq);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("JOY");

        for (int w = 0, e = 1; w < 80 && e <= 10; w += 8, e++)
            dataSeries1.getData().add(new XYChart.Data("Post " + e, out[w]));


        XYChart.Series dataSeries2 = new XYChart.Series();
        dataSeries2.setName("SURPRISE");

        for (int w = 1, e = 1; w < 81 && e <= 10; w += 8, e++)
            dataSeries2.getData().add(new XYChart.Data("Post " + e, out[w]));

        XYChart.Series dataSeries3 = new XYChart.Series();
        dataSeries3.setName("FEAR");
        for (int w = 2, e = 1; w < 82 && e <= 10; w += 8, e++)
            dataSeries3.getData().add(new XYChart.Data("Post " + e, out[w]));


        XYChart.Series dataSeries4 = new XYChart.Series();
        dataSeries4.setName("SADNESS");

        for (int w = 3, e = 1; w < 83 && e <= 10; w += 8, e++)
            dataSeries4.getData().add(new XYChart.Data("Post " + e, out[w]));


        XYChart.Series dataSeries5 = new XYChart.Series();
        dataSeries5.setName("TRUST");

        for (int w = 0, e = 1; w < 84 && e <= 10; w += 8, e++)
            dataSeries5.getData().add(new XYChart.Data("Post " + e, out[w]));

        XYChart.Series dataSeries6 = new XYChart.Series();
        dataSeries6.setName("DISGUST");

        for (int w = 5, e = 1; w < 85 && e <= 10; w += 8, e++)
            dataSeries6.getData().add(new XYChart.Data("Post " + e, out[w]));

        XYChart.Series dataSeries7 = new XYChart.Series();
        dataSeries7.setName("ANTICIPATION");

        for (int w = 6, e = 1; w < 86 && e <= 10; w += 8, e++)
            dataSeries7.getData().add(new XYChart.Data("Post " + e, out[w]));


        XYChart.Series dataSeries8 = new XYChart.Series();
        dataSeries8.setName("ANGER");

        for (int w = 7, e = 1; w < 87 && e <= 10; w += 8, e++)
            dataSeries8.getData().add(new XYChart.Data("Post " + e, out[w]));


        lineChart.getData().addAll(dataSeries1, dataSeries2, dataSeries3, dataSeries4, dataSeries5, dataSeries6, dataSeries7, dataSeries8);
        File fileDate = new File("sample/spl1/date.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String str;
        while (true) {
            try {
                if (!((str = br.readLine()) != null)) break;
                System.out.println(str);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        //LineChart chart = new LineChart(xAxis, yAxis, lineChart);
        lineChart.setPrefSize(1200, 700);

//
//        Button moreSenti = new Button("Final Report");
//        moreSenti.setTranslateX(450);
//        moreSenti.setTranslateY(700);
//        moreSenti.setPrefSize(360, 40);
//        moreSenti.setTextFill(Color.WHITE);
//
//        moreSenti.setOnAction(e ->
//        {
//            finalReport fr=new finalReport(stage,accessToken,sentimentTot,finalTempTotal);
//          });


        //   root.getChildren().addAll(canvas,lineChart,more,status2,status3,status4,status5,status6,moreSenti);
        root.getChildren().addAll(canvas, lineChart, more);


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

}