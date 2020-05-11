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
import sample.spl1.Operations;
import sample.spl1.emotioncal.EmotionCalculation;

import java.io.FileNotFoundException;

public class English {

    public void Eng(Stage primaryStage)
    {

        try{
            Button backs = new Button("Back");
            backs.setTranslateX(50);
            backs.setTranslateY(20);
            setStyle(backs);
            backs.setPrefSize(60, 30);


            Operations operations=new Operations();
            primaryStage.setTitle("User Input");

            TextArea textField = new TextArea();


            Button button = new Button("Enter");
            button.setTextFill(Color.WHITE);
            setStyle(button);
            button.setTranslateX(620);
            button.setTranslateY(350);
            button.setPrefSize(150,70);
            button.setOnAction(action -> {

                try {
                    operations.splitInput(textField.getText());
                } catch (FileNotFoundException exc) {
                    exc.printStackTrace();
                }
                try {
                    operations.removeWord();
                } catch (FileNotFoundException exc) {
                    exc.printStackTrace();
                }
                operations.search();

                try {
                    EmotionCalculation emCal = new EmotionCalculation();
                    // launch(args);
                    emCal.searchEmotion();
                    // launch(args);
                    emCal.emotionCalc(primaryStage);
                    emCal.VisualOutput(primaryStage);

                } catch (Exception ex)
                {
                    ex.printStackTrace();

                }


            }); try {
                Text headning = new Text("USER INPUT");
                headning.setFont(Font.font(Font.getFontNames().get(12), FontPosture.REGULAR, 11));
                headning.setFill(Color.DARKBLUE);
                headning.setScaleX(6);
                headning.setScaleY(6);
                headning.setTranslateX(650);
                headning.setTranslateY(90);

                Image backgrounds = new Image(getClass().getClassLoader().getResource("emotion(16-9).png").toString(), true);
                Canvas canvas = new Canvas(1400,750);
                GraphicsContext gc = canvas.getGraphicsContext2D();
                //gc.drawImage(backgrounds,0,0);

                textField.setLayoutX(150);
                textField.setLayoutY(180);
                textField.setPrefRowCount(5);
                textField.setPrefColumnCount(6);
                textField.setWrapText(true);
                textField.setMinSize(1125,150);
                Pane roots = new Pane();
                BackgroundImage bi = new BackgroundImage(backgrounds,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
                Background bg = new Background(bi);
                roots.setBackground(bg);
                roots.getChildren().addAll(canvas,textField,button,headning);
                Scene scene = new Scene(roots,1400,750);
                primaryStage.setScene(scene);
                //primaryStage.setFullScreen(true);
                primaryStage.show();
            }catch (Exception ex)
            {
                System.out.println("Picture url Problem");
            }

//
//                        VBox vbox = new VBox(textField, button);
//
//                        Scene scene = new Scene(vbox, 1600, 800);
//                        primaryStage.setScene(scene);
//                        primaryStage.show();


        } catch (Exception excep) {
            excep.printStackTrace();
        }


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