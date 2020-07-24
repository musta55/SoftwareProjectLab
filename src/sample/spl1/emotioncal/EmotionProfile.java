package sample.spl1.emotioncal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.spl1.thirdPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class EmotionProfile {

   private String name;
    private Stage stage;
   public EmotionProfile(String name,Stage stage)
   {
       this.name=name;
       this.stage=stage;
   }

    public  void profileScore() throws FileNotFoundException {
        DecimalFormat df = new DecimalFormat("0.00");
        Scanner scan;
        File file = null;
        file = new File(name);
        scan = new Scanner(file);
        double[] out = new double[10000];
        double[] out2 = new double[10000];
        double[] out3 = new double[10000];
        double[] emo = new double[10000];
        double[] emo2 = new double[10000];
        double[] emo3 = new double[10000];
        int m = 0,count=0;
        while (scan.hasNextLine()) {
            count++;
            String currentLine = scan.nextLine();
            // System.out.println("Current Line "+currentLine);
            String[] firstSplits = currentLine.split(" ", 0);
            for (; m < firstSplits.length; m++) {
                //     System.out.println("String is " + firstSplits[m]);
                try {
                    if (firstSplits[m] == "NaN" || firstSplits[m] == "Infinity");
                    else {
                        if(count==1)   out[m] = Double.parseDouble(firstSplits[m]);
                        if(count==2)   out2[m] = Double.parseDouble(firstSplits[m]);
                        if(count==3)   out3[m] = Double.parseDouble(firstSplits[m]);
                        System.out.print("i = "+m+ " "+out[m] + "          ");
                    }
                } catch (Exception q) {
                    out[m] = 0.000;
                }
                //     System.out.println("Value is " + out[m]);
            }
            // double d = Double.parseDouble();
        }
        System.out.println("Set is :"+m/8 +" Set");
        int j=0,tmp=m;
        for(int i=0,k=0;i<8;i++)
        {
            for( j=k;j<tmp;j+=8)
            {
                emo[i]+=out[j];
            }
            k++;
            emo[i]/=(m/8);
        }
        tmp=m;
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
            for( j=k;j<tmp;j+=8)
            {
                emo2[i]+=out2[j];
            }
            k++;
            emo2[i]/=(m/8);
        }
        tmp=m;

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
            for( j=k;j<tmp;j+=8)
            {
                emo3[i]+=out3[j];
            }
            k++;
            emo3[i]/=(m/8);
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
        String text = null;


        if (temp == 0) {
            text = "  That is fantastic! You have a kind heart and jolly mind.Your articles are full of serenity and ecstasy.Joyful emotion is mainly focused on your text.";
        } else if (temp == 1) {
            text = "WoW !! Your emotion is full of surprises. As a person,you are curious.Surprising things happening around you.Live with amazement";
        } else if (temp == 2) {
            text = "Listen Carefully.Are you fear of something?Or may be some terrible things is happening in your mind as fear emotion is your primary emotion.Stay safe and sound.";
        } else if (temp == 3) {
            text = "Hmm ,you seemed a bit depressed.Sadness grabs you severely.Hopefully you will overcome your pain as soon as you can.Best wishes.";
        } else if (temp == 4) {
            text = "Trust emotion is reflected greatly on your articles.You accept and adapt things happening around you wisely.Have enough courage to trust and accept things changing everyday.";
        } else if (temp == 5) {
            text = "Disgust and boredom is the key of your articles.Hate speech is consistently found in your articles.";
        } else if (temp == 6) {
            text = "Hey!!It is really pleased to say that you seems like a happy person.Keep smiling that reflects on your posts.Spread positivity and be optimistic.";
        } else if (temp == 7) {
            text = "Excuse me?Do you get angry a lot?May be you don't realize.But your mind reflects it.";

        }



        String text2 = null;


        if (temp2 == 0) {
            text2 = "Also your articles are full of serenity and ecstasy.Joyful emotion is partially focused on your text.";
        } else if (temp2 == 1) {
            text2 = "Your emotion is full of surprises. As a person,you are curious too.Live with amazement.";
        } else if (temp2 == 2) {
            text2 = "And also there are terrible things  happening in your mind as fear emotion is also reflected.Stay safe and sound.";
        } else if (temp2== 3) {
            text2 = "Hmm ,it seems that you are a bit depressed.Sadness grabs you severely.Hopefully you will overcome your pain as soon as you can.Best wishes.";
        } else if (temp2 == 4) {
            text2 = "Besides, trust emotion is reflected greatly on your articles.You accept and adapt things happening around you wisely.Have enough courage to trust and accept things changing everyday.";
        } else if (temp2 == 5) {
            text2 = "Moreover,disgust and boredom is the key of your articles.Hate speeches are consistently found in your articles.";
        } else if (temp2 == 6) {
            text2 = "Furthermore,It is really pleased to say that you seems like a happy person.Keep smiling that reflects on your posts.Spread positivity and be optimistic.";
        } else if (temp2 == 7) {
            text2 = "apart from this, anger emotion plays a vital role.May be you don't realize it.But your mind reflects it.";

        }

       VisualProfile(emo,text+text2);

       IndividualVisualProfile(emo,emo2,emo3);
        System.out.println(text+text2);




    }

    public void VisualProfile(double[] em,String text) {
       Stage stage=new Stage();
        Text headning = new Text("Result");
        headning.setScaleX(2);
        headning.setScaleY(2);
        headning.setTranslateX(640);
        headning.setTranslateY(90);
        headning.setFill(Color.rgb(150, 170, 130 ));
        headning.setFont(Font.font(Font.getFontNames().get(12), FontPosture.REGULAR, 14));


        Button back = new Button("Back");
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

     Image background = new Image(getClass().getClassLoader().getResource("Pictures/1x/emotion(16-9)-0-3.jpg").toString(), true);
        Pane root = new Pane();
        back.setOnAction(e -> {
            try {

                thirdPage tp=new thirdPage();
                tp.app(stage,name);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });



        TextArea textField = new TextArea();
        textField.setLayoutX(630);
        textField.setLayoutY(140);
        textField.setPrefRowCount(5);
        textField.setPrefColumnCount(6);
        textField.setWrapText(true);
        textField.setMinSize(525, 350);
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

        series1.getData().add(new XYChart.Data<>("Joy", em[0]));
        series1.getData().add(new XYChart.Data<>("Surprise",  em[1]));
        series1.getData().add(new XYChart.Data<>("Fear",  em[2]));
        series1.getData().add(new XYChart.Data<>("Sadness",  em[3]));
        series1.getData().add(new XYChart.Data<>("Trust",  em[4]));
        series1.getData().add(new XYChart.Data<>("Disgust",  em[5]));
        series1.getData().add(new XYChart.Data<>("anticipation",  em[6]));
        series1.getData().add(new XYChart.Data<>("Anger",  em[7]));
        barChart.setTranslateX(40);
        barChart.setTranslateY(140);
        barChart.setScaleX(1.2);
        barChart.setScaleY(1.2);

        barChart.setStyle("\"-fx-padding: 8 15 15 15;\\n\" +\n" +
                "                \"    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\\n\" +\n" +
                "                \"    -fx-background-radius: 8;\\n\" +\n" +
                "                \"    -fx-background-color: \\n\" +\n" +
                "                \"        linear-gradient(from 0% 93% to 0% 100%, #8d9092 0%, #717375 100%),\\n\" +\n" +
                "                \"        #8d9092,\\n\" +\n" +
                "                \"        #717375,\\n\" +\n" +
                "                \"        radial-gradient(center 50% 50%, radius 100%, #2471A3    , #17202A);\\n\" +\n" +
                "                \"    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\\n\" +\n" +
                "                \"    -fx-font-weight: bold;\\n\" +\n" +
                "                \"    -fx-font-size: 1.1em;\"");


//        //second bar color
//        for(Node n:barChart.lookupAll(".default-color1.chart-bar")) {
//            n.setStyle("-fx-bar-fill: green;");
//        }




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
        root.getChildren().addAll(barChart, back,textField,headning);
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
        pieChart.setScaleX(0.9);
        pieChart.setScaleY(0.9);


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
        pieChart2.setScaleX(0.9);
        pieChart2.setScaleY(0.9);


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


        barChart3.setTranslateX(740);
        barChart3.setTranslateY(100);
        barChart3.setScaleX(1.5);
        barChart3.setScaleY(1.5);


        barChart3.getData().addAll(series3);
        //Creating a scene object
        Scene scene = new Scene(root, 1400, 755);

        stage.setTitle("Bar Chart");
        root.getChildren().addAll(pieChart,pieChart2,barChart3, back,headning,headning2,headning3);
        stage.setScene(scene);

        stage.show();

        stage.setScene(scene);
        stage.show();

    }

}
