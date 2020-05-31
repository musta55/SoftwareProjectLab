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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import sample.spl1.*;
import sample.spl1.emotioncal.EmotionCalculation;
public class visualoutputfb {
    public static String s=null;
    public static void VisualOutputFacebook(Stage stage) throws FileNotFoundException {

        Scanner scan;
        File file = null;
        file = new File("src/sample/spl1/out.txt");
        scan = new Scanner(file);
        double[] out=new double[10000];
        double[] sentimentPos=new double[1000];
        double[] sentimentNeg=new double[1000];
        double[] sentimentTot=new double[1000];
        double tempTotal;
        int i=0;
        int m=0;
        while (scan.hasNextLine()) {
            //
            String currentLine=scan.nextLine();
            // System.out.println("Current Line "+currentLine);
            String[] firstSplits = currentLine.split(" ", 0);
            for(;m<firstSplits.length;m++) {
                //     System.out.println("String is " + firstSplits[m]);
                try {
                    if(firstSplits[m]=="NaN"||firstSplits[m]=="Infinity");
                    else {
                        out[m] = Double.parseDouble(firstSplits[m]);
                        System.out.print(out[m] +"          ");
                    }
                }catch (Exception q)
                {
                    out[m]=0.000;
                }
                //     System.out.println("Value is " + out[m]);
            }
            // double d = Double.parseDouble();
        }
        Group root = new Group();
        //   stage.setScene(new Scene(root));

         Image background = new Image(visualoutputfb.class.getClassLoader().getResource("emotionSide.png").toString(), true);
        //   Image fusics = new Image("fusics.png");

     //   Image background=new Image("sample/spl1/oldPaper.jpg");
        Canvas canvas = new Canvas(1800,900);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(background,0,0);
        Scene scene = new Scene(root, 2000, 900);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
        CategoryAxis xAxis =new CategoryAxis();
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
               fourthPage fp=new fourthPage();
               fp.runs(stage);
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

        more.setOnAction(e->{
            try {

                double joyOut=0;

                for(int y=0;y<46;y+=8)joyOut+=out[y];
                joyOut=joyOut/6;




                double surpriseOut=0;
                for(int y=1;y<46;y+=8)surpriseOut+=out[y];
                surpriseOut=surpriseOut/6;

                double fearOut=0;
                for(int y=2;y<46;y+=8)fearOut+=out[y];
                fearOut=fearOut/6;

                double sadnessOut=0;
                for(int y=3;y<46;y+=8)sadnessOut+=out[y];
                sadnessOut=sadnessOut/6;

                double trustOut=0;
                for(int y=4;y<46;y+=8)trustOut+=out[y];
                trustOut=trustOut/6;

                double disgustOut=0;
                for(int y=5;y<48;y+=8)disgustOut+=out[y];
                disgustOut=disgustOut/6;

                double anticipationOut=0;
                for(int y=6;y<48;y+=8)anticipationOut+=out[y];
                anticipationOut=anticipationOut/6;

                double angerOut=0;
                for(int y=7;y<48;y+=8)angerOut+=out[y];
                angerOut=angerOut/6;

                //   Image backgrounds = new Image(getClass().getClassLoader().getResource("emotionSide.png").toString(), true);
                Image backgrounds=new Image("emotionSide.png");

                Pane roots = new Pane();

                CategoryAxis xAxises = new CategoryAxis();
                xAxises.setCategories(FXCollections.<String>
                        observableArrayList(Arrays.asList("Joy", "Surprise", "Fear", "Sadness","Trust","Disgust","anticipation","Anger")));
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
                double maxa=-1,maxb=-1;
                int temp=0,temp2=0;
                Double[] freq = new Double[]{ joyOut,surpriseOut,fearOut,sadnessOut,trustOut,disgustOut,anticipationOut,angerOut };
                for (int iteration = 0; iteration < 8; iteration++) {
//                    if (freq[iteration] > maxa) {
//                        maxa = freq[iteration];
//                        temp = iteration;

                    if (freq[iteration] > maxa)
                    {
                        maxb = maxa;
                        maxa = freq[iteration];
                        temp=iteration;
                    }

        /* If arr[i] is in between first and
           second then update second  */
                    else if (freq[iteration] > maxb && freq[iteration] != maxa) {
                        maxb = freq[iteration];
                        temp2=iteration;
                    }


                }
                System.out.println("Highest is "+temp+"Second is "+temp2);
                String text=null;


                if(temp==0)
                {
                    text="This person has a kind heart and jolly mind.Her post is full of serenity and ecstasy";
                }
                else if(temp==1)
                {
                    text="As a person,you are curious.Surprising things happening around you.Live with amazement";
                }
                else if(temp==2)
                {
                    text="Some terrible things is happening in your mind.Stay safe and sound";
                }
                else if(temp==3)
                {
                    text="Sadness grabs you severely.But remember, heavy hearts , like heavy clouds in the sky, are best relieved by the letting of a little water";
                }
                else if(temp==4 )
                {
                    text="You accept and adapt things happening around you wisely.Have enough courage to trust love one more time and always one more time";
                }
                else if(temp==5 )
                {
                    text="Disgust and boredom is the key of your status";
                }
                else if(temp==6 )
                {
                    text="Keep smiling that reflects on your posts.Spread positivity and be optimistic";
                }
                else if(temp==7 )
                {
                    text="Keep smiling that reflects on your posts.Spread positivity and be optimistic";

                }
                Text texts = new Text(text);

                texts.setFont(Font.font(Font.getFontNames().get(7), FontPosture.REGULAR, 12));
                texts.setScaleX(1);
                texts.setScaleY(1);
                texts.setTranslateX(50);
                texts.setTranslateY(40);
                texts.setFill(Color.DARKCYAN);


                roots.getChildren().addAll(barChart,texts,back);
                Scene scenea = new Scene(roots, 1400, 750);
                // Scene scene = new Scene(root, 1600, 800);
                stage.setScene(scenea);
                //primaryStage.setFullScreen(true);
                stage.show();

            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });

        String accessToken = "EAAJfo73qhKQBAPTqcW4SDLEAAMF6lCN2rABAK3H577LmAEvfLcJaQWkHnf8YUbZCeTPk5U44PiuC1cXO4vhJwqhKcIg7na6ddgocHZAhu43MVOgVwLZA1DA7nZBj937VOVANZA2ZAJZBLflCjp1ZBr76ZBNGUBJoQZCZBsi56ZBdAHS8XT0VFLn7XyQxEZBUNivOV8QcRx6IeGn2J1hoPeNoumOM8LcbNjP8livHdwZDZDfFVmOnd3WSpfsapFSaIXcq7tjaaGeLkPU3pykfu64thCEfPipr8jRJYZCdvPv5ZBJi2hsjpZBw0vvZCCKklcYZB6uZBJZBbf1Ri0eoZCJrjX65Q2Ew8lW7ZCZBfXiWkh9CXqZC2Im8ahp7JjoN4zu61ZAN17uQBs6t9gCgVlTmFTIDEUhMOZBq0ZCnBmtukdY77PEB3U ";

//        Button status2 = new Button("Status 2");
//        status2.setTranslateX(280);
//        status2.setTranslateY(620);
//        status2.setStyle("-fx-padding: 8 15 15 15;\n" +
//                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
//                "    -fx-background-radius: 8;\n" +
//                "    -fx-background-color: \n" +
//                "        linear-gradient(from 0% 93% to 0% 100%, #0B2058 0%, #030B21 100%),\n" +
//                "        #030B21,\n" +
//                "        #0B2058,\n" +
//                "        radial-gradient(center 50% 50%, radius 100%, #143389, #09236B);\n" +
//                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
//                "    -fx-font-weight: bold;\n" +
//                "    -fx-font-size: 1.5em;");
//        status2.setPrefSize(100, 30);
//        status2.setTextFill(Color.WHITE);
//
//
//
//
//
//
//        status2.setOnAction(e->
//                {
//                    Operations operations = new Operations();
//
//
//                    FacebookClient fbClient = new DefaultFacebookClient(accessToken);
//                    //   FacebookClient.AccessToken exAccessToken = fbClient.obtainExtendedAccessToken("668106823992484 ", "f63f747f31e390a44f93891920364794");
//
//                    Connection<Post> result;
//                    result = fbClient.fetchConnection("me/feed", Post.class);
//
//
//                    String userInput = null;
//                    EmotionCalculation emCal = new EmotionCalculation();
//                    emCal.fileOpen();
//                    int counter=0;
//                    for (List<Post> apost : result) {
//                        for (Post aPost : apost) {
//                            counter++;
//                            if (counter == 2) {
//
//                                try {
//                                    int number=aPost.getMessage().length();
//                                    s=aPost.getMessage().substring(0,number/16);
//                                    s+=aPost.getMessage().substring((number/16)+1,number/8);
//                                    s+="\n";
//                                    s+=aPost.getMessage().substring((number/8)+1,number/4);
//                                    s+="\n";
//                                    s+=aPost.getMessage().substring((number/4)+1,number/2);
//                                    s+="\n";
//                                    s+=aPost.getMessage().substring((number/2)+1,number);
//                                    s+="\n";
//                                    //     s+=aPost.getMessage().substring(2100,number);
//
//
//
//
//                                    char c;
//                                    c = aPost.getMessage().charAt(0);
//                                    char d;
//                                    d=aPost.getMessage().charAt(1);
//
//                                    if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') )&&((d >= 'a' && d <= 'z') || (d >= 'A' && d <= 'Z'))) {
//
//                                        operations.splitInput(aPost.getMessage());
//                                        aPost.getMessage().replaceAll(aPost.getMessage(), "");
//
//
//
//                                    } else {
//                                        System.out.println("The Post is Bangla");
//                                        OperationsBangla operationsBangla = new OperationsBangla();
//                                        operationsBangla.splitInputBangla();
//                                        String[] inArray = null;
//                                        String[] inArray2 = new String[10000];
//                                        Dictionary dictionary = new Dictionary("src/sample/spl1/Translation.txt");
//
//                                        inArray = aPost.getMessage().split("[ ,/;>.*'|\"(){+></@$%^&_=}]", 0);
//
//                                        aPost.getMessage().replaceAll(aPost.getMessage(), "");
//                                        for (int j = 0; j < inArray.length; j++) {
//                                            inArray2[j] = operationsBangla.searchBan(inArray[j]);
//                                        }
//                                        String inp = "";
//                                        for (int j = 0; j < inArray.length; j++) {
//                                            inArray2[j] = dictionary.search(inArray[j]);
//
//                                            inp = inp + inArray2[j] + " ";
//
//
//                                        }
//                                        operations.splitInput(inp);
//                                    }
//                                    operations.removeWord();
//                                    operations.search();
//                                    emCal.searchEmotion();
//                                    emCal.emotionCalc(stage);
//                                    emCal.DataOutputStream();
//
//                                } catch (Exception ea) {
//                                    System.out.println("");
//                                }
//                                emCal.VisualOutput(stage);
//
//                                break;
//                            }
//                        }
//
//                    }
//
//                }
//        );
//
//        Button status3 = new Button("Status 3");
//        status3.setTranslateX(460);
//        status3.setTranslateY(620);
//        status3.setStyle("-fx-padding: 8 15 15 15;\n" +
//                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
//                "    -fx-background-radius: 8;\n" +
//                "    -fx-background-color: \n" +
//                "        linear-gradient(from 0% 93% to 0% 100%, #0B2058 0%, #030B21 100%),\n" +
//                "        #030B21,\n" +
//                "        #0B2058,\n" +
//                "        radial-gradient(center 50% 50%, radius 100%, #143389, #09236B);\n" +
//                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
//                "    -fx-font-weight: bold;\n" +
//                "    -fx-font-size: 1.5em;");
//        status3.setPrefSize(100, 30);
//        status3.setTextFill(Color.WHITE);
//
//
//
//
//
//
//        status3.setOnAction(e->
//                {
//                    Operations operations = new Operations();
//
//
//                    FacebookClient fbClient = new DefaultFacebookClient(accessToken);
//                    //   FacebookClient.AccessToken exAccessToken = fbClient.obtainExtendedAccessToken("668106823992484 ", "f63f747f31e390a44f93891920364794");
//
//                    Connection<Post> result;
//                    result = fbClient.fetchConnection("me/feed", Post.class);
//
//
//                    String userInput = null;
//                    EmotionCalculation emCal = new EmotionCalculation();
//                    emCal.fileOpen();
//                    int counter=0;
//                    for (List<Post> apost : result) {
//                        for (Post aPost : apost) {
//                            counter++;
//                            if (counter == 3) {
//
//                                try {
//                                    int number=aPost.getMessage().length();
//                                    s=aPost.getMessage().substring(0,number/16);
//                                    s+=aPost.getMessage().substring((number/16)+1,number/8);
//                                    s+="\n";
//                                    s+=aPost.getMessage().substring((number/8)+1,number/4);
//                                    s+="\n";
//                                    s+=aPost.getMessage().substring((number/4)+1,number/2);
//                                    s+="\n";
//                                    s+=aPost.getMessage().substring((number/2)+1,number);
//                                    s+="\n";
//                                    //     s+=aPost.getMessage().substring(2100,number);
//
//
//
//
//                                    char c;
//                                    c = aPost.getMessage().charAt(0);
//                                    char d;
//                                    d=aPost.getMessage().charAt(1);
//
//                                    if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') )&&((d >= 'a' && d <= 'z') || (d >= 'A' && d <= 'Z'))) {
//
//                                        operations.splitInput(aPost.getMessage());
//                                        aPost.getMessage().replaceAll(aPost.getMessage(), "");
//
//
//
//                                    } else {
//                                        System.out.println("The Post is Bangla");
//                                        OperationsBangla operationsBangla = new OperationsBangla();
//                                        operationsBangla.splitInputBangla();
//                                        String[] inArray = null;
//                                        String[] inArray2 = new String[10000];
//                                        Dictionary dictionary = new Dictionary("src/sample/spl1/Translation.txt");
//
//                                        inArray = aPost.getMessage().split("[ ,/;>.*'|\"(){+></@$%^&_=}]", 0);
//
//                                        aPost.getMessage().replaceAll(aPost.getMessage(), "");
//                                        for (int j = 0; j < inArray.length; j++) {
//                                            inArray2[j] = operationsBangla.searchBan(inArray[j]);
//                                        }
//                                        String inp = "";
//                                        for (int j = 0; j < inArray.length; j++) {
//                                            inArray2[j] = dictionary.search(inArray[j]);
//
//                                            inp = inp + inArray2[j] + " ";
//
//
//                                        }
//                                        operations.splitInput(inp);
//                                    }
//                                    operations.removeWord();
//                                    operations.search();
//                                    emCal.searchEmotion();
//                                    emCal.emotionCalc(stage);
//                                    emCal.DataOutputStream();
//
//                                } catch (Exception ea) {
//                                    System.out.println("");
//                                }
//                                emCal.VisualOutput(stage);
//
//                                break;
//                            }
//                        }
//
//                    }
//
//                }
//        );
//
//        Button status4 = new Button("Status 4");
//        status4.setTranslateX(640);
//        status4.setTranslateY(620);
//        status4.setStyle("-fx-padding: 8 15 15 15;\n" +
//                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
//                "    -fx-background-radius: 8;\n" +
//                "    -fx-background-color: \n" +
//                "        linear-gradient(from 0% 93% to 0% 100%, #0B2058 0%, #030B21 100%),\n" +
//                "        #030B21,\n" +
//                "        #0B2058,\n" +
//                "        radial-gradient(center 50% 50%, radius 100%, #143389, #09236B);\n" +
//                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
//                "    -fx-font-weight: bold;\n" +
//                "    -fx-font-size: 1.5em;");
//        status4.setPrefSize(100, 30);
//        status4.setTextFill(Color.WHITE);
//
//
//
//
//
//
//        status4.setOnAction(e->
//                {
//                    Operations operations = new Operations();
//
//
//                    FacebookClient fbClient = new DefaultFacebookClient(accessToken);
//                    //   FacebookClient.AccessToken exAccessToken = fbClient.obtainExtendedAccessToken("668106823992484 ", "f63f747f31e390a44f93891920364794");
//
//                    Connection<Post> result;
//                    result = fbClient.fetchConnection("me/feed", Post.class);
//
//
//                    String userInput = null;
//                    EmotionCalculation emCal = new EmotionCalculation();
//                    emCal.fileOpen();
//                    int counter=0;
//                    for (List<Post> apost : result) {
//                        for (Post aPost : apost) {
//                            counter++;
//                            if (counter == 4) {
//
//                                try {
//                                    int number=aPost.getMessage().length();
//                                    s=aPost.getMessage().substring(0,number/16);
//                                    s+=aPost.getMessage().substring((number/16)+1,number/8);
//                                    s+="\n";
//                                    s+=aPost.getMessage().substring((number/8)+1,number/4);
//                                    s+="\n";
//                                    s+=aPost.getMessage().substring((number/4)+1,number/2);
//                                    s+="\n";
//                                    s+=aPost.getMessage().substring((number/2)+1,number);
//                                    s+="\n";
//                                    //     s+=aPost.getMessage().substring(2100,number);
//
//
//
//
//                                    char c;
//                                    c = aPost.getMessage().charAt(0);
//                                    char d;
//                                    d=aPost.getMessage().charAt(1);
//
//                                    if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') )&&((d >= 'a' && d <= 'z') || (d >= 'A' && d <= 'Z'))) {
//
//                                        operations.splitInput(aPost.getMessage());
//                                        aPost.getMessage().replaceAll(aPost.getMessage(), "");
//
//
//
//                                    } else {
//                                        System.out.println("The Post is Bangla");
//                                        OperationsBangla operationsBangla = new OperationsBangla();
//                                        operationsBangla.splitInputBangla();
//                                        String[] inArray = null;
//                                        String[] inArray2 = new String[10000];
//                                        Dictionary dictionary = new Dictionary("src/sample/spl1/Translation.txt");
//
//                                        inArray = aPost.getMessage().split("[ ,/;>.*'|\"(){+></@$%^&_=}]", 0);
//
//                                        aPost.getMessage().replaceAll(aPost.getMessage(), "");
//                                        for (int j = 0; j < inArray.length; j++) {
//                                            inArray2[j] = operationsBangla.searchBan(inArray[j]);
//                                        }
//                                        String inp = "";
//                                        for (int j = 0; j < inArray.length; j++) {
//                                            inArray2[j] = dictionary.search(inArray[j]);
//
//                                            inp = inp + inArray2[j] + " ";
//
//
//                                        }
//                                        operations.splitInput(inp);
//                                    }
//                                    operations.removeWord();
//                                    operations.search();
//                                    emCal.searchEmotion();
//                                    emCal.emotionCalc(stage);
//                                    emCal.DataOutputStream();
//
//                                } catch (Exception ea) {
//                                    System.out.println("");
//                                }
//                                emCal.VisualOutput(stage);
//
//                                break;
//                            }
//                        }
//
//                    }
//
//                }
//        );
//
//        Button status5 = new Button("Status 5");
//        status5.setTranslateX(820);
//        status5.setTranslateY(620);
//        status5.setStyle("-fx-padding: 8 15 15 15;\n" +
//                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
//                "    -fx-background-radius: 8;\n" +
//                "    -fx-background-color: \n" +
//                "        linear-gradient(from 0% 93% to 0% 100%, #0B2058 0%, #030B21 100%),\n" +
//                "        #030B21,\n" +
//                "        #0B2058,\n" +
//                "        radial-gradient(center 50% 50%, radius 100%, #143389, #09236B);\n" +
//                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
//                "    -fx-font-weight: bold;\n" +
//                "    -fx-font-size: 1.5em;");
//        status5.setPrefSize(100, 30);
//        status5.setTextFill(Color.WHITE);
//
//
//
//
//
//
//        status5.setOnAction(e->
//                {
//                    Operations operations = new Operations();
//
//
//                    FacebookClient fbClient = new DefaultFacebookClient(accessToken);
//                    //   FacebookClient.AccessToken exAccessToken = fbClient.obtainExtendedAccessToken("668106823992484 ", "f63f747f31e390a44f93891920364794");
//
//                    Connection<Post> result;
//                    result = fbClient.fetchConnection("me/feed", Post.class);
//
//
//                    String userInput = null;
//                    EmotionCalculation emCal = new EmotionCalculation();
//                    emCal.fileOpen();
//                    int counter=0;
//                    for (List<Post> apost : result) {
//                        for (Post aPost : apost) {
//                            counter++;
//                            if (counter == 5) {
//
//                                try {
//                                    int number=aPost.getMessage().length();
//                                    s=aPost.getMessage().substring(0,number/16);
//                                    s+=aPost.getMessage().substring((number/16)+1,number/8);
//                                    s+="\n";
//                                    s+=aPost.getMessage().substring((number/8)+1,number/4);
//                                    s+="\n";
//                                    s+=aPost.getMessage().substring((number/4)+1,number/2);
//                                    s+="\n";
//                                    s+=aPost.getMessage().substring((number/2)+1,number);
//                                    s+="\n";
//                                    //     s+=aPost.getMessage().substring(2100,number);
//
//
//
//
//                                    char c;
//                                    c = aPost.getMessage().charAt(0);
//                                    char d;
//                                    d=aPost.getMessage().charAt(1);
//
//                                    if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') )&&((d >= 'a' && d <= 'z') || (d >= 'A' && d <= 'Z'))) {
//
//                                        operations.splitInput(aPost.getMessage());
//                                        aPost.getMessage().replaceAll(aPost.getMessage(), "");
//
//
//
//                                    } else {
//                                        System.out.println("The Post is Bangla");
//                                        OperationsBangla operationsBangla = new OperationsBangla();
//                                        operationsBangla.splitInputBangla();
//                                        String[] inArray = null;
//                                        String[] inArray2 = new String[10000];
//                                        Dictionary dictionary = new Dictionary("src/sample/spl1/Translation.txt");
//
//                                        inArray = aPost.getMessage().split("[ ,/;>.*'|\"(){+></@$%^&_=}]", 0);
//
//                                        aPost.getMessage().replaceAll(aPost.getMessage(), "");
//                                        for (int j = 0; j < inArray.length; j++) {
//                                            inArray2[j] = operationsBangla.searchBan(inArray[j]);
//                                        }
//                                        String inp = "";
//                                        for (int j = 0; j < inArray.length; j++) {
//                                            inArray2[j] = dictionary.search(inArray[j]);
//
//                                            inp = inp + inArray2[j] + " ";
//
//
//                                        }
//                                        operations.splitInput(inp);
//                                    }
//                                    operations.removeWord();
//                                    operations.search();
//                                    emCal.searchEmotion();
//                                    emCal.emotionCalc(stage);
//                                    emCal.DataOutputStream();
//
//                                } catch (Exception ea) {
//                                    System.out.println("");
//                                }
//                                emCal.VisualOutput(stage);
//
//                                break;
//                            }
//                        }
//
//                    }
//
//                }
//        );
//
//        Button status6 = new Button("Status 6");
//        status6.setTranslateX(1070);
//        status6.setTranslateY(620);
//        status6.setStyle("-fx-padding: 8 15 15 15;\n" +
//                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
//                "    -fx-background-radius: 8;\n" +
//                "    -fx-background-color: \n" +
//                "        linear-gradient(from 0% 93% to 0% 100%, #0B2058 0%, #030B21 100%),\n" +
//                "        #030B21,\n" +
//                "        #0B2058,\n" +
//                "        radial-gradient(center 50% 50%, radius 100%, #143389, #09236B);\n" +
//                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
//                "    -fx-font-weight: bold;\n" +
//                "    -fx-font-size: 1.5em;");
//        status6.setPrefSize(100, 30);
//        status6.setTextFill(Color.WHITE);
//
//
//
//
//
//
//        status6.setOnAction(e->
//                {
//                    Operations operations = new Operations();
//
//
//                    FacebookClient fbClient = new DefaultFacebookClient(accessToken);
//                    //   FacebookClient.AccessToken exAccessToken = fbClient.obtainExtendedAccessToken("668106823992484 ", "f63f747f31e390a44f93891920364794");
//
//                    Connection<Post> result;
//                    result = fbClient.fetchConnection("me/feed", Post.class);
//
//
//                    String userInput = null;
//                    EmotionCalculation emCal = new EmotionCalculation();
//                    emCal.fileOpen();
//                    int counter=0;
//                    for (List<Post> apost : result) {
//                        for (Post aPost : apost) {
//                            counter++;
//                            if (counter == 6) {
//
//                                try {
//                                    int number=aPost.getMessage().length();
//                                    s=aPost.getMessage().substring(0,number/16);
//                                    s+=aPost.getMessage().substring((number/16)+1,number/8);
//                                    s+="\n";
//                                    s+=aPost.getMessage().substring((number/8)+1,number/4);
//                                    s+="\n";
//                                    s+=aPost.getMessage().substring((number/4)+1,number/2);
//                                    s+="\n";
//                                    s+=aPost.getMessage().substring((number/2)+1,number);
//                                    s+="\n";
//                                    //     s+=aPost.getMessage().substring(2100,number);
//
//
//
//
//                                    char c;
//                                    c = aPost.getMessage().charAt(0);
//                                    char d;
//                                    d=aPost.getMessage().charAt(1);
//
//                                    if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') )&&((d >= 'a' && d <= 'z') || (d >= 'A' && d <= 'Z'))) {
//
//                                        operations.splitInput(aPost.getMessage());
//                                        aPost.getMessage().replaceAll(aPost.getMessage(), "");
//
//
//
//                                    } else {
//                                        System.out.println("The Post is Bangla");
//                                        OperationsBangla operationsBangla = new OperationsBangla();
//                                        operationsBangla.splitInputBangla();
//                                        String[] inArray = null;
//                                        String[] inArray2 = new String[10000];
//                                        Dictionary dictionary = new Dictionary("src/sample/spl1/Translation.txt");
//
//                                        inArray = aPost.getMessage().split("[ ,/;>.*'|\"(){+></@$%^&_=}]", 0);
//
//                                        aPost.getMessage().replaceAll(aPost.getMessage(), "");
//                                        for (int j = 0; j < inArray.length; j++) {
//                                            inArray2[j] = operationsBangla.searchBan(inArray[j]);
//                                        }
//                                        String inp = "";
//                                        for (int j = 0; j < inArray.length; j++) {
//                                            inArray2[j] = dictionary.search(inArray[j]);
//
//                                            inp = inp + inArray2[j] + " ";
//
//
//                                        }
//                                        operations.splitInput(inp);
//                                    }
//                                    operations.removeWord();
//                                    operations.search();
//                                    emCal.searchEmotion();
//                                    emCal.emotionCalc(stage);
//                                    emCal.DataOutputStream();
//
//                                } catch (Exception ea) {
//                                    System.out.println("");
//                                }
//                                emCal.VisualOutput(stage);
//
//                                break;
//                            }
//                        }
//
//                    }
//
//                }
//        );

        CategoryAxis xAxisq = new CategoryAxis();
        xAxisq.setLabel("Status");

        NumberAxis yAxisq = new NumberAxis();
        yAxisq.setLabel("Emotion Intensity");


        LineChart lineChart = new LineChart(xAxisq, yAxisq);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("JOY");

        for(int w=0,e=1;w<50 &&e<10;w+=8,e++)
            dataSeries1.getData().add(new XYChart.Data("Post "+ e, out[w]));



        XYChart.Series dataSeries2 = new XYChart.Series();
        dataSeries2.setName("SURPRISE");

        for(int w=1,e=1;w<50 &&e<10;w+=8,e++)
            dataSeries2.getData().add(new XYChart.Data( "Post "+ e, out[w]));

        XYChart.Series dataSeries3 = new XYChart.Series();
        dataSeries3.setName("FEAR");
        for(int w=2,e=1;w<50 &&e<10;w+=8,e++)
            dataSeries3.getData().add(new XYChart.Data( "Post "+ e, out[w]));



        XYChart.Series dataSeries4 = new XYChart.Series();
        dataSeries4.setName("SADNESS");

        for(int w=3,e=1;w<50 &&e<10;w+=8,e++)
            dataSeries4.getData().add(new XYChart.Data( "Post "+ e, out[w]));


        XYChart.Series dataSeries5 = new XYChart.Series();
        dataSeries5.setName("TRUST");

        for(int w=0,e=1;w<50 &&e<10;w+=8,e++)
            dataSeries5.getData().add(new XYChart.Data( "Post "+ e, out[w]));

        XYChart.Series dataSeries6 = new XYChart.Series();
        dataSeries6.setName("DISGUST");

        for(int w=5,e=1;w<50 &&e<10;w+=8,e++)
            dataSeries6.getData().add(new XYChart.Data( "Post "+ e, out[w]));

        XYChart.Series dataSeries7 = new XYChart.Series();
        dataSeries7.setName("ANTICIPATION");

        for(int w=6,e=1;w<50 &&e<10;w+=8,e++)
            dataSeries7.getData().add(new XYChart.Data( "Post "+ e, out[w]));



        XYChart.Series dataSeries8 = new XYChart.Series();
        dataSeries8.setName("ANGER");

        for(int w=7,e=1;w<50 &&e<10;w+=8,e++)
            dataSeries8.getData().add(new XYChart.Data( "Post "+ e, out[w]));


        lineChart.getData().addAll(dataSeries1,dataSeries2,dataSeries3,dataSeries4,dataSeries5,dataSeries6,dataSeries7,dataSeries8);




//
//
//        ObservableList<XYChart.Series<String,Double>> lineChartData = FXCollections.observableArrayList(
//                new LineChart.Series<String,Double>("Joy", FXCollections.observableArrayList(
//                        new XYChart.Data<String,Double>("Status 1",out[0]),
//                        new XYChart.Data<String,Double>("Status 2", out[8]),
//                        new XYChart.Data<String,Double>("Status 3", out[16]),
//                        new XYChart.Data<String,Double>("Status 4", out[24]),
//                        new XYChart.Data<String,Double>("Status 5", out[32]),
//                        new XYChart.Data<String,Double>("Status 6", out[40])
//
//                )),
//                new LineChart.Series<String,Double>("Surprise", FXCollections.observableArrayList(
//                        new XYChart.Data<String,Double>("Status 1", out[1]),
//                        new XYChart.Data<String,Double>("Status 2", out[9]),
//                        new XYChart.Data<String,Double>("Status 3", out[17]),
//                        new XYChart.Data<String,Double>("Status 4", out[25]),
//                        new XYChart.Data<String,Double>("Status 5", out[33]),
//                        new XYChart.Data<String,Double>("Status 6", out[41])
//
//                )),
//                new LineChart.Series<String,Double>("Fear", FXCollections.observableArrayList(
//                        new XYChart.Data<String,Double>("Status 1",out[2]),
//                        new XYChart.Data<String,Double>("Status 2", out[10]),
//                        new XYChart.Data<String,Double>("Status 3", out[18]),
//                        new XYChart.Data<String,Double>("Status 4", out[26]),
//                        new XYChart.Data<String,Double>("Status 5", out[34]),
//                        new XYChart.Data<String,Double>("Status 6", out[42])
//
//                )),
//                new LineChart.Series<String,Double>("Sadness", FXCollections.observableArrayList(
//                        new XYChart.Data<String,Double>("Status 1",out[3]),
//                        new XYChart.Data<String,Double>("Status 2", out[11]),
//                        new XYChart.Data<String,Double>("Status 3", out[19]),
//                        new XYChart.Data<String,Double>("Status 4", out[27]),
//                        new XYChart.Data<String,Double>("Status 5", out[35]),
//                        new XYChart.Data<String,Double>("Status 6", out[43])
//
//                )),
//                new LineChart.Series<String,Double>("Trust", FXCollections.observableArrayList(
//                        new XYChart.Data<String,Double>("Status 1",out[4]),
//                        new XYChart.Data<String,Double>("Status 2", out[12]),
//                        new XYChart.Data<String,Double>("Status 3", out[20]),
//                        new XYChart.Data<String,Double>("Status 4", out[28]),
//                        new XYChart.Data<String,Double>("Status 5", out[36]),
//                        new XYChart.Data<String,Double>("Status 6", out[44])
//                )),
//
//                new LineChart.Series<String,Double>("Disgust", FXCollections.observableArrayList(
//                        new XYChart.Data<String,Double>("Status 1",out[5]),
//                        new XYChart.Data<String,Double>("Status 2", out[13]),
//                        new XYChart.Data<String,Double>("Status 3", out[21]),
//                        new XYChart.Data<String,Double>("Status 4", out[29]),
//                        new XYChart.Data<String,Double>("Status 5", out[37]),
//                        new XYChart.Data<String,Double>("Status 6", out[45])
//
//                )),
//
//                new LineChart.Series<String,Double>("anticipation", FXCollections.observableArrayList(
//                        new XYChart.Data<String,Double>("Status 1",out[6]),
//                        new XYChart.Data<String,Double>("Status 2", out[14]),
//                        new XYChart.Data<String,Double>("Status 3", out[22]),
//                        new XYChart.Data<String,Double>("Status 4", out[30]),
//                        new XYChart.Data<String,Double>("Status 5", out[38]),
//                        new XYChart.Data<String,Double>("Status 6", out[46])
//
//                )),
//
//                new LineChart.Series<String,Double>("Anger", FXCollections.observableArrayList(
//                        new XYChart.Data<String,Double>("Status 1",out[7]),
//                        new XYChart.Data<String,Double>("Status 2", out[15]),
//                        new XYChart.Data<String,Double>("Status 3", out[23]),
//                        new XYChart.Data<String,Double>("Status 4", out[31]),
//                        new XYChart.Data<String,Double>("Status 5", out[39]),
//                        new XYChart.Data<String,Double>("Status 6", out[47])
//
//                ))
//        );


        for(int s=0,t=1;t<=15;s+=8,t++)
        {
           sentimentPos[t]=out[s]+out[s+1]+out[s+4]+out[s+6];
        }

        for(int s=0,t=1;t<=15;s+=8,t++)
        {
           sentimentNeg[t]=out[s+2]+out[s+3]+out[s+5]+out[s+7];
        }
        for(int t=1;t<=15;t++)sentimentTot[t]=sentimentPos[t]-sentimentNeg[t];

        tempTotal=0;
        for(int t=1;t<=15;t++)tempTotal+=sentimentTot[t];
        tempTotal/=7;

        //LineChart chart = new LineChart(xAxis, yAxis, lineChart);
        lineChart.setPrefSize(1200,700);




        Button moreSenti = new Button("Sentiment Analysis");
        moreSenti.setTranslateX(450);
        moreSenti.setTranslateY(700);
        moreSenti.setStyle("-fx-padding: 8 15 15 15;\n" +
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
        moreSenti.setPrefSize(360, 40);
        moreSenti.setTextFill(Color.WHITE);
        double finalTempTotal = tempTotal;
        moreSenti.setOnAction(e->
        {

            Group roota = new Group();
            //   stage.setScene(new Scene(root));
            //   Image backgrounda = new Image(getClass().getClassLoader().getResource("emotionSide.png").toString(), true);

        //    Image backgrounda=new Image("emotion(16-8).png");
            //   Image fusics = new Image("fusics.png");
            Canvas canvasa = new Canvas(1800,900);


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
            backa.setOnAction(ed -> {
                try {
                    fourthPage fp=new fourthPage();
                    fp.runs(stage);
                } catch (Exception ex) {
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

            NumberAxis xAxisa = new NumberAxis();
            xAxisa.setLabel("No of status");

            NumberAxis yAxisa = new NumberAxis();
            yAxisa.setLabel("Overall Sentiment Score");

            ScatterChart scatterChart = new ScatterChart(xAxisa, yAxisa);

            LineChart linechart =new LineChart(xAxisa, yAxisa);
            XYChart.Series dataSeriessenti = new XYChart.Series();
            dataSeriessenti.setName("SENTIMENT SCORE");

            XYChart.Series dataSeriessenti2 = new XYChart.Series();

            dataSeriessenti2.setName("MEAN VALUE");

            for(int t=1;t<=7;t++)
                dataSeriessenti2.getData().add(new XYChart.Data( t, finalTempTotal));
            for(int t=1;t<=7;t++)
            dataSeriessenti.getData().add(new XYChart.Data( t, sentimentTot[t]));


            linechart.getData().addAll(dataSeriessenti2);
            scatterChart.getData().addAll(dataSeriessenti);

            linechart.setPrefSize(1000,700);
            scatterChart.setPrefSize(1000,700);
//            linechart.setOpacity(0.7);
//            scatterChart.setOpacity(6.2);
          //  linechart.lookup(".default-color0.chart-series-line").setStyle("-fxstroke: transparent");
            scatterChart.lookup(".default-color0.chart-series-line").setStyle("-fxstroke: transparent");


            roota.getChildren().addAll(canvasa,backa,scatterChart,linechart);

        });




     //   root.getChildren().addAll(canvas,lineChart,more,status2,status3,status4,status5,status6,moreSenti);
        root.getChildren().addAll(canvas,lineChart,more,moreSenti);




    }



}
