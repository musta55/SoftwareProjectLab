package sample.spl1.emotioncal;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.spl1.*;
import sample.spl1.visualOut.LinearRegression;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;


public class EmotionCalculation {

    private String Names;
    public EmotionCalculation(String profileName)
    {
        this.Names=profileName;
    }
    public EmotionCalculation()
    {
    }
   public String[] emotion = new String[85000];
    public String[] word = new String[85000];

    int i = 0, k = 0, totalEmotionCount = 0;
    int[] Frequency = new int[8];
    public ArrayList<String> emotionList = new ArrayList<String>();      // Emotion Word List
    public ArrayList<String> wordList = new ArrayList<String>();         //Finding emotion from word
    int exClaimCount = 0;
    int intensity;
    public double JoyCal = 0;
    public double SurpriseCal = 0;
    public double AngerCal = 0;
    public double SadnessCal = 0;
    public double FearCal = 0;
    public double anticipationCal = 0;
    public double TrustCal = 0;
    public double DisgustCal = 0;
    public String sf = null;
    private static final DecimalFormat df = new DecimalFormat("0.00");


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
                while (q < 2) {

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

    public static double calculateSD(double[] emoArray) {
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
        System.out.println();
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
            System.out.println();
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

    public void fileOpen(  File myObj) {

        try {
//            File myObj = new File(Name);



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

    public void DataOutputStreamProf() throws IOException {



        String[] dbuf = {String.valueOf(JoyCal), String.valueOf(SurpriseCal), String.valueOf(FearCal), String.valueOf(SadnessCal), String.valueOf(TrustCal), String.valueOf(DisgustCal), String.valueOf(anticipationCal), String.valueOf(AngerCal)};

        String textToAppend = dbuf[0] + " " + dbuf[1] + " " + dbuf[2] + " " + dbuf[3] + " " + dbuf[4] + " " + dbuf[5] + " " + dbuf[6] + " " + dbuf[7] + " ";

        try
        {
            FileWriter fw = new FileWriter(Names,true); //the true will append the new data
            fw.write(textToAppend+" ");
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }

    }

    public void DataOutputStream(String Name) throws IOException {



        String[] dbuf = {String.valueOf(JoyCal), String.valueOf(SurpriseCal), String.valueOf(FearCal), String.valueOf(SadnessCal), String.valueOf(TrustCal), String.valueOf(DisgustCal), String.valueOf(anticipationCal), String.valueOf(AngerCal)};

        try {
            Path path = Paths.get(Name);
            String textToAppend = dbuf[0] + " " + dbuf[1] + " " + dbuf[2] + " " + dbuf[3] + " " + dbuf[4] + " " + dbuf[5] + " " + dbuf[6] + " " + dbuf[7] + " ";
            Files.write(path, textToAppend.getBytes(), StandardOpenOption.APPEND);
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }


    PieChart pieChart(double []value)
    {

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Joy", JoyCal),
                new PieChart.Data("Surprise", SurpriseCal),
                new PieChart.Data("Anger", AngerCal),
                new PieChart.Data("Anticipation", anticipationCal),
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
        pieChart.setLabelLineLength(20);

        //Setting the labels of the pie chart visible
        pieChart.setLabelsVisible(true);

        //Setting the start angle of the pie chart
        pieChart.setStartAngle(180);

        pieChart.setTranslateX(780);
        pieChart.setTranslateY(150);
        pieChart.setScaleX(1.3);
        pieChart.setScaleY(1.4);

        return pieChart;
    }


    public void VisualOutput(Stage stage,String status) throws FileNotFoundException {
        Text headning = new Text("Text");
        headning.setScaleX(2);
        headning.setScaleY(2);
        headning.setTranslateX(140);
        headning.setTranslateY(120);
        headning.setFill(Color.WHITE );
        headning.setFont(Font.font(Font.getFontNames().get(12), FontPosture.REGULAR, 17));



        Button back = new Button("");

        back.setGraphic(new ImageView("Pictures/backArrow - Copy.png"));
        back.setTranslateX(0);
        back.setTranslateY(340);
        back.setPrefSize(1, 5);
        back.setTextFill(Color.YELLOW);

        Image background = new Image(getClass().getClassLoader().getResource("Pictures/newbg.png").toString(), true);
        Pane root = new Pane();
        back.setOnAction(e -> {
            try {
                Main goBack = new Main();
                goBack.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        TextArea textField = new TextArea();
        textField.setLayoutX(80);
        textField.setLayoutY(200);
        textField.setPrefRowCount(5);
        textField.setPrefColumnCount(6);
        textField.setWrapText(true);
        textField.setMinSize(425, 450);
        textField.setText(status);
        textField.setFont(javafx.scene.text.Font.font("Comic Sans MS", FontWeight.BOLD, 18));



        double []value=new double[8];
        value[0]=JoyCal;  value[1]=SurpriseCal;  value[2]=AngerCal;  value[3]=anticipationCal;  value[4]=SadnessCal;  value[5]=DisgustCal;  value[6]=FearCal;  value[7]=TrustCal;  value[0]=JoyCal;
        PieChart pieChart = pieChart(value);
        int max=0;

        for(int i=0;i<8;i++)
        {
           if(max<value[i])max=i;
        }

        Image happy = new Image(new FileInputStream("src/Pictures/gif/happy.gif"));
        ImageView ivH = new ImageView(happy);
        ivH.setX(480);
        ivH.setY(320);
        ivH.setScaleX(0.7);
        ivH.setScaleY(0.7);
        ivH.setPreserveRatio(true);

        Image sad = new Image(new FileInputStream("src/Pictures/gif/sad.gif"));
        ImageView ivS = new ImageView(sad);
        ivS.setX(480);
        ivS.setY(320);
        ivS.setScaleX(0.7);
        ivS.setScaleY(0.7);
        ivS.setPreserveRatio(true);

        Image disgust = new Image(new FileInputStream("src/Pictures/gif/disgust.gif"));
        ImageView ivD = new ImageView(disgust);
        ivD.setX(480);
        ivD.setY(320);
        ivD.setScaleX(0.7);
        ivD.setScaleY(0.7);
        ivD.setPreserveRatio(true);

        Image angry = new Image(new FileInputStream("src/Pictures/gif/angry.gif"));
        ImageView ivA = new ImageView(angry);
        ivA.setX(480);
        ivA.setY(320);
        ivA.setScaleX(0.7);
        ivA.setScaleY(0.7);
        ivA.setPreserveRatio(true);
        if(max==0 || max==1 || max==3 || max==7)
        {
            root.getChildren().addAll(pieChart, back,textField,headning,ivH);
        }

        else  if(max==2 )
        {
            root.getChildren().addAll(pieChart, back,textField,headning,ivA);
        }
        else  if(max==4 )
        {
            root.getChildren().addAll(pieChart, back,textField,headning,ivS);
        }

        else  if(max==5 )
        {
            root.getChildren().addAll(pieChart, back,textField,headning,ivD);
        }

        else  if(max==6 )
        {
            root.getChildren().addAll(pieChart, back,textField,headning,ivS);
        }

        Scene scene = new Scene(root, 1400, 750);

        //Setting title to the Stage
        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        root.setBackground(bg);
        stage.setTitle("Pie Chart");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();



        // Scene scene = new Scene(root, 1600, 800);
        stage.setScene(scene);
        //primaryStage.setFullScreen(true);
        stage.show();

    }

    public void VisualOutputProf(Stage stage,String status) throws FileNotFoundException {

        Text headning = new Text("Text");
        headning.setScaleX(2);
        headning.setScaleY(2);
        headning.setTranslateX(140);
        headning.setTranslateY(120);
        headning.setFill(Color.WHITE );
        headning.setFont(Font.font(Font.getFontNames().get(12), FontPosture.REGULAR, 17));


        Button backs = new Button("");

        backs.setGraphic(new ImageView("Pictures/backArrow - Copy.png"));
        backs.setTranslateX(0);
        backs.setTranslateY(340);
        backs.setPrefSize(1, 5);
        backs.setTextFill(Color.YELLOW);




        Image background = new Image(getClass().getClassLoader().getResource("Pictures/newbg.png").toString(), true);
        Pane root = new Pane();
        backs.setOnAction(e -> {
            try {
                String webFile=null;
                if(Names.charAt(0)=='w') webFile=Names.substring(3);
                else
                {
                     webFile=Names.substring(7);
                }

                thirdPage tp=new thirdPage();
                tp.app(stage,webFile);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        TextArea textField = new TextArea();
        textField.setLayoutX(80);
        textField.setLayoutY(200);
        textField.setPrefRowCount(5);
        textField.setPrefColumnCount(6);
        textField.setWrapText(true);
        textField.setMinSize(425, 450);
        textField.setText(status);
        textField.setFont(javafx.scene.text.Font.font("Comic Sans MS", FontWeight.BOLD, 18));



        double []value=new double[8];
        value[0]=JoyCal;  value[1]=SurpriseCal;  value[2]=AngerCal;  value[3]=anticipationCal;  value[4]=SadnessCal;  value[5]=DisgustCal;  value[6]=FearCal;  value[7]=TrustCal;  value[0]=JoyCal;
        PieChart pieChart = pieChart(value);
        int max=0;

        for(int i=0;i<8;i++)
        {
            if(max<value[i])max=i;
        }

        Image happy = new Image(new FileInputStream("src/Pictures/gif/happy.gif"));
        ImageView ivH = new ImageView(happy);
        ivH.setX(480);
        ivH.setY(320);
        ivH.setScaleX(0.7);
        ivH.setScaleY(0.7);
        ivH.setPreserveRatio(true);

        Image sad = new Image(new FileInputStream("src/Pictures/gif/sad.gif"));
        ImageView ivS = new ImageView(sad);
        ivS.setX(480);
        ivS.setY(320);
        ivS.setScaleX(0.7);
        ivS.setScaleY(0.7);
        ivS.setPreserveRatio(true);

        Image disgust = new Image(new FileInputStream("src/Pictures/gif/disgust.gif"));
        ImageView ivD = new ImageView(disgust);
        ivD.setX(480);
        ivD.setY(320);
        ivD.setScaleX(0.7);
        ivD.setScaleY(0.7);
        ivD.setPreserveRatio(true);

        Image angry = new Image(new FileInputStream("src/Pictures/gif/angry.gif"));
        ImageView ivA = new ImageView(angry);
        ivA.setX(480);
        ivA.setY(320);
        ivA.setScaleX(0.7);
        ivA.setScaleY(0.7);
        ivA.setPreserveRatio(true);
        if(max==0 || max==1 || max==3 || max==7)
        {
            root.getChildren().addAll(pieChart, backs,textField,headning,ivH);
        }

        else  if(max==2 )
        {
            root.getChildren().addAll(pieChart, backs,textField,headning,ivA);
        }
        else  if(max==4 )
        {
            root.getChildren().addAll(pieChart, backs,textField,headning,ivS);
        }

        else  if(max==5 )
        {
            root.getChildren().addAll(pieChart, backs,textField,headning,ivD);
        }

        else  if(max==6 )
        {
            root.getChildren().addAll(pieChart, backs,textField,headning,ivS);
        }


        //Creating a scene object
        Scene scene = new Scene(root, 1400, 750);

        //Setting title to the Stage
        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        root.setBackground(bg);
        stage.setTitle("Pie Chart");
         //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();


        // Scene scene = new Scene(root, 1600, 800);
        stage.setScene(scene);
        //primaryStage.setFullScreen(true);
        stage.show();

    }

    public void VisualOutputPred(Stage stage, String accessToken, double[]a, double[]b)  {

        Button back = new Button("");
        back.setGraphic(new ImageView("Pictures/backArrow - Copy.png"));
        back.setTranslateX(0);
        back.setTranslateY(340);
        back.setPrefSize(1, 5);
        back.setTextFill(Color.YELLOW);

        //      Image background = new Image(getClass().getClassLoader().getResource("emotionSide.png").toString(), true);
        Pane root = new Pane();

        back.setOnAction(e -> {
            try {

                AnalysisPage ap=new AnalysisPage(stage,Names);
                ap.analysis();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        LinearRegression lr=new LinearRegression(a,b);

        double []pred={lr.predict(JoyCal),lr.predict(SurpriseCal),lr.predict(AngerCal)+lr.predict(anticipationCal), lr.predict( SadnessCal),lr.predict (DisgustCal)+ lr.predict(FearCal),lr.predict(TrustCal)};

        for(int i=0;i<6;i++)
        {
            if(pred[i]<0)pred[i]=0;
        }
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Haha",pred[0]),
                new PieChart.Data("Wow", pred[1]),
                new PieChart.Data("Angry",  pred[2]),
                new PieChart.Data("Love", pred[3]),
                new PieChart.Data("Sad", pred[4]),
                new PieChart.Data("Like",pred[5]));

        Text text = new Text();

        //Setting the text to be added.
        text.setText("    "+ df.format(pred[0])+"%                "+  df.format(pred[1])+"%                "+ df.format(pred[2])+"%                "+ df.format(pred[3])+"%                "+ df.format(pred[4])+"%                "+  df.format(pred[5])+"%");

        //setting the position of the text
        text.setX(30);
        text.setY(690);

        Line line = new Line();
        line.setStartX(640.0);
        line.setStartY(0);
        line.setEndX(640.0);
        line.setEndY(1400.0);
        ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList(
                new PieChart.Data("Joy",JoyCal),
                new PieChart.Data("Surprise",  SurpriseCal),
                new PieChart.Data("Anger",  AngerCal),
                new PieChart.Data("anticipation",  anticipationCal),
                new PieChart.Data("Sadness", SadnessCal),
                new PieChart.Data("Disgust",  DisgustCal),
                new PieChart.Data("Fear",  FearCal),
                new PieChart.Data("Trust",  TrustCal));

        PieChart pieChart1 = new PieChart(pieChartData);
        pieChart1.setTitle("Predicted Reaction");
        pieChart1.setClockwise(true);
        pieChart1.setLabelLineLength(70);
        pieChart1.setLabelsVisible(true);
        pieChart1.setStartAngle(180);
        pieChart1.setTranslateX(50);
        pieChart1.setTranslateY(160);
        pieChart1.setScaleX(1.5);
        pieChart1.setScaleY(1.5);
        PieChart pieChart2 = new PieChart(pieChartData2);
        pieChart2.setTitle("Emotion ");
        pieChart2.setClockwise(true);
        pieChart2.setLabelLineLength(70);
        pieChart2.setLabelsVisible(true);
        pieChart2.setStartAngle(180);

        pieChart2.setTranslateX(710);
        pieChart2.setTranslateY(160);
        pieChart2.setScaleX(1.5);
        pieChart2.setScaleY(1.5);

        Scene scene = new Scene(root, 1400, 750);


        Image background = new Image(getClass().getClassLoader().getResource("Pictures/newbg.png").toString(), true);

        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);

        root.setBackground(bg);
        stage.setTitle("Pie Chart");
        root.getChildren().addAll(pieChart1,pieChart2,line,text, back);
        stage.setScene(scene);
        stage.show();
        back.setOnAction(e -> {
            try {

                firstPost fp=new firstPost(accessToken);
                fp.firstpost(stage,Names);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        stage.setScene(scene);
        stage.show();

    }


    public void VisualOutputs(Stage stage,String accessToken,String Name,String status,int haha,int angry,int sad,int like ,int love,int wow) throws FileNotFoundException {



        Button back = new Button("");
        back.setGraphic(new ImageView("Pictures/backArrow - Copy.png"));
        back.setTranslateX(10);
        back.setTranslateY(150);
        back.setPrefSize(1, 5);
        back.setTextFill(Color.YELLOW);

        //   back.setPrefSize(80, 30);


        back.setTextFill(Color.WHITE);

        Image background = new Image(getClass().getClassLoader().getResource("Pictures/newbg.png").toString(), true);
        back.setOnAction(e -> {
            try {
                firstPost  fp=new firstPost(accessToken);
                fp.firstpost(stage,Name);

//                fourthPage fp=new fourthPage();
//                fp.runs(stage,accessToken,Name);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });



        Image Ab = null;

        Ab = new Image(new FileInputStream("src/Pictures/fb.png"));
        ImageView about = new ImageView(Ab);
        Button regression = new Button(null, about);
        regression.setBackground(null);

        regression.setTranslateX(400);
        regression.setTranslateY(600);

        stage.show();


        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);

        stage.show();


        CategoryAxis xAxis = new CategoryAxis();

        xAxis.setCategories(FXCollections.
                observableArrayList(Arrays.asList("Joy", "Surprise", "Anger", "Sadness","Fear","Love","Trust","Disgust")));
        xAxis.setLabel("category");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("score");


        //Creating the Bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setBarGap(4);
        barChart.setCategoryGap(7);
        barChart.getAnimated();



        barChart.setTitle("Comparison between EMOTION and REACTION");


        //Prepare XYChart.Series objects by setting data
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Emotion");
        series1.getData().add(new XYChart.Data<>("Joy", JoyCal));
        series1.getData().add(new XYChart.Data<>("Surprise", SurpriseCal));
        series1.getData().add(new XYChart.Data<>("Anger", AngerCal));
        series1.getData().add(new XYChart.Data<>("Sadness", SadnessCal));
        series1.getData().add(new XYChart.Data<>("Fear",FearCal));
        series1.getData().add(new XYChart.Data<>("Love", anticipationCal));
        series1.getData().add(new XYChart.Data<>("Trust",TrustCal));
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

barChart.setLayoutX(50);
        barChart.setLayoutY(200);


        //Setting the data to bar chart
        barChart.getData().addAll(series1, series2);

        double [] em=new double[8];

        double []b ={haha,wow,angry, sad, sad,love,like,angry};


        em[0]=JoyCal; em[1]=SurpriseCal; em[2]=AngerCal; em[3]=DisgustCal; em[4]=anticipationCal; em[5]=SadnessCal; em[6]=FearCal; em[7]=TrustCal;





        regression.setOnAction(e -> {
            try {
                try {
                    regressionPrediction rp=new regressionPrediction();
                    rp.prediction(stage,accessToken,em,b);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Text postNo = new Text("Post");
        postNo.setScaleX(4);
        postNo.setScaleY(4);
        postNo.setTranslateX(780);
        postNo.setTranslateY(40);
        postNo.setFill(Color.BLACK);
        postNo.setFont(Font.font(Font.getFontNames().get(12), FontPosture.REGULAR, 9));




        TextArea textField = new TextArea();
        textField.setLayoutX(727);
        textField.setLayoutY(140);
        textField.setPrefRowCount(5);
        textField.setPrefColumnCount(4);
        textField.setWrapText(true);
        textField.setMinSize(425, 500);
        textField.setText(status);


        Pane root = new Pane();
        root.getChildren().addAll(barChart,back,regression,textField,postNo);

        //Creating a scene object
        Scene scene = new Scene(root, 1408, 752);
        root.setBackground(bg);
        //Setting title to the Stage
        stage.setTitle("Bar Chart");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();

    }


}





