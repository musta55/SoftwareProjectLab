package sample.spl1.emotioncal;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Post;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import sample.spl1.*;
import sample.visualOut.LinearRegression;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import static java.lang.Math.abs;
import static sample.visualOut.visualoutputfb.setStyle;

public class EmotionCalculation {

    private static DecimalFormat df = new DecimalFormat("0.00");
    InputStream is = null;
    DataInputStream dis = null;
    FileOutputStream fos = null;
    DataOutputStream dos = null;
    public String[] emotion = new String[85000];
    public String[] word = new String[85000];

    int i = 0, k = 0, totalEmotionCount = 0;
    int Frequency[] = new int[8];
    public ArrayList<String> emotionList = new ArrayList<String>();      // Emotion Word List
    public ArrayList<String> wordList = new ArrayList<String>();         //Finding emotion from word
    int exClaimCount = 0;
    int intensity;
    //    public static double JoyCal = 0;
//    public static double SurpriseCal = 0;
//    public  static double AngerCal = 0;
//    public  static double SadnessCal = 0;
//    public  static double FearCal = 0;
//    public static  double anticipationCal = 0;
//    public static  double TrustCal = 0;
    public double JoyCal = 0;
    public double SurpriseCal = 0;
    public double AngerCal = 0;
    public double SadnessCal = 0;
    public double FearCal = 0;
    public double anticipationCal = 0;
    public double TrustCal = 0;
    public double DisgustCal = 0;
    public String sf = null;


