package sample.spl1;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

import java.io.*;

import static demo.sphinx.helloworld.HelloWorld.setStyle;

public class WebPage {

    public void web(Stage stage,String Name)
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


            Button back = new Button("");
            back.setGraphic(new ImageView("Pictures/backArrow - Copy.png"));
            back.setTranslateX(0);
            back.setTranslateY(340);
            back.setPrefSize(1, 5);
            back.setTextFill(Color.YELLOW);
            back.setOnAction(esb->{
                try {

                    AnalysisPage ap=new AnalysisPage(stage,Name);
                }catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            });
            try {
                Text headning = new Text("WebPage Link");
                headning.setFont(Font.font(Font.getFontNames().get(12), FontPosture.REGULAR, 11));
                headning.setFill(Color.WHITE);
                headning.setScaleX(6);
                headning.setScaleY(6);
                headning.setTranslateX(850);
                headning.setTranslateY(90);
                Image backgrounds = new Image(getClass().getClassLoader().getResource("Pictures/1x/emotion(16-9)-0-3.jpg").toString(), true);
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
                roots.getChildren().addAll(canvas,textFields,button,headning,back);
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
                    String s=document.text().substring(700,x-551);

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


                    EmotionCalculation emCal = new EmotionCalculation(Name,2);


                        emCal.searchEmotion();

                        emCal.emotionCalc(stage);
                        emCal.DataOutputStreamProf();

                    emCal.VisualOutputProf(stage,t);



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
    public Button setStyle ( Button b)
    {
        b.setStyle("-fx-background-color: \n" +
                "        linear-gradient(\t#FFFFFF, \t#FFFFFF),\n" +
                "        linear-gradient(#FFFFFF, #FFFFFF),\n" +
                "        linear-gradient(\t#FFFFFF, #efaa22),\n" +
                "        linear-gradient(#ffe657 0%, #3CF53C 50%, #1ED71E 100%),\n" +
                "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 30;\n" +
                "    -fx-background-insets: 0,1,2,3,0;\n" +
                "    -fx-text-fill: #654b00;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 2.1em;\n" +
                "    -fx-padding: 10 20 10 20;");
        return b;
    }
    }

