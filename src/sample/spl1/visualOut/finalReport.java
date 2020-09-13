package sample.spl1.visualOut;

import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.spl1.AnalysisPage;
import sample.spl1.FuzzyLogic.FuzzyController;
import sample.spl1.emotioncal.EmotionCalculation;
import sample.spl1.emotioncal.EmotionProfile;
import sample.spl1.thirdPage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;

import static java.lang.Math.abs;
public class finalReport {
    DecimalFormat df = new DecimalFormat("0.00");
    private Stage stage;
    private String accessToken;
    private double[] sentimentTot;

    private  double finalTempTotal;
    private String Name;
    public finalReport(Stage stage,String accessToken,double[] sentimentTot,double finalTempTotal,String Name){
        this.stage=stage;
        this.accessToken=accessToken;
        this.finalTempTotal=finalTempTotal;
        this.sentimentTot=sentimentTot;
        this.Name=Name;
        finalreport();

    }

    public finalReport(Stage stage,String accessToken,double[] sentimentTot,double finalTempTotal){
        this.stage=stage;
        this.accessToken=accessToken;
        this.finalTempTotal=finalTempTotal;
        this.sentimentTot=sentimentTot;
        finalreport();

    }
    private void finalreport() {


        Image background = new Image(getClass().getClassLoader().getResource("Pictures/1x/Artboard 1.png").toString(), true);


        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        Pane roota = new Pane();
        roota.setBackground(bg);


        Button backa = new Button("");
        backa.setGraphic(new ImageView("Pictures/backArrow - Copy.png"));
        backa.setTranslateX(0);
        backa.setTranslateY(340);
        backa.setPrefSize(1, 5);
        backa.setTextFill(Color.YELLOW);
        backa.setPrefSize(60, 30);


        backa.setOnAction(esb -> {
            try {
                AnalysisPage goBack = new AnalysisPage(stage, Name);
                goBack.analysis();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        Scene scenea = new Scene(roota, 1400, 750);
        stage.setScene(scenea);
        stage.setFullScreen(true);
        stage.show();
        stage.setTitle("Sentiment Analysis");

//            CategoryAxis xAxiss = new CategoryAxis();
//            xAxiss.setLabel("Sentiment");
//            xAxiss.getCategories().addAll("Positive", "Negative");
//
//            NumberAxis yAxiss = new NumberAxis();
//            yAxiss.setLabel("Intensity");
//
//            StackedBarChart    stackedBarChart = new StackedBarChart(xAxiss, yAxiss);
//            stackedBarChart.getAnimated();
//
//            XYChart.Series dataSeriesSenti1 = new XYChart.Series();
//            dataSeriesSenti1.setName("Positive Sentiment");
//
//            for(int a=1;a<10;a++)
//                dataSeriesSenti1.getData().add(new XYChart.Data("Status "+a, sentimentPos[a]));
//            //    dataSeries1.getData().add(new XYChart.Data("Negative", 540));
//
//            stackedBarChart.getData().add(dataSeriesSenti1);
//
//
//            XYChart.Series dataSeriesSenti2 = new XYChart.Series();
//            dataSeriesSenti2.setName("Negative Sentiment");
//            for(int a=1;a<10 ;a++)
//                dataSeriesSenti2.getData().add(new XYChart.Data("Status "+a  ,sentimentNeg[a]));
//
//
//            stackedBarChart.getData().add(dataSeriesSenti2);
//
//            stackedBarChart.setPrefSize(1100,700);
        // roota.getChildren().addAll(canvasa,stackedBarChart,backa);

        stage.setTitle("Sentiment Progression Over Status");

//            NumberAxis xAxisa = new NumberAxis();
//            xAxisa.setLabel("No of status");
//
//            NumberAxis yAxisa = new NumberAxis();
//            yAxisa.setLabel("Overall Sentiment Score");
//
//            ScatterChart scatterChart = new ScatterChart(xAxisa, yAxisa);
//
//            LineChart linechart =new LineChart(xAxisa, yAxisa);
//            XYChart.Series dataSeriessenti = new XYChart.Series();
//            dataSeriessenti.setName("SENTIMENT SCORE");
//
//            XYChart.Series dataSeriessenti2 = new XYChart.Series();
//
//            dataSeriessenti2.setName("MEAN VALUE");
//
//            for(int t=1;t<=7;t++)
//                dataSeriessenti2.getData().add(new XYChart.Data( t, finalTempTotal));
//            for(int t=1;t<=7;t++)
//            dataSeriessenti.getData().add(new XYChart.Data( t, sentimentTot[t]));
//
//
//            linechart.getData().addAll(dataSeriessenti2);
//            scatterChart.getData().addAll(dataSeriessenti);
//
//            linechart.setPrefSize(1000,700);
//            scatterChart.setPrefSize(1000,700);


//        final LineChart<Number, Number> sc = new LineChart<>(new NumberAxis(), new NumberAxis());
//        sc.setPrefSize(400,400);
//        sc.setScaleX(600);
//        sc.setScaleY(20);
//
//        XYChart.Series series1 = new XYChart.Series();
//        series1.setName("Sentimental Progress");
//

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("No of articles");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Emotion score");

        LineChart lineChart = new LineChart(xAxis, yAxis);
        lineChart.setScaleY(1.5);
        lineChart.setScaleX(1.5);
        lineChart.setLayoutX(600);
        lineChart.setLayoutY(200);
        lineChart.setPrefSize(500, 400);


        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Emotion Progress");

        for (int t = 1; t <= 10; t++)
            series1.getData().add(new XYChart.Data(t - 1, sentimentTot[t]));


        lineChart.getData().add(series1);


        double[] slope = new double[100];
        for (int k = 1; k < 9; k++) {
            slope[k] = (sentimentTot[k + 1] - sentimentTot[k]) / ((k + 1) - k);
            System.out.println("Slopes are " + slope[k]);
        }
        Text textInt = new Text();
        String txt = "";
        String report = null;

        EmotionCalculation emcal = new EmotionCalculation();
        double stdDev = emcal.calculateSD(sentimentTot);
        System.out.println("std dev is :" + stdDev);

        double[] personalityTest =new double[4];
       personalityTest[0] = stdDev / 10;

        if (stdDev < 1) {
            txt = " Low";
            report += "\nYou have difficulty connecting with feelings — others’ and your own; give the impression of being snobby, withholding, or cold; obsess about problems; sometimes too serious.";
        } else if (stdDev < 5) {
            txt = "Medium";
            report += "\nYou are emotionally strong for self and others, practical, able to stay cool in a crisis, nonjudgmental.";
        } else {
            txt = " High";

            report += "\nYou are highly sensitive, naturally giving and a good listener.";
        }

        textInt.setText(txt);
        textInt.setFill(Color.BLACK);
        textInt.setFont(javafx.scene.text.Font.font(null, FontWeight.BOLD, 5));
        textInt.setStyle("-fx-font-size: 10px;");
        textInt.setX(331);
        textInt.setY(168);

        int highSt = 0, lowSt = 0, mediumSt = 0;
        Text textStab = new Text();
        String txtstab = "";
        for (int l = 1; l < 9; l++) {
            if (abs(slope[l]) >= 100) highSt++;
            else if (abs(slope[l]) >= 40) mediumSt++;
            else if (abs(slope[l]) >= 0) lowSt++;

        }


        if (highSt >= mediumSt && highSt >= lowSt) {
            txtstab = "Unstable";
            report += "\n\nYou tend to be a drama king or queen,seek external feedback rather than relying on own intuition, excessive need to share.";

            personalityTest[3] = (highSt) * 1 / (highSt + mediumSt + lowSt);
        } else {
            if (mediumSt >= lowSt) {
                txtstab = "Medium";
                report += "\n\nYou tend to be extremely logical, comfortable with fixing problems logically and intellectually, able to stay calm in emotionally heated situations.";
                personalityTest[3] = (mediumSt) * 1 / (highSt + mediumSt + lowSt);

            } else if (mediumSt < lowSt) {
                txtstab = "Stable";
                report += "\n\nYou are extremely consistent and loyal, respectful, get along with nearly everyone.";
                personalityTest[3] = (lowSt) * 1 / (highSt + mediumSt + lowSt);
            }
        }

        textStab.setText(txtstab);
        textStab.setFill(Color.BLACK);
        textStab.setFont(javafx.scene.text.Font.font(null, FontWeight.BOLD, 5));
        textStab.setStyle("-fx-font-size: 8px;");
        textStab.setX(126);
        textStab.setY(112);


        String txtmean = "";
        if (finalTempTotal <= 25 && finalTempTotal >= 0) txtmean = "Anticipation";
        else if (finalTempTotal <= 50 && finalTempTotal >= 26) txtmean = "Surprise";
        else if (finalTempTotal <= 75 && finalTempTotal > 50) txtmean = "Trust";
        else if (finalTempTotal <= 100 && finalTempTotal > 75) txtmean = "Joy";
        else if (finalTempTotal < 0 && finalTempTotal >= -25) txtmean = "Sadness";
        else if (finalTempTotal < -25 && finalTempTotal >= -50) txtmean = "Fear";
        else if (finalTempTotal < -50 && finalTempTotal >= -75) txtmean = "Anger";
        else if (finalTempTotal < -75 && finalTempTotal >= -100) txtmean = "Disgust";


//        Text textMean= new Text();
//        textMean.setText(txtmean);
//        textMean.setFill(Color.GREEN);
//        textMean.setScaleX(1.5);
//        textMean.setScaleY(3);
//        textMean.setX(800);
//        textMean.setY(450);

        Text textMean = new Text(txtmean);
        textMean.setLayoutX(28);
        textMean.setLayoutY(51);
        textMean.setFill(Color.BLACK);
        textMean.setFont(javafx.scene.text.Font.font(null, FontWeight.BOLD, 8));
        textMean.setStyle("-fx-font-size: 15px;");
        textMean.setX(163);
        textMean.setY(190);



        String txtRec = "";
        if (sentimentTot[1] >= 0) {
            personalityTest[2] = 0.8;
            txtRec = " Positive";
            report += "\n\nAlso you are likely a gifted healer, helper, and friend; intuitive about others’ thoughts and feelings; emotionally responsive; in touch with your body and emotions.";
        } else {
            personalityTest[2] = 0.3;
            txtRec = " Negative";
            report += "\n\nAlso you easily absorb others’ negativity; prone to anxiety, depression, and fatigue; easily feel hemmed in when living with others; difficulty setting boundaries with draining people.";
        }

        Text textRec = new Text(txtRec);
        textRec.setTextOrigin(VPos.TOP);
        textRec.setLayoutX(10);
        textRec.setLayoutY(11);
        textRec.setFill(Color.BLACK);
        textRec.setFont(javafx.scene.text.Font.font(null, FontWeight.BOLD, 5));
        textRec.setStyle("-fx-font-size: 10px;");
        textRec.setX(190);
        textRec.setY(78);


        String txtCon = "";
        int pos = 0, neg = 0;
        for (int k = 0; k < 10; k++) {

            if (slope[k] >= 0) pos++;
            else {
                pos = 0;
            }
            if (pos >= 2) {
                txtCon = "";
                txtCon = "Positive";
                report += "\n\nLast but not the least, you are likely to be practical, able to stay cool in a crisis, nonjudgmental.";
                personalityTest[1] = 0.5;
                break;
            }
            if (slope[k] < 0) neg++;
            else {
                neg = 0;
            }
            if (neg >= 2) {
                txtCon = "";
                txtCon = "Negative";
                report += "\n\nBut your emotional consistency is downward.";
                personalityTest[1] = 0.8;
                break;
            }

        }
        if (pos <= 2 || neg <= 2) {
            txtCon = "No";
            report += "\n\nAlso,you are likely tend to be a drama king or queen as you react too much.";
            personalityTest[1] = 0.2;
        }


        Text textCon = new Text();
        textCon.setText(txtCon);

        textCon.setFill(Color.BLACK);

        textCon.setX(275);
        textCon.setY(117);
        textCon.setFont(javafx.scene.text.Font.font(null, FontWeight.BOLD, 5));
        textCon.setStyle("-fx-font-size: 17px;");


        Text HeadText = new Text();
        HeadText.setText("Your Progress Report");

        HeadText.setFill(Color.BLACK);
        HeadText.setScaleX(2.0);
        HeadText.setScaleY(2);
        HeadText.setX(900);
        HeadText.setY(30);


        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Mean Emotion");
        for (int t = 1; t <= 10; t++)
            series2.getData().add(new XYChart.Data(t - 1, finalTempTotal));

        CategoryAxis xAxisStab = new CategoryAxis();
        xAxisStab.setLabel("Emotional Stability");

        NumberAxis yAxisStab = new NumberAxis();
        yAxisStab.setLabel("Value");

        BarChart barChart = new BarChart(xAxisStab, yAxisStab);
        //   final BarChart<Number, Number> barChart = new BarChart<>(new CategoryAxis(), new NumberAxis());
        barChart.setPrefSize(70, 50);
        XYChart.Series dataSeriesStab = new XYChart.Series();


        dataSeriesStab.setName("Stability");


        dataSeriesStab.getData().add(new XYChart.Data("Unstable", highSt));
        dataSeriesStab.getData().add(new XYChart.Data("Medium", mediumSt));
        dataSeriesStab.getData().add(new XYChart.Data("Stable", lowSt));

        barChart.setPrefSize(50, 50);


        barChart.setScaleX(1.5);
        barChart.setScaleY(1.5);
        barChart.setLayoutX(100);
        barChart.setLayoutY(500);

        barChart.getData().add(dataSeriesStab);
        barChart.setAnimated(false);


        Button next = new Button();
        next.setGraphic(new ImageView("Pictures/backArrow.png"));
        next.setTranslateX(1290);
        next.setTranslateY(340);
        next.setPrefSize(1, 5);
        next.setTextFill(Color.YELLOW);
        String finalReport = report;
        next.setOnAction(e ->
        {
            EmotionProfile emp = new EmotionProfile(Name, stage);
            try {
                emp.profileScore(1, finalReport, personalityTest);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }

        });


        roota.getChildren().addAll(lineChart, backa, textInt, barChart, textStab, textMean, textRec, textCon, HeadText, next);

    }
}
