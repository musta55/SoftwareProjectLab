package sample.visualOut;

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
import sample.spl1.Operations;
import sample.spl1.emotioncal.EmotionCalculation;
import sample.spl1.fourthPage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static demo.sphinx.helloworld.HelloWorld.setStyle;

public class accessToken {

    public void token(Stage stage, String Name)
    {


        try {
            stage.setTitle("Text Input");

            TextArea textFields = new TextArea();

            Button button = new Button("Enter");
            button.setTextFill(Color.WHITE);
            setStyle(button);
            button.setTranslateX(620);
            button.setTranslateY(350);
            button.setPrefSize(120,30);

            try {
                Text headning = new Text("Enter Access token");
                headning.setFont(Font.font(Font.getFontNames().get(6), FontPosture.REGULAR, 5));
                headning.setFill(Color.DARKBLUE);
                headning.setScaleX(4);
                headning.setScaleY(4);
                headning.setTranslateX(50);
                headning.setTranslateY(90);
                Image backgrounds = new Image(getClass().getClassLoader().getResource("emotion(16-9).png").toString(), true);
                Canvas canvas = new Canvas(700,650);
                GraphicsContext gc = canvas.getGraphicsContext2D();
                //gc.drawImage(backgrounds,0,0);

                textFields.setLayoutX(150);
                textFields.setLayoutY(180);
                textFields.setPrefRowCount(5);
                textFields.setPrefColumnCount(6);
                textFields.setWrapText(true);
                textFields.setMinSize(465,15);
                Pane roots = new Pane();
                BackgroundImage bi = new BackgroundImage(backgrounds,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
                Background bg = new Background(bi);
                roots.setBackground(bg);
                roots.getChildren().addAll(canvas,textFields,button,headning);
                Scene scene = new Scene(roots,700,650);
                stage.setScene(scene);
                //primaryStage.setFullScreen(true);
                stage.show();
            }catch (Exception ex)
            {
                System.out.println("Picture url Problem");
            }

            button.setOnAction(action -> {
                fourthPage fPage=new fourthPage();
                fPage.runs(stage,textFields.getText(),Name);
            });

        } catch (Exception excep) {
            excep.printStackTrace();
        }


    }


}
