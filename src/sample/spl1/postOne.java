package sample.spl1;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.json.JsonObject;
import com.restfb.types.Post;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.spl1.emotioncal.EmotionCalculation;

import java.util.Arrays;
import java.util.List;

public class postOne {
    int[] integers = new int[100];

    public void postone(Stage stages,String accessToken,int numbers)
    {

        Operations operations = new Operations();


        FacebookClient fbClient = new DefaultFacebookClient(accessToken);
        //   FacebookClient.AccessToken exAccessToken = fbClient.obtainExtendedAccessToken("668106823992484 ", "f63f747f31e390a44f93891920364794");

        Connection<Post> result;
        result = fbClient.fetchConnection("me/feed", Post.class);


        String userInput = null;
        EmotionCalculation emCal = new EmotionCalculation();
        emCal.fileOpen();
        int counter=0;
//        for (List<Post> apost : result) {
//            for (Post aPost : apost) {
//                counter++;


        List<Post> apost=result.getData();
        Post aPost=apost.get(numbers);


                   System.out.println("Post is :\n"+ aPost.getMessage());
                    try {
                        int number=aPost.getMessage().length();
//                                            s=aPost.getMessage().substring(0,number/16);
//                                            s+=aPost.getMessage().substring((number/16)+1,number/8);
//                                            s+="\n";
//                                            s+=aPost.getMessage().substring((number/8)+1,number/4);
//                                            s+="\n";
//                                            s+=aPost.getMessage().substring((number/4)+1,number/2);
//                                            s+="\n";
//                                            s+=aPost.getMessage().substring((number/2)+1,number);
//                                            s+="\n";
                        //     s+=aPost.getMessage().substring(2100,number);



//                                                operations.splitInput(aPost.getMessage());
//                                                aPost.getMessage().replaceAll(aPost.getMessage(), "");

                        // System.out.println("The Post is Bangla");

                        System.out.println("Date is :"+aPost.getCreatedTime());
                   //     counter++;

//                        PostWithDetailedReactions post =
//                                fbClient.fetchObject(aPost.getId() + "/reactions", PostWithDetailedReactions.class, Parameter.with("fields", fields));

                        JsonObject obj = fbClient.fetchObject(aPost.getId(), JsonObject.class,
                                Parameter.with("fields",
                                        "reactions.type(LOVE).limit(0).summary(total_count).as(reactions_love)," +
                                                "reactions.type(WOW).limit(0).summary(total_count).as(reactions_wow)," +
                                                "reactions.type(HAHA).limit(0).summary(total_count).as(reactions_haha)," +
                                                "reactions.type(LIKE).limit(0).summary(total_count).as(reactions_like)," +
                                                "reactions.type(ANGRY).limit(0).summary(total_count).as(reactions_angry)," +
                                                "reactions.type(SAD).limit(0).summary(total_count).as(reactions_sad)"));


                        //   System.out.println("REACTIONS ARE:" + obj.toString());
                        String str=obj.toString();
                        str = str.replaceAll("[^-?0-9]+", " ");
                        System.out.println(Arrays.asList(str.trim().split(" ")));
//                str=str.replace('[',' ');
//                str=str.replace(']',' ');

                        String[] integerStrings = str.split("[ ]");
                        String[] string2=new String[10];
                        int eq=0;
                        for(int k=1;k<integerStrings.length;k++)
                        {
                            if(k==6 || k==5);
                            else
                            {
                                string2[eq++]=integerStrings[k];
                            }
                        }
                        String[] reaction={"Haha","Angry","Sad","Like","Love","Wow"};

                      //  System.out.println("wow is "+string2[5]);

                        int sum=0;
                        for (int i = 0; i < eq; i++) {
                            //     System.out.println(string2[i]);
                            integers[i] = Integer.parseInt(string2[i]);
                            sum+=integers[i];

                        }
                        for (int i = 0; i < eq; i++) {
                            //     System.out.println(string2[i]);
                         integers[i]=(integers[i]*100)/sum;

                        }



                        for (int i = 0; i < 6; i++) {

                            System.out.println(reaction[i] +" : "+ integers[i]+"%");
                        }

                        OperationsBangla operationsBangla = new OperationsBangla();
                        operationsBangla.splitInputBangla();
                        String[] inArray = null;
                        String[] inArray2 = new String[10000];
                        Dictionary dictionary = new Dictionary("src/sample/spl1/Translation.txt");

                        inArray = aPost.getMessage().split("[ ,/;>.*'|\"(){+></@$%^&_=}]", 0);

                        aPost.getMessage().replaceAll(aPost.getMessage(), "");
                        for (int j = 0; j < inArray.length; j++) {
                            inArray2[j] = operationsBangla.searchBan(inArray[j]);
                        }
                        String inp = "";
                        for (int j = 0; j < inArray.length; j++) {
                            inArray2[j] = dictionary.search(inArray[j]);

                            inp = inp + inArray2[j] + " ";


                        }
                        operations.splitInput(inp);

                        operations.removeWord();
                        operations.search();
                        emCal.searchEmotion();
                        emCal.emotionCalc(stages);
                        emCal.DataOutputStream();

                    } catch (Exception ea) {
                        System.out.println("");
                    }
                    emCal.VisualOutputs(stages,integers[0],integers[1],integers[2],integers[3],integers[4],integers[5]);

//                    break;
//                }
//            }
//










    }




}
