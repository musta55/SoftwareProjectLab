package sample.spl1;


import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Post;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class secondPage {

    InputStream is = null;
    DataInputStream dis = null;
    FileOutputStream fos = null;
    DataOutputStream dos = null;
    public void TheSecond(Stage stage) {
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));

        Text headning = new Text("Menu");
        headning.setFont(Font.font(Font.getFontNames().get(12), FontPosture.REGULAR, 14));
        headning.setScaleX(5);
        headning.setScaleY(5);
        headning.setTranslateX(630);
        headning.setTranslateY(70);
        headning.setFill(Color.DARKBLUE);
     //   headning.setEffect(ds);

        Button pro = new Button("User Input");
        pro.setTranslateX(500);
        pro.setTranslateY(170);
        setStyle(pro);
        pro.setPrefSize(300, 80);
        pro.setTextFill(Color.WHITE);

        Button socialMedia = new Button("Social Media");
        socialMedia.setTranslateX(500);
        socialMedia.setTranslateY(350);
        setStyle(socialMedia);
        socialMedia.setPrefSize(300, 80);
        socialMedia.setTextFill(Color.WHITE);

        Button liveUrl = new Button(" Web Page");
        liveUrl.setTranslateX(500);
        liveUrl.setTranslateY(530);
        setStyle(liveUrl);
        liveUrl.setPrefSize(300, 80);
        liveUrl.setTextFill(Color.WHITE);


        Button back = new Button("Back");
        back.setTranslateX(50);
        back.setTranslateY(20);
        back.setStyle("-fx-padding: 8 15 15 15;\n" +
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
        back.setPrefSize(80, 40);
        back.setTextFill(Color.WHITE);

        Image background = new Image(getClass().getClassLoader().getResource("emotionSide.png").toString(), true);
        Pane root = new Pane();

        root.getChildren().addAll(socialMedia,back,pro,headning,liveUrl);
        pro.setOnAction(e -> {
            try {

                ThirdPage PMenu = new ThirdPage();
                PMenu.TheThird(stage);
            } catch (Exception excep) {
                excep.printStackTrace();
            }
        });

        back.setOnAction(e->{
            try {
                Main goBack = new Main();
                goBack.start(stage);
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });

        socialMedia.setOnAction(e -> {
            try
            {
                fourthPage fPage=new fourthPage();
                fPage.runs(stage);
            }catch (Exception ea)
            {
                System.out.println("Hello");
            }

        });
       liveUrl.setOnAction(e -> {
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
        });







        Canvas canvas = new Canvas(1400,750);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(background,0,0);
        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        root.setBackground(bg);


        Scene scene = new Scene(root, 1400, 750);
        stage.setScene(scene);
        //stage.setFullScreen(true);
        stage.show();
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