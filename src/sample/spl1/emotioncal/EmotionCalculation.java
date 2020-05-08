package sample.spl1.emotioncal;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sample.spl1.Main;
import java.io.IOException;
public class EmotionCalculation {


    int  k = 0, totalEmotionCount = 0;
    int Frequency[] = new int[8];
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


    public void searchEmotion() throws IOException {

        EmotionSearch emse=new EmotionSearch();
        emse.emotionSearch();

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


        JoyCal = (Frequency[0] * 100) / tot;
        SurpriseCal = ((Frequency[1] + exClaimCount) * 100) / tot;
        FearCal = (Frequency[2] * 100) / tot;
        SadnessCal = (Frequency[3] * 100) / tot;
        TrustCal = (Frequency[4] * 100) / tot;
        DisgustCal = (Frequency[5] * 100) / tot;
        anticipationCal = (Frequency[6] * 100) / tot;
        AngerCal = (Frequency[7] * 100) / tot;
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


//        CategoryAxis xAxis = new CategoryAxis();
//        xAxis.setCategories(FXCollections.<String>
//                observableArrayList(Arrays.asList("Speed", "User rating", "Milage", "Safety")));
//        xAxis.setLabel("category");
//
//        NumberAxis yAxis = new NumberAxis();
//        yAxis.setLabel("score");
//
//        //Creating the Bar chart
//        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
//        barChart.setTitle("Comparison between various cars");
//
//        //Prepare XYChart.Series objects by setting data
//        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
//        series1.setName("Fiat");
//        series1.getData().add(new XYChart.Data<>("Speed", 1.0));
//        series1.getData().add(new XYChart.Data<>("User rating", 3.0));
//        series1.getData().add(new XYChart.Data<>("Milage", 5.0));
//        series1.getData().add(new XYChart.Data<>("Safety", 5.0));
//
//        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
//        series2.setName("Audi");
//        series2.getData().add(new XYChart.Data<>("Speed", 5.0));
//        series2.getData().add(new XYChart.Data<>("User rating", 6.0));
//        series2.getData().add(new XYChart.Data<>("Milage", 10.0));
//        series2.getData().add(new XYChart.Data<>("Safety", 4.0));
//
//        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
//        series3.setName("Ford");
//        series3.getData().add(new XYChart.Data<>("Speed", 4.0));
//        series3.getData().add(new XYChart.Data<>("User rating", 2.0));
//        series3.getData().add(new XYChart.Data<>("Milage", 3.0));
//        series3.getData().add(new XYChart.Data<>("Safety", 6.0));
//
//        //Setting the data to bar chart
//        barChart.getData().addAll(series1, series2, series3);
//
//        //Creating a Group object
//        Group root = new Group(barChart);
//
//        //Creating a scene object
//        Scene scene = new Scene(root, 600, 400);
//
//        //Setting title to the Stage
//        stage.setTitle("Bar Chart");
//
//        //Adding scene to the stage
//        stage.setScene(scene);
//
//        //Displaying the contents of the stage
//        stage.show();

    }
}





