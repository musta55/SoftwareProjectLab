package sample.spl1;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
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
import sample.spl1.emotioncal.EmotionCalculation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static demo.sphinx.helloworld.HelloWorld.setStyle;

public class WebPage {

    public void web(Stage stage)
    {


        try {
            stage.setTitle("Text Input");

            TextArea textFields = new TextArea();

            Button button = new Button("Enter");
            button.setTextFill(Color.WHITE);
            setStyle(button);
            button.setTranslateX(620);
            button.setTranslateY(350);
            button.setPrefSize(150,70);

            try {
                Text headning = new Text("Enter WebPage Link");
                headning.setFont(Font.font(Font.getFontNames().get(12), FontPosture.REGULAR, 11));
                headning.setFill(Color.DARKBLUE);
                headning.setScaleX(6);
                headning.setScaleY(6);
                headning.setTranslateX(650);
                headning.setTranslateY(90);
                Image backgrounds = new Image(getClass().getClassLoader().getResource("emotion(16-9).png").toString(), true);
                Canvas canvas = new Canvas(1600,900);
                GraphicsContext gc = canvas.getGraphicsContext2D();
                //gc.drawImage(backgrounds,0,0);

                textFields.setLayoutX(150);
                textFields.setLayoutY(180);
                textFields.setPrefRowCount(5);
                textFields.setPrefColumnCount(6);
                textFields.setWrapText(true);
                textFields.setMinSize(865,35);
                Pane roots = new Pane();
                BackgroundImage bi = new BackgroundImage(backgrounds,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
                Background bg = new Background(bi);
                roots.setBackground(bg);
                roots.getChildren().addAll(canvas,textFields,button,headning);
                Scene scene = new Scene(roots,1400,750);
                stage.setScene(scene);
                //primaryStage.setFullScreen(true);
                stage.show();
            }catch (Exception ex)
            {
                System.out.println("Picture url Problem");
            }

            button.setOnAction(action -> {
                System.out.println("running...");
                Document document = null;

            //Get Document object after parsing the html from given url.
                try {
                    Operations operations=new Operations();
                    document = Jsoup.connect(textFields.getText()).get();
                    int x=document.text().length();
                    String s=document.text().substring(700,x-551-1852);

                    String t=null;
                   String text[]= s.split("[.,]",0);
                    for(int i=0;i<text.length;i++)
                    {
                      t=t+text[i]+"\n";
                    }


                    System.out.println("  Title: " + t); //Print title.
                    operations.splitInput(t);
                    operations.removeWord();
                    operations.search();


                    EmotionCalculation emCal = new EmotionCalculation();


                        emCal.searchEmotion();

                        emCal.emotionCalc(stage);

                    emCal.VisualOutput(stage,t);



                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //      document = Jsoup.connect("google.com").get();







            Elements price = document.select(".zsg-photo-card-price:contains($)"); //Get price
            Elements address = document.select("span[itemprop]:contains(DenverCO)"); //Get address

                FileOutputStream fout= null;
                try {
                    fout = new FileOutputStream("output_zillow.csv");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                PrintStream csv=new PrintStream(fout);
            csv.println("name	price	number sold");

            for (int i=0; i < price.size()-2; i++)
            {
                csv.println(address.get(i).text() + "	" + price.get(i).text());
            }
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (Exception excep) {
            excep.printStackTrace();
        }


    }

    }

