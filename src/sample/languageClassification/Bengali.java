package sample.languageClassification;

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
import sample.spl1.Dictionary;
import sample.spl1.Operations;
import sample.spl1.OperationsBangla;
import sample.spl1.emotioncal.EmotionCalculation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Bengali {
    public void ban(Stage primaryStage)
    {
        try {


            primaryStage.setTitle("Text Input");

            TextArea textFields = new TextArea();

            Button button = new Button("Enter");
            button.setTextFill(Color.WHITE);
            setStyle(button);
            button.setTranslateX(620);
            button.setTranslateY(350);
            button.setPrefSize(150,70);

            try {
                Text headning = new Text("TEXT INPUT");
                headning.setFont(Font.font(Font.getFontNames().get(12), FontPosture.REGULAR, 11));
                headning.setFill(Color.rgb(237, 134, 18));
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
                textFields.setMinSize(1125,150);
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
                primaryStage.setScene(scene);
                //primaryStage.setFullScreen(true);
                primaryStage.show();
            }catch (Exception ex)
            {
                System.out.println("Picture url Problem");
            }

            button.setOnAction(action -> {
                Operations operations = new Operations();

                OperationsBangla operationsBangla = new OperationsBangla();

                operationsBangla.splitInputBangla();
                String inputStringBan = "";
                ArrayList<String> inListBan = new ArrayList<String>();//to store the input words
                String[] inArray = new String[10000];
                String[] inArray2 = new String[10000];


                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                Dictionary dictionary = null;
                try {
                    dictionary = new Dictionary("isrc/spl1/Translaton.txt");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                inArray = textFields.getText().split("[ ,/;>.*'|\"(){+></@$%^&_=}]", 0);

                for (int j = 0; j < inArray.length; j++) {
                    //    System.out.println("askdas");
                    inArray2[j] = operationsBangla.searchBan(inArray[j]);
                    //    System.out.print("Bangla Language is ###################"+inArray2[j] + " ");
                }
                String inp = "";
                for (int j = 0; j < inArray.length; j++) {

                    //  System.out.print(inArray[j]+" ");
                    //   System.out.print(dictionary.search("\n\n\nDictionary search kore PAIiiiiiiiiiiiii"+inArray[j])+" ");
                    inArray2[j] = dictionary.search(inArray[j]);
                    //  inListBan.add(inArray2[j]);

                    //   System.out.print(inArray2[4]+" ");

                    inp = inp + inArray2[j] + " ";

                }

                try {
                    operations.splitInput(inp);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }

                try {
                    operations.removeWord();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                operations.search();


                EmotionCalculation emCal = new EmotionCalculation();

                try {
                    emCal.searchEmotion();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    emCal.emotionCalc(primaryStage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    emCal.VisualOutput(primaryStage,textFields.getText());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            });

        } catch (Exception excep) {
            excep.printStackTrace();
        }



    }


    public Button setStyle ( Button b)
    {
        b.setStyle("-fx-background-color: \n" +
                "        linear-gradient(#ffd65b, #e68400),\n" +
                "        linear-gradient(#ffef84, #f2ba44),\n" +
                "        linear-gradient(#ffea6a, #efaa22),\n" +
                "        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n" +
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
