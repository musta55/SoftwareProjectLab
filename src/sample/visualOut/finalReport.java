package sample.visualOut;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.spl1.emotioncal.EmotionCalculation;
import sample.spl1.fourthPage;
import sample.spl1.thirdPage;

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
    private void finalreport()
    {
        Group roota = new Group();
        Canvas canvasa = new Canvas(1800, 900);


        Button backa = new Button("Back");
        backa.setTranslateX(1100);
        backa.setTranslateY(650);
        backa.setStyle("-fx-padding: 8 15 15 15;\n" +
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
        backa.setPrefSize(60, 30);
        backa.setOnAction(esb->{
            try {
                thirdPage goBack = new thirdPage();
                goBack.app(stage,Name);
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });
        GraphicsContext gca = canvasa.getGraphicsContext2D();
        //      gca.drawImage(backgrounda,0,0);
        Scene scenea = new Scene(roota, 2000, 900);
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


        final LineChart<Number, Number> sc = new LineChart<>(new NumberAxis(), new NumberAxis());
        sc.setPrefSize(400,400);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Sentimental Progress");
        for (int t = 1; t <= 10; t++)
            series1.getData().add(new XYChart.Data(t - 1, sentimentTot[t]));
        double [] slope=new double[100];
        for(int k=1;k<9;k++)
        {
            slope[k]=(sentimentTot[k+1]-sentimentTot[k])/((k+1)-k);
            System.out.println("Slopes are "+slope[k]);
        }
        Text textInt = new Text();
        String txt="";

        EmotionCalculation emcal=new EmotionCalculation();
        double stdDev= emcal.calculateSD(sentimentTot);
        System.out.println("std dev is :"+stdDev);

        if(stdDev<1)txt= "Emotional Power : Low ("+df.format(stdDev)+")";
        else  if(stdDev<5)txt= "Emotional Power : Medium ("+df.format(stdDev)+")";
        else txt= "Emotional Power : High  ("+df.format(stdDev)+")";

        textInt.setText(txt);
        textInt.setFill(Color.GREEN);
        textInt.setScaleX(1.5);
        textInt.setScaleY(3);
        textInt.setX(800);
        textInt.setY(150);

        int highSt=0,lowSt=0,mediumSt=0;
        Text textStab= new Text();
        String txtstab="";
        for(int l=1;l<9;l++)
        {
            if(abs(slope[l])>=100)highSt++;
            else if(abs(slope[l])>=40)mediumSt++;
            else if(abs(slope[l])>=0)lowSt++;

        }

        if(highSt>=mediumSt && highSt>=lowSt )
        {
            txtstab="Emotional Stability : Unstable";
        }
        else
        {
            if(mediumSt>=lowSt) txtstab="Emotional Stability : Medium";
            else if(mediumSt<lowSt) txtstab="Emotional Stability : Stable";
        }




        textStab.setText(txtstab);
        textStab.setFill(Color.GREEN);
        textStab.setScaleX(1.5);
        textStab.setScaleY(3);
        textStab.setX(800);
        textStab.setY(280);


        String txtmean="";
        if(finalTempTotal<=25 && finalTempTotal>=0)txtmean= "Combined Emotion :Anticipation";
        else  if(finalTempTotal<=50 && finalTempTotal>=26)txtmean= "Combined Emotion :Surprise";
        else  if(finalTempTotal<=75 && finalTempTotal>50)txtmean= "Combined Emotion :Trust";
        else  if(finalTempTotal<=100 && finalTempTotal>75)txtmean= "Combined Emotion :Joy";
        else  if(finalTempTotal<0 && finalTempTotal>=-25)txtmean= "Combined Emotion :Sadness";
        else  if(finalTempTotal<-25 && finalTempTotal>=-50)txtmean= "Combined Emotion :Fear";
        else  if(finalTempTotal<-50 && finalTempTotal>=-75)txtmean= "Combined Emotion :Anger";
        else  if(finalTempTotal<-75 && finalTempTotal>=-100)txtmean= "Combined Emotion :Disgust";



        Text textMean= new Text();
        textMean.setText(txtmean);
        textMean.setFill(Color.GREEN);
        textMean.setScaleX(1.5);
        textMean.setScaleY(3);
        textMean.setX(800);
        textMean.setY(450);



        String txtRec="";
        if(sentimentTot[1]>=0)txtRec="Recent Post : Positive";
        else txtRec="Recent Post : Negative";
        Text textRec= new Text();



        textRec.setFill(Color.GREEN);
        textRec.setScaleX(1.5);
        textRec.setScaleY(3);
        textRec.setText(txtRec);
        textRec.setX(800);
        textRec.setY(550);




        String txtCon="";
        int pos=0,neg=0;
        for(int k=0;k<10;k++)
        {
            if(pos<=3 || neg<=3) txtCon = "Consistency : No Consistency";
            if(slope[k]>=0)pos++;
            else {
                pos=0;
            }
            if(pos>=3) {
                txtCon="";
                txtCon = "Consistency : Positive";
                break;

            }


            if(slope[k]<0)neg++;
            else {
                neg=0;
            }
            if(neg>=3) {
                txtCon="";
                txtCon = "Consistency : Negative";
                break;
            }

        }


        Text textCon= new Text();
        textCon.setText(txtCon);

        textCon.setFill(Color.GREEN);
        textCon.setScaleX(1.5);
        textCon.setScaleY(3);
        textCon.setX(800);
        textCon.setY(650);


        Text HeadText= new Text();
        HeadText.setText("Your Final Report");

        HeadText.setFill(Color.DARKRED);
        HeadText.setScaleX(3.4);
        HeadText.setScaleY(4.4);
        HeadText.setX(900);
        HeadText.setY(50);




        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Mean Emotion");
        for (int t = 1; t <= 10; t++)
            series2.getData().add(new XYChart.Data(t - 1, finalTempTotal));

        CategoryAxis xAxisStab    = new CategoryAxis();
        xAxisStab.setLabel("Stability");

        NumberAxis yAxisStab = new NumberAxis();
        yAxisStab.setLabel("Value");

        BarChart barChart = new BarChart(xAxisStab, yAxisStab);
        //   final BarChart<Number, Number> barChart = new BarChart<>(new CategoryAxis(), new NumberAxis());
        barChart.setPrefSize(70,50);
        XYChart.Series dataSeriesStab = new XYChart.Series();


        dataSeriesStab.setName("Stability");


        dataSeriesStab.getData().add(new XYChart.Data("Unstable", highSt));
        dataSeriesStab.getData().add(new XYChart.Data("Medium"  , mediumSt));
        dataSeriesStab.getData().add(new XYChart.Data("Stable"  , lowSt));

        barChart.setPrefSize(50,50);


        barChart.setScaleX(1.5);
        barChart.setScaleY(1.5);
        barChart.setLayoutX(1000);
        barChart.setLayoutY(200);

        barChart.getData().add(dataSeriesStab);
        barChart.setAnimated(false);
        sc.setAnimated(false);
        sc.setCreateSymbols(true);


        sc.getData().addAll(series1, series2);
        sc.setPrefSize(650, 700);


        roota.getChildren().addAll(canvasa,sc,backa,textInt,barChart,textStab,textMean,textRec,textCon,HeadText);

    }
}
