package sample.spl1.visualOut;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.Post;
import javafx.stage.Stage;
import sample.spl1.Dictionary;
import sample.spl1.Operations;
import sample.spl1.OperationsBangla;
import sample.spl1.emotioncal.EmotionCalculation;

import java.io.File;
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

        EmotionCalculation emCal = new EmotionCalculation();
        System.out.println("File create hoise emcal e");
        emCal.fileOpen(Name);
        for (List<Post> apost : result) {
            for (Post aPost : apost) {
                counter++;
                if (counter >= 11) {
                    break;
                }
                emCal.fileOpen(Name);

                System.out.println(aPost.getMessage());

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
                        System.out.println("Successfully wrote to the date file.");
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                } catch (Exception ea) {
                    System.out.println();
                }
            }
            if (counter >= 11) {
                break;
            }
        }
        try {
            visualoutputfb.VisualOutputFacebook(stages,accessToken,Name);
        } catch (FileNotFoundException aex) {
            aex.printStackTrace();
        }
    }

}
