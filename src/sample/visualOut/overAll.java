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

import java.util.List;

public class overAll {
    public static int counter = 0;
    public void overall(Stage stages,String accessToken)
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
                if (counter >= 8) {
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
                        emCal.VisualOutput(stages,inp);
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



        //    root.getChildren().addAll(,back,pro,headning,liveUrl);




    }
}
