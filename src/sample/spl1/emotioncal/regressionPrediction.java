package sample.spl1.emotioncal;

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
import sample.spl1.Dictionary;
import sample.spl1.Operations;
import sample.spl1.OperationsBangla;

import java.io.*;
import java.util.ArrayList;

public class regressionPrediction {

    public void prediction(Stage primaryStage,String accessToken,String Name,double[]a,double[]b)
    {


        try {


            primaryStage.setTitle("Text Input");


            TextArea textFields = new TextArea();
            Image Ab = new Image(new FileInputStream("src/Pictures/enter.png"));
            ImageView about = new ImageView(Ab);
            Button button = new Button(null,about);
            button.setBackground(null);

            button.setTranslateX(580);
            button.setTranslateY(520);

            Text headning = new Text("TEXT INPUT");
            headning.setFont(Font.font(Font.getFontNames().get(12), FontPosture.REGULAR, 11));
            headning.setFill(Color.WHITE);
            headning.setScaleX(6);
            headning.setScaleY(6);
            headning.setTranslateX(700);
            headning.setTranslateY(190);
            Image backgrounds = new Image(getClass().getClassLoader().getResource("Pictures/newbg.png").toString(), true);
            Canvas canvas = new Canvas(1600,900);
            GraphicsContext gc = canvas.getGraphicsContext2D();
            //gc.drawImage(backgrounds,0,0);


            textFields.setLayoutX(150);
            textFields.setLayoutY(280);
            textFields.setPrefRowCount(5);
            textFields.setPrefColumnCount(6);
            textFields.setWrapText(true);
            textFields.setMinSize(1025,200);
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
                    emCal.VisualOutputPred(primaryStage,accessToken,Name,a,b);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            });

        } catch (Exception excep) {
            excep.printStackTrace();
        }

    }

}