    public void searchEmotion() throws IOException {

//        EmotionSearch emse=new EmotionSearch();
//        emse.emotionSearch();

        //get the file
      //  System.out.print("After Lemmatization : ");
        for (i = 0; i < Operations.outList.size(); i++) {
          //  System.out.print(Operations.outList.get(i) + " ");
            if (Operations.outList.get(i).contains("!")) {
                exClaimCount++;
            }
            if (Operations.outList.get(i).contains("?")) {
                exClaimCount++;
            }
        }

      //  System.out.println("Hey What's up");

        //   FileWriter newDb = new FileWriter("src/spl1/emotions.txt");

        File files = new File("src/sample/spl1/emotion.txt");

        //   FileReader fr = new FileReader(files);
        //   BufferedReader br = new BufferedReader(fr);

        try (BufferedReader br = new BufferedReader(new FileReader(files))) {              //Exception With Resources


            String currentLine = "";


            int p = 0;
            int bool;
            while ((currentLine = br.readLine()) != null) {

                String[] firstSplit = currentLine.split("\t", 0);
                //  System.out.println(firstSplit[0]);

                if (firstSplit[2].matches("1")) {
                    if (!(firstSplit[1].matches("positive"))) {
                        if (!(firstSplit[1].matches("negative"))) {
                            //  System.out.println(firstSplit[0] + "\t" + firstSplit[1] );
                            //     newDb.write(firstSplit[0] + "\t" + firstSplit[1] + "\n");

                            wordList.add(firstSplit[0]);
                            emotionList.add(firstSplit[1]);
                            p++;


                        }
                    }

                }

            }
        }

//        for(int j=0;j<wordList.size();j++)
//        {
//            System.out.println("word="+wordList.get(j));
//        }
//
//        for(int j=0;j<emotionList.size();j++)
//        {
//            System.out.println(" emotion="+emotionList.get(j));
//        }

        //   Operations op=new Operations(outList);

        //   outList=op.getOutList();
        Boolean found = false;
        for (int w = 0; w < 8; w++) Frequency[w] = 0;


        //      FileWriter fw=new FileWriter("sample/spl1/emotionOutput.txt");
        //   fw.close();


        for (i = 0; i < Operations.outList.size(); ) {

            found = false;
            NEW:
            //   for (int j = 0; j < wordList.size(); j++) {

            //   System.out.println(wordList.get(j)+"  ");

            if (Operations.outList.get(i).equals("so") || Operations.outList.get(i).equals("more") || Operations.outList.get(i).equals("most") || Operations.outList.get(i).equals("quite") || Operations.outList.get(i).equals("very") || Operations.outList.get(i).equals("extremely") || Operations.outList.get(i).equals("quite") || Operations.outList.get(i).equals("just") || Operations.outList.get(i).equals("almost") || Operations.outList.get(i).equals("too") || Operations.outList.get(i).equals("enough") || Operations.outList.get(i).equals("very") || Operations.outList.get(i).equals("extremely") || Operations.outList.get(i).equals("terribly") || Operations.outList.get(i).equals("amazingly") || Operations.outList.get(i).equals("wonderfully") || Operations.outList.get(i).equals("insanely")) {

                int q = 0;
                //   System.out.println("intensity");
                while (q < 3) {

                    // i++;
                    System.out.println(Operations.outList.get(i));

                    int j = 0, a = 0;
                    for (; j < wordList.size(); j++) {

                        if (Operations.outList.get(i).equals(wordList.get(j))) {
                            System.out.print("word :" + wordList.get(j) + " ");
                            System.out.print("Emotion :" + emotionList.get(j) + " ");
                            //  fw.write(emotionList.get(j)+" ");

                            //     System.out.println("Intensity");
                            if (emotionList.get(j).equalsIgnoreCase("joy")) {
                                Frequency[0]++;
                                intensity += 4;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("surprise")) {
                                Frequency[1]++;
                                intensity += 3;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("fear")) {
                                Frequency[2]++;
                                intensity -= 1;
                            }

                            if (emotionList.get(j).equalsIgnoreCase("sadness")) {
                                Frequency[3]++;
                                intensity -= 2;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("trust")) {
                                Frequency[4]++;
                                intensity += 4;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("disgust")) {
                                Frequency[5]++;
                                intensity -= 4;
                            }

                            if (emotionList.get(j).equalsIgnoreCase("anticipation")) {
                                Frequency[6]++;
                                intensity += 2;
                                //  System.out.println("INtensity is" + intensity);
                            }
                            if (emotionList.get(j).equalsIgnoreCase("anger")) {
                                Frequency[7]++;
                                intensity -= 3;
                            }
                            found = true;
                            //  break NEW;
                        } else {
                            a++;
                        }
                    }
                    if (a == j) {
                        i++;
                        q++;
                    } else break;
                }

            } else if (Operations.outList.get(i).equals("never") || Operations.outList.get(i).equals("rarely") || Operations.outList.get(i).equals("not") || Operations.outList.get(i).equals("no") || Operations.outList.get(i).equals("rare") || Operations.outList.get(i).equals("scarcely") || Operations.outList.get(i).equals("seldom") || Operations.outList.get(i).equals("few") || Operations.outList.get(i).equals("little") || Operations.outList.get(i).equals("bad") || Operations.outList.get(i).equals("sad") || Operations.outList.get(i).equals("dont") || Operations.outList.get(i).equals("") || Operations.outList.get(i).equals("poor")) {
                //   System.out.println("negative");
                int q = 0;
                while (q < 5) {


                    // i++;
                    //  NEWS:

                    int j = 0, a = 0;
                    for (; j < wordList.size(); j++) {

                        if (Operations.outList.get(i).equals(wordList.get(j))) {

                            System.out.print("Word :" + wordList.get(j));
                            System.out.print("Emotion :" + emotionList.get(j) + " ");
//                            //     System.out.println("negative");
                            if (emotionList.get(j).equalsIgnoreCase("joy")) {
                                Frequency[5]++;
                                intensity -= 4;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("surprise")) {
                                Frequency[2]++;
                                intensity -= 3;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("fear")) {
                                Frequency[1]++;
                                intensity += 1;
                            }

                            if (emotionList.get(j).equalsIgnoreCase("sadness")) {
                                Frequency[6]++;
                                intensity += 2;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("trust")) {
                                Frequency[7]++;
                                intensity -= 4;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("disgust")) {
                                Frequency[0]++;
                                intensity += 4;
                            }

                            if (emotionList.get(j).equalsIgnoreCase("anticipation")) {
                                Frequency[3]++;
                                intensity -= 2;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("anger")) {
                                Frequency[4]++;
                                intensity += 3;
                            }


                            found = true;
                            //     break NEWS;


                        } else {
                            a++;
                            q++;
                        }
                    }
                    if (a == j) {
                        i++;
                    } else break;
                }
            } else {

//                    if (!(Operations.outList.get(i).equals("never") || Operations.outList.get(i).equals("rarely") || Operations.outList.get(i).equals("not") || Operations.outList.get(i).equals("no") || Operations.outList.get(i).equals("notonly") || Operations.outList.get(i).equals("scarcely") || Operations.outList.get(i).equals("seldom") || Operations.outList.get(i).equals("few") || Operations.outList.get(i).equals("little") || Operations.outList.get(i).equals("bad") || Operations.outList.get(i).equals("sad") || Operations.outList.get(i).equals("dont") || Operations.outList.get(i).equals("amazingly") || Operations.outList.get(i).equals("") || Operations.outList.get(i).equals("poor"))) {
//
//
//                        if (!(
//                                Operations.outList.get(i).equals("so") || Operations.outList.get(i).equals("more") || Operations.outList.get(i).equals("most") || Operations.outList.get(i).equals("quite") || Operations.outList.get(i).equals("very") || Operations.outList.get(i).equals("extremely") || Operations.outList.get(i).equals("quite") || Operations.outList.get(i).equals("just") || Operations.outList.get(i).equals("almost") || Operations.outList.get(i).equals("too") || Operations.outList.get(i).equals("enough") || Operations.outList.get(i).equals("very") || Operations.outList.get(i).equals("extremely") || Operations.outList.get(i).equals("terribly") || Operations.outList.get(i).equals("amazingly") || Operations.outList.get(i).equals("wonderfully") || Operations.outList.get(i).equals("insanely"))) {
//

                for (int j = 0; j < wordList.size(); j++) {
                    if (Operations.outList.get(i).equals(wordList.get(j))) {
                        //   System.out.println("sadsa");
                        System.out.print("Word :" + wordList.get(j) + " ");
                        System.out.print("Emotion :" + emotionList.get(j) + " ");
                        //    fw.write(emotionList.get(j)+" ");
                        //     System.out.println("normal");
                        if (emotionList.get(j).equalsIgnoreCase("joy")) Frequency[0] += 2;
                        if (emotionList.get(j).equalsIgnoreCase("surprise")) Frequency[1] += 3;
                        if (emotionList.get(j).equalsIgnoreCase("fear")) Frequency[2]++;
                        if (emotionList.get(j).equalsIgnoreCase("sadness")) Frequency[3]++;
                        if (emotionList.get(j).equalsIgnoreCase("trust")) Frequency[4]++;
                        if (emotionList.get(j).equalsIgnoreCase("disgust")) Frequency[5] += 2;
                        if (emotionList.get(j).equalsIgnoreCase("anticipation")) Frequency[6]++;
                        if (emotionList.get(j).equalsIgnoreCase("anger")) Frequency[7]++;


                        found = true;
                        break NEW;
                    }
                }

            }
//                }
//            }

            if (!found) {
                //     System.out.print(outList.get(i) + " ");
                //     System.out.print("Emotion : Neutral ");
            }
            i++;
        }
        //   fw.close();
        Operations.outList.clear();

    }

