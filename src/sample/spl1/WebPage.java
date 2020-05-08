package sample.spl1;

import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sample.spl1.emotioncal.EmotionCalculation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class WebPage {

    public void web(Stage stage)
    {


        try {
            System.out.println("running...");
            Document document;
            try {
                //Get Document object after parsing the html from given url.
                document = Jsoup.connect("https://www.thedailystar.net/frontpage/news/pm-warns-global-food-shortage-1895131").get();

                //      document = Jsoup.connect("google.com").get();


                String title = document.text(); //Get title
                System.out.println("  Title: " + title); //Print title.

                Operations operations=new Operations();

                operations.splitInput(title);

                operations.removeWord();
                operations.search();


                EmotionCalculation emCal = new EmotionCalculation();

                emCal.searchEmotion();
                emCal.emotionCalc(stage);

                emCal.VisualOutput(stage);

                Elements price = document.select(".zsg-photo-card-price:contains($)"); //Get price
                Elements address = document.select("span[itemprop]:contains(DenverCO)"); //Get address

                FileOutputStream fout=new FileOutputStream("output_zillow.csv");
                PrintStream csv=new PrintStream(fout);
                csv.println("name	price	number sold");

                for (int i=0; i < price.size()-2; i++)
                {
                    csv.println(address.get(i).text() + "	" + price.get(i).text());
                }
                fout.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } catch (Exception excep) {
            excep.printStackTrace();
        }

    }
}
