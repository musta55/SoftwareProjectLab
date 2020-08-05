package sample.spl1.visualOut;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Post;
import javafx.stage.Stage;
import sample.spl1.Dictionary;
import sample.spl1.Operations;
import sample.spl1.OperationsBangla;
import sample.spl1.emotioncal.EmotionCalculation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Progression {
    public static int counter = 0;
    public void statusProgress(Stage stages,String accessToken,String Name)
    {
        System.out.println("Progression is :");
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
                EmotionCalculation emCal = new EmotionCalculation();
                Name+="fb";
                emCal.fileOpen(Name);

                System.out.println(aPost.getMessage());
                System.out.println("Post like is :"+aPost.getLikesCount());
                System.out.println("Post Source is :"+aPost.getSource());
                System.out.println("Post link is :"+aPost.getLink());
                try {

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
                        emCal.DataOutputStream(Name);

                    System.out.println("Post date is :"+aPost.getCreatedTime());

                    try {
                        Path path = Paths.get(Name+"date.txt");
                        Files.write(path, (Iterable<? extends CharSequence>) aPost.getCreatedTime(), StandardOpenOption.APPEND);
                        System.out.println("Successfully wrote to the file.");
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                } catch (Exception ea) {
                    System.out.println("");
                }
            }
        }
        try {
            visualoutputfb.VisualOutputFacebook(stages,accessToken,Name);
        } catch (FileNotFoundException aex) {
            aex.printStackTrace();
        }
    }

}