    public static double calculateSD(double emoArray[]) {
        double sum = 0.0, standardDeviation = 0.0;
        int length = emoArray.length;

        for (double num : emoArray) {
            sum += num;
        }

        double mean = sum / length;

        for (double num : emoArray) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation / length);

    }

    public void emotionCalc(Stage stage) throws Exception {


        int max = -1, temp = 0;

        for (int iteration = 0; iteration < 8; iteration++) {
            if (Frequency[iteration] > max) {
                max = Frequency[iteration];
                temp = iteration;
            }

        }

        totalEmotionCount = 4 * Frequency[0] + 3 * (Frequency[1] + exClaimCount) + (-1) * Frequency[2] + (-2) * Frequency[3] + (+4) * Frequency[4] + (-4) * Frequency[5] + (+2) * Frequency[6] + (-3) * Frequency[7];

        totalEmotionCount += intensity;
        System.out.println("");
        System.out.println("Scoring :");
        double tot = 0;
        for (int i = 0; i < 8; i++) {
            tot += Frequency[i];
        }
        tot+=exClaimCount;



        JoyCal =  Math.round((Frequency[0] * 100) / tot * 100.0) / 100.0;
        SurpriseCal =Math.round(((Frequency[1]+exClaimCount) * 100) / tot * 100.0) / 100.0;
        FearCal =Math.round((Frequency[2] * 100) / tot * 100.0) / 100.0;
        SadnessCal = Math.round((Frequency[3] * 100) / tot * 100.0) / 100.0;
        TrustCal =Math.round((Frequency[4] * 100) / tot * 100.0) / 100.0;
        DisgustCal =Math.round((Frequency[5] * 100) / tot * 100.0) / 100.0;
        anticipationCal = Math.round((Frequency[6] * 100) / tot * 100.0) / 100.0;
        AngerCal = Math.round((Frequency[7] * 100) / tot * 100.0) / 100.0;

//
//        Main pie=new Main();
//        Stage stage = new Stage();
//        pie.start(stage);
        double[] emoArray = {JoyCal, SurpriseCal, FearCal, SadnessCal, TrustCal, DisgustCal, anticipationCal, AngerCal};
        double SD = calculateSD(emoArray);

        System.out.format("Standard Deviation = %.6f\n", SD);


        System.out.println("Joy =" + String.format("%.1f", ((Frequency[0] * 100) / tot)) + "%");
        System.out.println("Surprised =" + String.format("%.1f", ((Frequency[1] * 100) / tot)) + "%");
        System.out.println("Fear =" + String.format("%.1f", ((Frequency[2] * 100) / tot)) + "%");
        System.out.println("Sadness =" + String.format("%.1f", ((Frequency[3] * 100) / tot)) + "%");
        System.out.println("Trust =" + String.format("%.1f", ((Frequency[4] * 100) / tot)) + "%");
        System.out.println("Disgust =" + String.format("%.1f", ((Frequency[5] * 100) / tot)) + "%");
        System.out.println("anticipation=" + String.format("%.1f", ((Frequency[6] * 100) / tot)) + "%");
        System.out.println("Anger=" + String.format("%.1f", ((Frequency[7] * 100) / tot)) + "%");

//     DataOutputStream();

        // pieChartExample.start(Stage stage);


        System.out.println("Total emotion score :" + totalEmotionCount);
        if (totalEmotionCount > 0) System.out.println("Overall the sentence is positive ");
        else if (totalEmotionCount == 0) System.out.println("Overall the sentence is neutral");
        else System.out.println("Overall the sentence is negative ");
        if (max == 0) System.out.println("Emotion Preference is neutral");

        else {
            System.out.println("");
            if (temp == 0) {
                System.out.println("Emotion Preference is Joy");
            }
            if (temp == 1) {
                System.out.println("Emotion Preference is Surprised");
            }
            if (temp == 2) {
                System.out.println("Emotion Preference is Fear");
            }
            if (temp == 3) {
                System.out.println("Emotion Preference is Sadness");
            }
            if (temp == 4) {
                System.out.println("Emotion Preference is Trust");
            }
            if (temp == 5) {
                System.out.println("Emotion Preference is Disgust");
            }
            if (temp == 6) {
                System.out.println("Emotion Preference is anticipation");
            }

            if (temp == 7) {
                System.out.println("Emotion Preference is Anger");
            }
        }

    }

    public void fileOpen() {

        try {
            File myObj = new File("src/sample/spl1/out.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public void DataOutputStream() throws IOException {



        String[] dbuf = {String.valueOf(JoyCal), String.valueOf(SurpriseCal), String.valueOf(FearCal), String.valueOf(SadnessCal), String.valueOf(TrustCal), String.valueOf(DisgustCal), String.valueOf(anticipationCal), String.valueOf(AngerCal)};

//Double []dbuf={JoyCal,SurpriseCal,FearCal,SadnessCal,TrustCal,DisgustCal,AngerCal};


        //   fw.write(dbuf[0]+"#"+dbuf[1]+"#"+dbuf[2]+"#"+dbuf[3]+"#"+dbuf[4]+"#"+dbuf[5]+"#"+dbuf[6]+"#"+dbuf[7]+"#");
        //    fw.write("Yo Little piece of Shit");


        try {
            //  FileWriter fw = new FileWriter("src/sample/spl1/out.txt");
            Path path = Paths.get("src/sample/spl1/out.txt");
            //    fw.write("Files in Java might be tricky, but it is fun enough!");
            //  fw.write(dbuf[0]+"#"+dbuf[1]+"#"+dbuf[2]+"#"+dbuf[3]+"#"+dbuf[4]+"#"+dbuf[5]+"#"+dbuf[6]+"#"+dbuf[7]+"#");
            String textToAppend = dbuf[0] + " " + dbuf[1] + " " + dbuf[2] + " " + dbuf[3] + " " + dbuf[4] + " " + dbuf[5] + " " + dbuf[6] + " " + dbuf[7] + " ";
            Files.write(path, textToAppend.getBytes(), StandardOpenOption.APPEND);
            //   fw.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }


    public void VisualOutput(Stage stage) {

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

        Image background = new Image(getClass().getClassLoader().getResource("emotionSide.png").toString(), true);
        Pane root = new Pane();
        back.setOnAction(e -> {
            try {
                Main goBack = new Main();
                goBack.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Joy", JoyCal),
                new PieChart.Data("Surprise", SurpriseCal),
                new PieChart.Data("Anger", AngerCal),
                new PieChart.Data("anticipation", anticipationCal),
                new PieChart.Data("Sadness", SadnessCal),
                new PieChart.Data("Disgust", DisgustCal),
                new PieChart.Data("Fear", FearCal),
                new PieChart.Data("Trust", TrustCal));

        //Creating a Pie chart
        PieChart pieChart = new PieChart(pieChartData);

        //Setting the title of the Pie chart
        pieChart.setTitle("Emotion ");


        //setting the direction to arrange the data
        pieChart.setClockwise(true);

        //Setting the length of the label line
        pieChart.setLabelLineLength(70);

        //Setting the labels of the pie chart visible
        pieChart.setLabelsVisible(true);

        //Setting the start angle of the pie chart
        pieChart.setStartAngle(180);

        pieChart.setTranslateX(300);
        pieChart.setTranslateY(200);
        pieChart.setScaleX(1.5);
        pieChart.setScaleY(1.5);

        //Creating a Group object
        Group roots = new Group(pieChart);


//        Text headning = new Text(s);
//        s=null;
//        headning.setFont(Font.font(Font.getFontNames().get(7), FontPosture.REGULAR, 12));
//        headning.setScaleX(1);
//        headning.setScaleY(1);
//        headning.setTranslateX(50);
//        headning.setTranslateY(40);
//        headning.setFill(Color.DARKCYAN);

        //Creating a scene object
        Scene scene = new Scene(root, 1400, 750);

        //Setting title to the Stage
        stage.setTitle("Pie Chart");
        root.getChildren().addAll(pieChart, back);
        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
        back.setOnAction(e -> {
            try {
                Main goBack = new Main();
                goBack.start(stage);
            } catch (Exception ex) {
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


        // Scene scene = new Scene(root, 1600, 800);
        stage.setScene(scene);
        //primaryStage.setFullScreen(true);
        stage.show();

    }

    public void VisualOutputPred(Stage stage,double[]a,double[]b) {

        Button back = new Button("Back");
        back.setTranslateX(1300);
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

  //      Image background = new Image(getClass().getClassLoader().getResource("emotionSide.png").toString(), true);
        Pane root = new Pane();
        back.setOnAction(e -> {
            try {

                firstPost fp=new firstPost();
                fp.firstpost(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        LinearRegression lr=new LinearRegression(a,b);


        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Haha",abs(lr.predict(JoyCal))),
                new PieChart.Data("Wow", abs(lr.predict(SurpriseCal))),
                new PieChart.Data("Angry",  abs(lr.predict(AngerCal)+lr.predict(DisgustCal))),
                new PieChart.Data("Love", abs(lr.predict( anticipationCal))),
                new PieChart.Data("Sad", abs(lr.predict (SadnessCal)+ lr.predict(FearCal))),
                new PieChart.Data("Like", abs( lr.predict(TrustCal))));

        Text text = new Text();

        //Setting the text to be added.
        text.setText("    "+ df.format(lr.predict(JoyCal))+"%                "+  df.format(lr.predict(SurpriseCal))+"%                "+ df.format(lr.predict(AngerCal)+lr.predict(DisgustCal))+"%                "+ df.format(lr.predict( anticipationCal))+"%                "+ df.format(lr.predict (SadnessCal)+ lr.predict(FearCal))+"%                "+  df.format(lr.predict(TrustCal))+"%");

        //setting the position of the text
        text.setX(30);
        text.setY(690);

        Line line = new Line();
        line.setStartX(640.0);
        line.setStartY(0);
        line.setEndX(640.0);
        line.setEndY(1400.0);
        ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList(
                new PieChart.Data("Joy", JoyCal),
                new PieChart.Data("Surprise", SurpriseCal),
                new PieChart.Data("Anger", AngerCal),
                new PieChart.Data("anticipation", anticipationCal),
                new PieChart.Data("Sadness", SadnessCal),
                new PieChart.Data("Disgust", DisgustCal),
                new PieChart.Data("Fear", FearCal),
                new PieChart.Data("Trust", TrustCal));


        //Creating a Pie chart
        PieChart pieChart1 = new PieChart(pieChartData);

        //Setting the title of the Pie chart
        pieChart1.setTitle("Predicted Reaction");


        //setting the direction to arrange the data
        pieChart1.setClockwise(true);

        //Setting the length of the label line
        pieChart1.setLabelLineLength(70);

        //Setting the labels of the pie chart visible
        pieChart1.setLabelsVisible(true);

        //Setting the start angle of the pie chart
        pieChart1.setStartAngle(180);

        pieChart1.setTranslateX(50);
        pieChart1.setTranslateY(160);
        pieChart1.setScaleX(1.5);
        pieChart1.setScaleY(1.5);



        //Creating a Pie chart
        PieChart pieChart2 = new PieChart(pieChartData2);

        //Setting the title of the Pie chart
        pieChart2.setTitle("Emotion ");


        //setting the direction to arrange the data
        pieChart2.setClockwise(true);

        //Setting the length of the label line
        pieChart2.setLabelLineLength(70);

        //Setting the labels of the pie chart visible
        pieChart2.setLabelsVisible(true);

        //Setting the start angle of the pie chart
        pieChart2.setStartAngle(180);

        pieChart2.setTranslateX(710);
        pieChart2.setTranslateY(160);
        pieChart2.setScaleX(1.5);
        pieChart2.setScaleY(1.5);







        //Creating a scene object
        Scene scene = new Scene(root, 1400, 750);

        //Setting title to the Stage
        stage.setTitle("Pie Chart");
        root.getChildren().addAll(pieChart1,pieChart2,line,text, back);
        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
        back.setOnAction(e -> {
            try {

                firstPost fp=new firstPost();
                fp.firstpost(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

//
//        BackgroundImage bi = new BackgroundImage(background,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundPosition.DEFAULT,
//                BackgroundSize.DEFAULT);
//        Background bg = new Background(bi);
//        root.setBackground(bg);


        // Scene scene = new Scene(root, 1600, 800);
        stage.setScene(scene);
        //primaryStage.setFullScreen(true);
        stage.show();

    }


    public void VisualOutputs(Stage stage,String status,int haha,int angry,int sad,int like ,int love,int wow) {


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

        Image background = new Image(getClass().getClassLoader().getResource("emotionSide.png").toString(), true);
        back.setOnAction(e -> {
            try {

                firstPost  fp=new firstPost();
                fp.firstpost(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        Button regression = new Button("Regression Analysis");
        regression.setTranslateX(510);
        regression.setTranslateY(600);
        regression.setStyle("-fx-padding: 8 15 15 15;\n" +
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
        regression.setPrefSize(300, 70);


        stage.show();


        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);

        stage.show();


        CategoryAxis xAxis = new CategoryAxis();
        xAxis.autosize();

        xAxis.setCategories(FXCollections.<String>
                observableArrayList(Arrays.asList("Joy", "Surprise", "Anger", "Sadness","Fear","Love","Trust","Disgust")));
        xAxis.setLabel("category");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("score");

        //Creating the Bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setBarGap(1);
        barChart.setCategoryGap(65);
        barChart.getAnimated();

        barChart.setTitle("Comparison between EMOTION and REACTION");


        //Prepare XYChart.Series objects by setting data
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Emotion");
        series1.getData().add(new XYChart.Data<>("Joy", JoyCal));
        series1.getData().add(new XYChart.Data<>("Surprise", SurpriseCal));
        series1.getData().add(new XYChart.Data<>("Anger", AngerCal));
        series1.getData().add(new XYChart.Data<>("Sadness", SadnessCal));
        series1.getData().add(new XYChart.Data<>("Fear", FearCal));
        series1.getData().add(new XYChart.Data<>("Love", anticipationCal));
        series1.getData().add(new XYChart.Data<>("Trust", TrustCal));
        series1.getData().add(new XYChart.Data<>("Disgust", DisgustCal));


        XYChart.Series<String, Number> series2 = new XYChart.Series<>();

        series2.setName("Reaction");
        series2.getData().add(new XYChart.Data<>("Joy", haha));
        series2.getData().add(new XYChart.Data<>("Surprise", wow));
        series2.getData().add(new XYChart.Data<>("Anger", angry));
        series2.getData().add(new XYChart.Data<>("Sadness", sad));
        series2.getData().add(new XYChart.Data<>("Fear", sad));
        series2.getData().add(new XYChart.Data<>("Love", love));
        series2.getData().add(new XYChart.Data<>("Trust", like));
        series2.getData().add(new XYChart.Data<>("Disgust", angry));




        //Setting the data to bar chart
        barChart.getData().addAll(series1, series2);



        double []a={JoyCal,SurpriseCal,AngerCal,SadnessCal,FearCal,anticipationCal,TrustCal,DisgustCal};
        double []b ={haha,wow,angry, sad, sad,love,like,angry};




        regression.setOnAction(e -> {
            try {
                RegressionOut ro=new RegressionOut(stage,a,b);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });



        //  setStyle(sc);

        barChart.setPrefSize(1200,600);
        Hyperlink link = new Hyperlink("Click Post");
        link.setScaleX(2);
        link.setScaleY(2);
        link.setTranslateX(1060);
        link.setTranslateY(600);


        link.setOnAction(e -> {
            textField tf=new textField();
            tf.text(stage,status);
        });

        //Creating a Group object
        Pane root = new Pane();
        root.getChildren().addAll(barChart,link,back,regression);
     //   root.getChildren().addAll(sc,link,back);
        //Creating a scene object
        Scene scene = new Scene(root, 2000, 900);

        //Setting title to the Stage
        stage.setTitle("Bar Chart");

        //Adding scene to the stage 
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();

    }
}





