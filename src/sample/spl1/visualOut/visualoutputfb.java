package sample.spl1.visualOut;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.spl1.thirdPage;

import java.io.*;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class visualoutputfb {
    public static String s = null;
    double[] out = new double[10000];
    double[] sentimentPos = new double[1000];
    double[] sentimentNeg = new double[1000];
    double[] sentimentTot = new double[1000];
    public double tempTotal;
    public double[] outData( File file)
    {



        try {
            Scanner    scan = new Scanner(file);

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

        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
    public static void VisualOutputFacebook(Stage stage, String accessToken,String Name) throws FileNotFoundException {

        DecimalFormat df = new DecimalFormat("0.00");
        Scanner scan;
        File file = null;


        System.out.println("file name in fb "+Name);
        file = new File(Name);
        scan = new Scanner(file);
        double[] out = new double[10000];
        int i = 0;
        int m = 0;
        while (scan.hasNextLine()) {
            //
            String currentLine = scan.nextLine();
            String[] firstSplits = currentLine.split(" ", 0);
            for (; m < firstSplits.length; m++) {
                try {
                    if (firstSplits[m] == "NaN" || firstSplits[m] == "Infinity") ;
                    else {
                        out[m] = Double.parseDouble(firstSplits[m]);
                        System.out.print(out[m] + "          ");
                    }
                } catch (Exception q) {
                    out[m] = 0.000;
                }
            }
        }
        Pane root = new Pane();
        Image background = new Image(visualoutputfb.class.getClassLoader().getResource("Pictures/newbg.png").toString(), true);
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


        Scene scene = new Scene(root, 1410, 752);
        stage.setScene(scene);
        stage.show();
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("EMOTIONAL PROGRESSION OVER STATUS");

        // CategoryAxis xAxis = new CategoryAxis("Emotions\n"+"1.Joy\n"+"2.Surprise\n"+"3.Fear\n"+"4.Sadness\n"+"5.Trust\n"+"6.Disgust\n"+"7.anticipation\n"+"8.Disgust\n");
        NumberAxis yAxis = new NumberAxis("INTENSITY", 0, 100, 10);
        Button back = new Button("");
        back.setGraphic(new ImageView("Pictures/backArrow - Copy.png"));
        back.setTranslateX(0);
        back.setTranslateY(340);
        back.setPrefSize(1, 5);
        back.setTextFill(Color.YELLOW);

        back.setOnAction(e -> {
            try {
                thirdPage tp=new thirdPage();
                tp.app(stage, Name);
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
                "        radial-gradient(center 50% 50%, radius 100%, #363632, #343443);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 1.5em;");
        more.setPrefSize(260, 40);
        more.setTextFill(Color.WHITE);

        more.setOnAction(e -> {
            try {

                Button backs = new Button("");
                backs.setGraphic(new ImageView("Pictures/backArrow - Copy.png"));
                backs.setTranslateX(0);
                backs.setTranslateY(340);
                backs.setPrefSize(1, 5);
                backs.setTextFill(Color.YELLOW);


                backs.setOnAction(ea-> {
                    try {
                        Progression statusProg =new Progression();

                      //  String accessToken="EAAMF6lCN2rABAL6ZCqIhEAzejYUMuQyccpK5G9Kq1rsvZAghc6Bac8OeVpzL0seJ6BpmaN2FNx5QkIjs7yom44YzruiPdwJISFImQsMAXGt9RG2THGAnaX9WBeBZCq7KBBSQku7pAlXobTZCZCKHThvIGAlePRNXRvekSC2kYJgZDZD";
                        statusProg.statusProgress(stage,accessToken,Name);
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


                Pane roots = new Pane();

                CategoryAxis xAxises = new CategoryAxis();
                xAxises.setCategories(FXCollections.
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
                barChart.setScaleX(.8);
                barChart.setScaleY(.8);
                barChart.setPrefSize(750,500);


                barChart.getData().addAll(series1);


                BackgroundImage bis = new BackgroundImage(background,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
                Background bgs = new Background(bis);
                roots.setBackground(bgs);
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

                texts.setScaleX(1);
                texts.setScaleY(1);
                texts.setTranslateX(350);
                texts.setTranslateY(140);
                texts.setFill(Color.DARKCYAN);
                texts.setFont(javafx.scene.text.Font.font("Comic Sans MS", FontWeight.BOLD, 18));


                roots.getChildren().addAll(barChart, texts, backs);
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
        lineChart.setPrefSize(750,400);

        lineChart.setLayoutX(400);
        lineChart.setLayoutY(200);


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





        TextArea textField = new TextArea();
        textField.setLayoutX(220);
        textField.setLayoutY(520);
        textField.setPrefRowCount(2);
        textField.setPrefColumnCount(2);
        textField.setWrapText(true);
        textField.setMinSize(105,3);

        Text headnings = new Text("Enter Status No.");
        headnings.setScaleX(3);
        headnings.setScaleY(3);
        headnings.setTranslateX(70);
        headnings.setTranslateY(550);
        headnings.setFill(Color.WHITE);
        headnings.setFont(Font.font(Font.getFontNames().get(12), FontPosture.REGULAR, 9));



        headnings.setCache(true);
        Image Ab = new Image(new FileInputStream("src/Pictures/enter.png"));
        ImageView about = new ImageView(Ab);
        Button button = new Button(null,about);
        button.setBackground(null);

        button.setTranslateX(100);
        button.setTranslateY(610);
        button.setOnAction(action -> {

                    try {
                      viewPost vp=new viewPost();
                   vp.view(Integer.parseInt(textField.getText()),accessToken);
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }
                }
        );




        File fileDate = new File(Name+"date.txt");

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

        Text headning = new Text("Last Ten Posts' Emotion");
        headning.setScaleX(6);
        headning.setScaleY(8);
        headning.setX(650);
        headning.setY(150);
        headning.setFill(Color.WHITE);
        headning.setFont(Font.font(Font.getFontNames().get(12), FontPosture.ITALIC, 7));


        headning.setCache(true);



        lineChart.setPrefSize(800, 500);

        root.getChildren().addAll(headning, lineChart, more,back,textField,headnings,button);


    }



}