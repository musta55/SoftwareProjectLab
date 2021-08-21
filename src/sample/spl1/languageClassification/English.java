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
import sample.spl1.Operations;
import sample.spl1.emotioncal.EmotionCalculation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class English {

    public void Eng(Stage primaryStage)
    {
        Language lang=new Language();

        try{
            Button backs = new Button("Back");
            backs.setTranslateX(50);
            backs.setTranslateY(20);
            lang.setStyle(backs);
            backs.setPrefSize(60, 30);
            Operations operations=new Operations();
            primaryStage.setTitle("User Input");
            TextArea textField =lang.txtarea();

            Image Ab = new Image(new FileInputStream("src/Pictures/enter.png"));
            ImageView about = new ImageView(Ab);
            Button button = new Button(null,about);
            button.setBackground(null);

            button.setTranslateX(580);
            button.setTranslateY(520);
            button.setOnAction(action -> {
                    // NLP
                try {
                    operations.splitInput(textField.getText());         //Tokenization
                    operations.removeWord();                            //Stop word removal
                }
                catch (FileNotFoundException exc) {
                    exc.printStackTrace();
                }
                operations.search();                                    //Lemmatization
                try {

                    //Lexical Affinity Method
                    EmotionCalculation emCal = new EmotionCalculation();
                    emCal.searchEmotion();
                    emCal.emotionCalc(primaryStage);
                    emCal.VisualOutput(primaryStage,textField.getText());

                } catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }); try {
                Text headning = lang.textAtt();

                Image backgrounds = new Image(new FileInputStream("src/Pictures/newbg.png"));
                Canvas canvas = new Canvas(1400,750);

                Pane roots = new Pane();
                BackgroundImage bi = new BackgroundImage(backgrounds,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                Background bg = new Background(bi);
             roots.setBackground(bg);
                roots.getChildren().addAll(canvas,textField,button,headning);
                Scene scene = new Scene(roots,1400,750);
                primaryStage.setScene(scene);
                primaryStage.show();
            }catch (Exception ex)
            {
                System.out.println("Picture url Problem");
            }

        } catch (Exception excep) {
            excep.printStackTrace();
        }

    }

}
