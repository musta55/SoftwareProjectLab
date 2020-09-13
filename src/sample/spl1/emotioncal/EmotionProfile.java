package sample.spl1.emotioncal;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
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
import sample.spl1.DoughnutChart;
import sample.spl1.FuzzyLogic.FuzzyController;
import sample.spl1.thirdPage;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class EmotionProfile {

   private String name;
    private Stage stage;


    double[] out3 = new double[100];
   public EmotionProfile(String name,Stage stage)
   {
       this.name=name;
       this.stage=stage;
   }
    public double[] fileInput(String Inname)
    {
        double[] fileData = new double[300];
        Scanner scan;
        int m = 0;
        File file = new File(Inname);
        try {
            scan = new Scanner(file);
                String currentLine = scan.nextLine();
                String[] firstSplits = currentLine.split(" ", 0);
                for (; m < firstSplits.length; m++) {
                    //     System.out.println("String is " + firstSplits[m]);
                    try {
                        if (firstSplits[m] == "NaN" || firstSplits[m] == "Infinity");
                        else {
                            fileData[m] = Double.parseDouble(firstSplits[m]);
                            System.out.print("i = "+m+ " "+fileData[m] + "          ");
                        }
                    } catch (Exception q) {
                        System.out.println("File data input error");
                    }
                    //     System.out.println("Value is " + out[m]);
                }
                // double d = Double.parseDouble();
            return fileData;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return fileData;
        }
    }
    public  void profileScore(int t,String report,double[] personalityTest) throws FileNotFoundException {


        double[] emo = new double[10];
        double[] emo2 = new double[10];
        double[] emo3 = new double[10];
        int m = 0,count=0;
        double[] out;
         out =fileInput("web"+name);

        double[] out2;
         out2 =fileInput("article"+name);
        double[] out3;
         out3 =fileInput("fb"+name);

        System.out.println("Web file name is :"+"web"+name);

        System.out.println("\nWeb File reading "+out);
        for(int i=0;i<out.length;i++) System.out.print(out[i]+" ");
        System.out.println("\nArticle file reading "+out);
        for(int i=0;i<out2.length;i++) System.out.print(out2[i]+" ");
        System.out.println("\nFacebook file reading "+out);
        for(int i=0;i<out3.length;i++) System.out.print(out3[i]+" ");



        int j=0;
        for(int i=0,k=0;i<8;i++)
        {
            for( j=k;j<out.length;j+=8)
            {
                emo[i]+=out[j];
            }
            k++;
        }
        double tot=0;                       //Web Article
        for(int i=0;i<8;i++)
        {
            tot+=emo[i];
        }

        for(int i=0;i<8;i++)
        {
            emo[i]=emo[i]*100/tot;
        }

        for(int i=0,k=0;i<8;i++)
        {
            for( j=k;j<out2.length;j+=8)
            {
                emo2[i]+=out2[j];
            }
            k++;

        }

        double tot2=0;                  //Article
        for(int i=0;i<8;i++)
        {
            tot2+=emo2[i];
        }

        for(int i=0;i<8;i++)
        {
            emo2[i]=emo2[i]*100/tot2;
        }


        for(int i=0,k=0;i<8;i++)
        {
            for( j=k;j<out3.length;j+=8)
            {
                emo3[i]+=out3[j];
            }
            k++;

        }

        double tot3=0;                                       //Facebook
        for(int i=0;i<8;i++)
        {
            tot3+=emo3[i];
        }

        for(int i=0;i<8;i++)
        {
            emo3[i]=emo3[i]*100/tot3;
        }


      for(int i=0;i<8;i++)
      {
          System.out.println("article :"+emo[i]);
          System.out.println("Web :"+emo2[i]);
          System.out.println("Facebook :"+emo3[i]);
      }




        System.out.println("Joy =" +emo[0]);
        System.out.println("Surprised ="+emo[1]);
        System.out.println("Fear =" +emo[2]);
        System.out.println("Sadness =" + emo[3]);
        System.out.println("Trust =" + emo[4] );
        System.out.println("Disgust =" + emo[5]);
        System.out.println("anticipation=" + emo[6]);
        System.out.println("Anger=" +emo[7] );




        int temp = 0, temp2 = 0;


        double firstNum = 0;
        double secondNum = 0;

        for(int i = 0; i <8; i++){
            if(firstNum < emo[i]){
                secondNum = firstNum;
                firstNum = emo[i];
                temp=i;
            }else if(secondNum < emo[i]){
                secondNum = emo[i];
                temp2=i;
            }
        }

        System.out.println("Highest is " + temp + " Second is " + temp2);
if(t==1)
       VisualProfile(emo,comment(temp,temp2)+report,personalityTest);
else
       IndividualVisualProfile(emo,emo2,emo3);

    }




    public String comment(int temp,int temp2)
    {
        String text = null;



        if (temp == 0) {
            text = "Joyful emotion is mainly focused on your text.";
        } else if (temp == 1) {
            text = "Your emotion is full of surprises. As a person,you are curious.Surprising things happening around you.\n";
        } else if (temp == 2) {
            text = "Fear emotion is your primary emotion.\n\n";
        } else if (temp == 3) {
            text = "You seemed a bit depressed.Sadness grabs you severely.\n";
        } else if (temp == 4) {
            text = "Trust emotion is reflected greatly on your articles.You accept and adapt things happening around you wisely.\n";
        } else if (temp == 5) {
            text = "Disgust and boredom is the key of your articles.Hate speech is consistently found in your articles.\n";
        } else if (temp == 6) {
            text = "You seems like a happy person.Keep smiling that reflects on your posts.\n";
        } else if (temp == 7) {
            text = "Anger emotion is consistently reflected on your articles and posts of social Media.\n";

        }



        String text2 = null;


        if (temp2 == 0) {
            text2 = "Also Joyful emotion is partially focused on your text.\n\n";
        } else if (temp2 == 1) {
            text2 = "Also Surprise  emotion is partially focused on your text.\n\n";
        } else if (temp2 == 2) {
            text2 = "Also sadness is particularly reflected on your posts.\n\n";
        } else if (temp2 == 4) {
            text2 = "Besides, trust emotion is reflected greatly on your articles.\n\n";
        } else if (temp2 == 5) {
            text2 = "Moreover, disgust and boredom is the key of your articles.Hate speeches are consistently found in your articles.\n\n";
        } else if (temp2 == 6) {
            text2 = "Furthermore,It is really pleased to say that you seems like a happy person.\n";
        } else if (temp2 == 7) {
            text2 = "Apart from this, anger emotion plays a vital role.May be you don't realize it.But your mind reflects it.\n\n";

        }
        return text+text2;

    }

    public void VisualProfile(double[] em,String text,double[] personalityTest) {
       Stage stage=new Stage();

        Button back = new Button("");
        back.setTranslateX(1100);
        back.setTranslateY(650);
        back.setTextFill(Color.WHITE);
        back.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #8d9092 0%, #717375 100%),\n" +
                "        #8d9092,\n" +
                "        #717375,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #2471A3    , #17202A);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 1.1em;");
        back.setPrefSize(60, 30);

     Image background = new Image(getClass().getClassLoader().getResource("Pictures/1x/Blue-Background-4.jpg").toString(), true);
        Pane root = new Pane();
        back.setOnAction(e -> {
            try {

                thirdPage tp=new thirdPage();
                tp.app(stage,name);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Text HeadText= new Text();
        HeadText.setText("Your Final Report");

        HeadText.setFill(Color.BLACK);
        HeadText.setX(900);
        HeadText.setY(30);
        HeadText.setScaleX(5);
        HeadText.setScaleY(5);

        TextArea textField = new TextArea();
        textField.setLayoutX(630);
        textField.setLayoutY(60);
        textField.setPrefRowCount(10);
        textField.setPrefColumnCount(12);
        textField.setWrapText(true);
        textField.setMinSize(725, 350);

        textField.skinProperty().addListener(new ChangeListener<Skin<?>>() {

            @Override
            public void changed(
                    ObservableValue<? extends Skin<?>> ov, Skin<?> t, Skin<?> t1) {
                if (t1 != null && t1.getNode() instanceof Region) {
                    Region r = (Region) t1.getNode();
                    r.setBackground(Background.EMPTY);

                    r.getChildrenUnmodifiable().stream().
                            filter(n -> n instanceof Region).
                            map(n -> (Region) n).
                            forEach(n -> n.setBackground(Background.EMPTY));

                    r.getChildrenUnmodifiable().stream().
                            filter(n -> n instanceof Control).
                            map(n -> (Control) n).
                            forEach(c -> c.skinProperty().addListener(this)); // *
                }
            }
        });

        textField.setStyle("-fx-text-fill: black; -fx-font-size: 20px;");

        textField.setFont(Font.font("Comic Sans MS", FontWeight.SEMI_BOLD, 22));
        textField.setText(text);

        CategoryAxis xAxises = new CategoryAxis();
        xAxises.setCategories(FXCollections.<String>
                observableArrayList(Arrays.asList("Joy", "Surprise", "Fear", "Sadness", "Trust", "Disgust", "anticipation", "Anger")));
        xAxises.setLabel("EMOTION");

        NumberAxis yAxises = new NumberAxis();
        yAxises.setLabel("INTENSITY");

        //Creating the Bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxises, yAxises);
        barChart.setTitle("Final Emotion");
        //Prepare XYChart.Series objects by setting data
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("EMOTION");

        for(Node n:barChart.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: yellow;");
        }
        series1.getData().add(new XYChart.Data<>("Joy", em[0]));
        series1.getData().add(new XYChart.Data<>("Surprise",  em[1]));
        series1.getData().add(new XYChart.Data<>("Fear",  em[2]));
        series1.getData().add(new XYChart.Data<>("Sadness",  em[3]));
        series1.getData().add(new XYChart.Data<>("Trust",  em[4]));
        series1.getData().add(new XYChart.Data<>("Disgust",  em[5]));
        series1.getData().add(new XYChart.Data<>("anticipation",  em[6]));
        series1.getData().add(new XYChart.Data<>("Anger",  em[7]));
        barChart.setTranslateX(40);
        barChart.setTranslateY(80);
        barChart.setScaleX(1.2);
        barChart.setScaleY(1.2);

        Button personalilty = new Button("Personality Test");
        //   backa.setGraphic(new ImageView("Pictures/backArrow - Copy.png"));
        personalilty.setTranslateX(700);
        personalilty.setTranslateY(440);
        personalilty.setPrefSize(1, 5);
        personalilty.setTextFill(Color.WHITE);
     //   personalilty.setPrefSize(200, 70);
        personalilty.setStyle("-fx-background-color: \n" +
                "        linear-gradient(#14FF14, #14FF14),\n" +
                "        linear-gradient(#14FF14, #14FF14),\n" +
                "        linear-gradient(#14FF14, #14FF14),\n" +
                "        linear-gradient(#ffe657 0%, #ffe657 50%, #FFAA00 100%),\n" +
                "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n" +
                "-fx-background-radius: 5em; " +
                "-fx-min-width: 300px; " +
                "-fx-min-height: 150px; " +
                "-fx-max-width: 300px; " +
                "-fx-max-height: 150px;"+
                "    -fx-text-fill: #FFFFE6;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 2.9em;\n" +
                "    -fx-padding: 10 10 10 10;");

        personalilty.setTextFill(Color.BLACK);


        personalilty.setOnAction(esb->{
            try {
                FuzzyController t = new FuzzyController(personalityTest);

            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });

        //second bar color

        barChart.getData().addAll(series1);

        //Creating a scene object
        Scene scene = new Scene(root, 1400, 755);


        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        root.setBackground(bg);
        stage.setTitle("Bar Chart");
        root.getChildren().addAll(barChart, back,textField,HeadText,personalilty);
        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();



        // Scene scene = new Scene(root, 1600, 800);
        stage.setScene(scene);
        //primaryStage.setFullScreen(true);
        stage.show();

    }

    public void IndividualVisualProfile(double[] em,double[] em2,double[] em3) {
        Text headning = new Text("Article");
        headning.setScaleX(2);
        headning.setScaleY(2);
        headning.setTranslateX(540);
        headning.setTranslateY(90);
        headning.setFill(Color.rgb(150, 170, 130 ));
        headning.setFont(Font.font(Font.getFontNames().get(12), FontPosture.REGULAR, 14));

        Text headning2 = new Text("FaceBook");
        headning.setScaleX(2);
        headning.setScaleY(2);
        headning.setTranslateX(60);
        headning.setTranslateY(90);
        headning.setFill(Color.rgb(150, 170, 130 ));
        headning.setFont(Font.font(Font.getFontNames().get(12), FontPosture.REGULAR, 14));


        Text headning3 = new Text("Web Article");
        headning.setScaleX(2);
        headning.setScaleY(2);
        headning.setTranslateX(60);
        headning.setTranslateY(620);
        headning.setFill(Color.rgb(150, 170, 130 ));
        headning.setFont(Font.font(Font.getFontNames().get(12), FontPosture.REGULAR, 14));

        Button back = new Button("");
        back.setGraphic(new ImageView("Pictures/backArrow - Copy.png"));
        back.setTranslateX(0);
        back.setTranslateY(340);
        back.setPrefSize(1, 5);
        back.setTextFill(Color.YELLOW);

      //  Image background = new Image(getClass().getClassLoader().getResource("Pictures/blueprint-grid-paper.jpg").toString(), true);
        Pane root = new Pane();
        back.setOnAction(e -> {
            try {

                thirdPage tp=new thirdPage();
                tp.app(stage,name);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Joy", em[0]),
                new PieChart.Data("Surprise", em[1]),
                new PieChart.Data("Anger", em[2]),
                new PieChart.Data("Anticipation", em[3]),
                new PieChart.Data("Sadness", em[4]),
                new PieChart.Data("Disgust",  em[5]),
                new PieChart.Data("Fear",  em[6]),
                new PieChart.Data("Trust",  em[7]));

        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Article ");
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(50);
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(180);
        pieChart.setTranslateX(50);
        pieChart.setTranslateY(10);
        pieChart.setScaleX(0.8);
        pieChart.setScaleY(0.8);


        ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList(
                new PieChart.Data("Joy", em2[0]),
                new PieChart.Data("Surprise", em2[1]),
                new PieChart.Data("Anger", em2[2]),
                new PieChart.Data("Anticipation", em2[3]),
                new PieChart.Data("Sadness", em2[4]),
                new PieChart.Data("Disgust",  em2[5]),
                new PieChart.Data("Fear",  em2[6]),
                new PieChart.Data("Trust",  em2[7]));

        PieChart pieChart2 = new PieChart(pieChartData2);
        pieChart2.setTitle("Web");
        pieChart2.setClockwise(true);
        pieChart2.setLabelLineLength(50);
        pieChart2.setLabelsVisible(true);
        pieChart2.setStartAngle(180);
        pieChart2.setTranslateX(50);
        pieChart2.setTranslateY(350);
        pieChart2.setScaleX(0.8);
        pieChart2.setScaleY(0.8);


        CategoryAxis xAxises3 = new CategoryAxis();
        xAxises3.setCategories(FXCollections.<String>
                observableArrayList(Arrays.asList("Joy", "Surprise", "Fear", "Sadness", "Trust", "Disgust", "anticipation", "Anger")));
        xAxises3.setLabel("FaceBook");

        NumberAxis yAxises3 = new NumberAxis();
        yAxises3.setLabel("INTENSITY");

        BarChart<String, Number> barChart3 = new BarChart<>(xAxises3, yAxises3);
        barChart3.setTitle("FaceBook");
        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("Aggregated Emotion");

        series3.getData().add(new XYChart.Data<>("Joy", em3[0]));
        series3.getData().add(new XYChart.Data<>("Surprise",  em3[1]));
        series3.getData().add(new XYChart.Data<>("Fear",  em3[2]));
        series3.getData().add(new XYChart.Data<>("Sadness",  em3[3]));
        series3.getData().add(new XYChart.Data<>("Trust",  em3[4]));
        series3.getData().add(new XYChart.Data<>("Disgust",  em3[5]));
        series3.getData().add(new XYChart.Data<>("anticipation",  em3[6]));
        series3.getData().add(new XYChart.Data<>("Anger",  em3[7]));


        barChart3.setTranslateX(640);
        barChart3.setTranslateY(40);
        barChart3.setScaleX(1.5);
        barChart3.setScaleY(1.0);

        ObservableList<PieChart.Data> sentChartData3 =  FXCollections.observableArrayList(
                new PieChart.Data("Positive", em3[0]+em3[1]+em3[4]+em3[6]),
                new PieChart.Data("Negative", em3[2]+em3[3]+em3[5]+em3[7]));

        final DoughnutChart chart3 = new DoughnutChart(sentChartData3);
        chart3.setTranslateX(400);
        chart3.setTranslateY(400);
        chart3.setScaleX(0.6);
        chart3.setScaleY(0.6);
        chart3.setTitle("Facebook");



        ObservableList<PieChart.Data> sentChartData2 =  FXCollections.observableArrayList(
                new PieChart.Data("Positive", em2[0]+em2[1]+em2[4]+em2[6]),
                new PieChart.Data("Negative", em2[2]+em2[3]+em2[5]+em2[7]));

        final DoughnutChart chart2 = new DoughnutChart(sentChartData2);
        chart2.setTranslateX(700);
        chart2.setTranslateY(400);
        chart2.setScaleX(0.6);
        chart2.setScaleY(0.6);
        chart2.setTitle("Web article");

        ObservableList<PieChart.Data> sentChartData =  FXCollections.observableArrayList(
                new PieChart.Data("Positive", em[0]+em[1]+em[4]+em[6]),
                new PieChart.Data("Negative", em[2]+em[3]+em[5]+em[7]));

        final DoughnutChart chart = new DoughnutChart(sentChartData);
        chart.setTranslateX(1000);
        chart.setTranslateY(400);
        chart.setScaleX(0.6);
        chart.setScaleY(0.6);
        chart.setTitle("Article");

        Text visu = new Text("Emotion Visualization");
        visu.setScaleX(6);
        visu.setScaleY(6);
        visu.setTranslateX(650);
        visu.setTranslateY(20);
        visu.setFill(Color.rgb(237, 134, 18));
        visu.setFont(Font.font(Font.getFontNames().get(12), FontPosture.REGULAR, 12));



        barChart3.getData().addAll(series3);
        //Creating a scene object
        Scene scene = new Scene(root, 1400, 755);

        stage.setTitle("Bar Chart");
        root.getChildren().addAll(pieChart,pieChart2,barChart3, back,headning,headning2,headning3,chart,chart2,chart3,visu);
        stage.setScene(scene);

        stage.show();

        stage.setScene(scene);
        stage.show();

    }


}
