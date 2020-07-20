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

import java.io.IOException;
import java.util.List;

public class overAll {
    public static int counter = 0;
    String OverAllText=null;
    public void overall(Stage stages,String accessToken,String Name) throws IOException {
        Operations operations = new Operations();

        FacebookClient fbClient = new DefaultFacebookClient(accessToken);

        Connection<Post> result;
        result = fbClient.fetchConnection("me/feed", Post.class);




        for (List<Post> apost : result) {
            for (Post aPost : apost) {
                counter++;
                if (counter >= 10) {
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



//                        emCal.searchEmotion();
//                        emCal.emotionCalc(stages);



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

                        OverAllText+=inp;


                        // inputStringBan = in.readLine();
                        //  inputStringBan= "আমি সত্য ভালবাসা";


                        operations.removeWord();
                        operations.search();


                        //launch(args);

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

        EmotionCalculation emCal = new EmotionCalculation(Name,3);
        try {
            emCal.searchEmotion();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            emCal.emotionCalc(stages);
            if(Name!=null)
                emCal.DataOutputStreamProf();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            emCal.VisualOutputProf(stages,OverAllText);
        } catch (Exception ex) {
            ex.printStackTrace();
        }






//
//        EmotionCalculation emCal = new EmotionCalculation();
//        emCal.fileOpen();
//
//
//        emCal.searchEmotion();
//        try {
//            emCal.emotionCalc(stages);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        emCal.VisualOutputProf(stages,OverAllText);



        //    root.getChildren().addAll(,back,pro,headning,liveUrl);




    }
}
