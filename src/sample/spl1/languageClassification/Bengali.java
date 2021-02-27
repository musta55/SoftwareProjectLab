package sample.spl1.languageClassification;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.spl1.emotioncal.EmotionCalculation;

import java.io.FileInputStream;
import java.io.IOException;

public class Bengali {

    public void ban(Stage primaryStage)
    {
        Language lang=new Language();

        try {
            primaryStage.setTitle("Text Input");
            TextArea textFields = lang.txtarea();
            Image Ab = new Image(new FileInputStream("src/Pictures/enter.png"));
            ImageView about = new ImageView(Ab);
            Button button = new Button(null,about);
            button.setBackground(null);

            button.setTranslateX(580);
            button.setTranslateY(520);

            try {

                Text headning =  lang.textAtt();
                Image backgrounds = new Image(getClass().getClassLoader().getResource("Pictures/newbg.png").toString(), true);
                Canvas canvas = new Canvas(1600,900);
                Pane roots = new Pane();
                BackgroundImage bi = new BackgroundImage(backgrounds,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background bg = new Background(bi);
                roots.setBackground(bg);
                roots.getChildren().addAll(canvas,textFields,button,headning);
                Scene scene = new Scene(roots,1400,750);
                primaryStage.setScene(scene);
                primaryStage.show();
            }catch (Exception ex)
            {
                System.out.println("Picture url Problem");
            }

            button.setOnAction(action -> {
                lang.NaturalLanguageProcessing(textFields);
                EmotionCalculation emCal = new EmotionCalculation();
                try {
                    emCal.searchEmotion();
                    emCal.emotionCalc(primaryStage);
                    emCal.VisualOutput(primaryStage,textFields.getText());

                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });

        } catch (Exception excep) {
            excep.printStackTrace();
        }
    }



}
