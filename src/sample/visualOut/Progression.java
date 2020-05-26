package sample.visualOut;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Post;
import javafx.stage.Stage;
import sample.spl1.Dictionary;
import sample.spl1.Operations;
import sample.spl1.OperationsBangla;
import sample.spl1.emotioncal.EmotionCalculation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class Progression {
    public static int counter = 0;
    public void statusProgress(Stage stages,String accessToken)
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
                    }


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
       // file.delete();

    }


}
