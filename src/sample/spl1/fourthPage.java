package sample.spl1;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Post;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.visualOut.visualoutputfb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import sample.spl1.emotioncal.EmotionCalculation;
public class fourthPage {
    public static int counter = 0;
public void runs(Stage stages)
{

    String accessToken = "EAAJfo73qhKQBACxb48sDXbDEzZCW8ZC1gDMzBIVmxcaD8dbnQYziIA5ZCgJErWMWNZCTM5OJyM2c06GfZArQZAJmzuqTfdhz0gqeCKuJSm4tQgPeYAwYCDwuAI0dQF8jMr9OB1gXqY2jTjY1kGK9mSnlU4jII1ZBjKxnUSiVKMGWAZDZD ";
        Button proa = new Button("Overall Emotion");
        proa.setTranslateX(550);
        proa.setTranslateY(70);
        setStyle(proa);
        proa.setPrefSize(300, 80);
        proa.setTextFill(Color.WHITE);

        Button progression = new Button("Progress");
        progression.setTranslateX(550);
        progression.setTranslateY(220);
        setStyle(progression);
        progression.setPrefSize(300, 80);
        progression.setTextFill(Color.WHITE);



        Button backs = new Button("Back");
        backs.setTranslateX(550);
        backs.setTranslateY(370);
        setStyle(backs);
        backs.setPrefSize(300, 80);
        backs.setTextFill(Color.WHITE);

        Pane roots = new Pane();



        backs.setOnAction(esb->{
            try {
                secondPage goBack = new secondPage();
                goBack.TheSecond(stages);
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });

    progression.setOnAction(e->
            {

                System.out.println("Progression is :");
                Operations operations = new Operations();


                FacebookClient fbClient = new DefaultFacebookClient(accessToken);
                //   FacebookClient.AccessToken exAccessToken = fbClient.obtainExtendedAccessToken("668106823992484 ", "f63f747f31e390a44f93891920364794");


                //Fetch Data from pages It's ok


                Connection<Post> result;
                result = fbClient.fetchConnection("me/feed", Post.class);


                String userInput = null;
                EmotionCalculation emCal = new EmotionCalculation();
                emCal.fileOpen();
                //     emCal.DataOutputStream();


                for (List<Post> apost : result) {
                    for (Post aPost : apost) {
                        counter++;
                        if (counter >= 10) {
                            break;
                        }



                        System.out.println(aPost.getMessage());
                        System.out.println("Post like is :"+aPost.getLikesCount());
                        System.out.println("Post Source is :"+aPost.getSource());
                        System.out.println("Post link is :"+aPost.getLink());
                        try {


                            char c;
                            c = aPost.getMessage().charAt(0);

                            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {


                                System.out.println("The post is English\n");

                                operations.splitInput(aPost.getMessage());


                                aPost.getMessage().replaceAll(aPost.getMessage(), "");
                                operations.removeWord();
                                operations.search();


                                //   EmotionCalculation emCal = new EmotionCalculation();
                                emCal.searchEmotion();
                                emCal.emotionCalc(stages);
                                emCal.DataOutputStream();


                            } else {
                                System.out.println("The Post is Bangla");
                                OperationsBangla operationsBangla = new OperationsBangla();

                                operationsBangla.splitInputBangla();
                                String[] inArray = null;
                                String[] inArray2 = new String[10000];
                                Dictionary dictionary = new Dictionary("src/sample/spl1/Translation.txt");

                                inArray = aPost.getMessage().split("[ ,/;>.*'|\"(){+></@$%^&_=}]", 0);

                                aPost.getMessage().replaceAll(aPost.getMessage(), "");
                                for (int j = 0; j < inArray.length; j++) {
                                    //    System.out.println("askdas");
                                    inArray2[j] = operationsBangla.searchBan(inArray[j]);
                                }
                                String inp = "";
                                for (int j = 0; j < inArray.length; j++) {

                                    //  System.out.print(inArray[j]+" ");
                                    //  System.out.print(dictionary.search(inArray[j])+" ");
                                    inArray2[j] = dictionary.search(inArray[j]);
                                    //  inListBan.add(inArray2[j]);

                                    //   System.out.print(inArray2[4]+" ");

                                    inp = inp + inArray2[j] + " ";


                                }
                                operations.splitInput(inp);


                                // inputStringBan = in.readLine();
                                //  inputStringBan= "আমি সত্য ভালবাসা";


                                operations.removeWord();
                                operations.search();


                                //launch(args);
                                emCal.searchEmotion();
                                emCal.emotionCalc(stages);
                                emCal.DataOutputStream();

                                //  System.out.println(dictionary.search("সত্য"));


                            }


                            //    System.out.println("fb.com/"+aPost.getId());

                        } catch (Exception ea) {
                            System.out.println("");
                        }
                    }
//                    if(counter>=7)
//                   break;


                    //  emCal.VisualOutput(stage);
                    try {
                        visualoutputfb.VisualOutputFacebook(stages);
                    } catch (FileNotFoundException aex) {
                        aex.printStackTrace();
                    }
                    break;
                }



                //    root.getChildren().addAll(,back,pro,headning,liveUrl);

                File file = new File("sample/spl1/out.txt");
                file.delete();

            }
    );




    proa.setOnAction(e->
            {

                Operations operations = new Operations();
             //   String accessToken = "EAAMF6lCN2rABAOZAGaFrHah3HNe51Hy6nxOli799Keg9NdZBsneHR1Ed8OOHNJdjUmJxWEJtUoJ88Ymz8nfYpSNdbThXRjbC4RJkyDn1nwCNfzpmrZA7vqCki1AYryTeRACVEZB7bZAnjvUcaw8sR8BnWizYa5ZAC0IUyxn8uGtwXZCij6xO3Lbeg3W8MWrnkEwrOYmzxDyqCLXTnCuKL0j ";

                FacebookClient fbClient = new DefaultFacebookClient(accessToken);
                //   FacebookClient.AccessToken exAccessToken = fbClient.obtainExtendedAccessToken("668106823992484 ", "f63f747f31e390a44f93891920364794");


                //Fetch Data from pages It's ok


                Connection<Post> result;
                result = fbClient.fetchConnection("me/feed", Post.class);


                String userInput = null;
                EmotionCalculation emCal = new EmotionCalculation();
                emCal.fileOpen();
                //     emCal.DataOutputStream();


                for (List<Post> apost : result) {
                    for (Post aPost : apost) {
                        counter++;
                        if (counter >= 5) {
                            break;
                        }


                        System.out.println(aPost.getMessage());
                        try {


                            char c;
                            c = aPost.getMessage().charAt(0);

                            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {


                                System.out.println("The post is English\n");

                                operations.splitInput(aPost.getMessage());


                                aPost.getMessage().replaceAll(aPost.getMessage(), "");
                                operations.removeWord();
                                operations.search();



                                emCal.searchEmotion();
                                emCal.emotionCalc(stages);



                            } else {
                                System.out.println("The Post is Bangla");
                                OperationsBangla operationsBangla = new OperationsBangla();

                                operationsBangla.splitInputBangla();
                                String[] inArray = null;
                                String[] inArray2 = new String[10000];
                                Dictionary dictionary = new Dictionary("src/sample/spl1/Translation.txt");

                                inArray = aPost.getMessage().split("[ ,/;>.*'|\"(){+></@$%^&_=}]", 0);

                                aPost.getMessage().replaceAll(aPost.getMessage(), "");
                                for (int j = 0; j < inArray.length; j++) {
                                    //    System.out.println("askdas");
                                    inArray2[j] = operationsBangla.searchBan(inArray[j]);
                                }
                                String inp = "";
                                for (int j = 0; j < inArray.length; j++) {

                                    //  System.out.print(inArray[j]+" ");
                                    //  System.out.print(dictionary.search(inArray[j])+" ");
                                    inArray2[j] = dictionary.search(inArray[j]);
                                    //  inListBan.add(inArray2[j]);

                                    //   System.out.print(inArray2[4]+" ");

                                    inp = inp + inArray2[j] + " ";


                                }
                                operations.splitInput(inp);


                                // inputStringBan = in.readLine();
                                //  inputStringBan= "আমি সত্য ভালবাসা";


                                operations.removeWord();
                                operations.search();


                                //launch(args);
                                emCal.searchEmotion();
                                emCal.emotionCalc(stages);

                                //  System.out.println(dictionary.search("সত্য"));


                            }


                            //    System.out.println("fb.com/"+aPost.getId());

                        } catch (Exception ea) {
                            System.out.println("");
                        }
                    }
//                    if(counter>=7)
//                   break;



                }

                emCal.VisualOutput(stages);

                //    root.getChildren().addAll(,back,pro,headning,liveUrl);


            }
    );



    Scene scene = new Scene(roots, 1400, 700);
    stages.setScene(scene);
    //stages.setFullScreen(true);
    stages.show();
    Image background = new Image(getClass().getClassLoader().getResource("emotion(16-9).png").toString(), true);



    Canvas canvas = new Canvas(1600,900);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    //gc.drawImage(background,0,0);
    BackgroundImage bi = new BackgroundImage(background,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
    Background bg = new Background(bi);
    roots.setBackground(bg);

    roots.getChildren().addAll(backs,proa,progression);

}


    public Button setStyle ( Button b)
    {
        b.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #0B2058 0%, #030B21 100%),\n" +
                "        #030B21,\n" +
                "        #0B2058,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #143389, #09236B);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 2.1em;");
        return b;
    }




}
