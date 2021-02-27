package sample.spl1.languageClassification;

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
import sample.spl1.emotioncal.EmotionCalculation;

import java.io.FileInputStream;
import java.io.IOException;

public class biLingual {
    private String Name;
    private Stage primaryStage;
    public String texts=null;
    public Text textAtt()
    {
        Text headning =new Text("Text Input");
        headning.setFont(Font.font(Font.getFontNames().get(12), FontPosture.REGULAR, 11));
        headning.setFill(Color.WHITE);
        headning.setScaleX(6);
        headning.setScaleY(6);
        headning.setTranslateX(650);
        headning.setTranslateY(190);
        return headning;

    }
    public TextArea txtarea()
    {
        TextArea textFields = new TextArea();
        textFields.setLayoutX(150);
        textFields.setLayoutY(280);
        textFields.setPrefRowCount(5);
        textFields.setPrefColumnCount(6);
        textFields.setWrapText(true);
        textFields.setMinSize(1025,200);
        return textFields;
    }


    public void biLanguage()
    {
        Language lang=new Language();
        try {
            primaryStage.setTitle("Text Input");
            TextArea textFields =lang.txtarea();
            Image Ab = new Image(new FileInputStream("src/Pictures/enter.png"));
            ImageView about = new ImageView(Ab);
            Button button = new Button(null,about);
            button.setBackground(null);

            button.setTranslateX(580);
            button.setTranslateY(520);

                Text headning =lang.textAtt();

                Image backgrounds = new Image(getClass().getClassLoader().getResource("Pictures/newbg.png").toString(), true);
                Canvas canvas = new Canvas(1600,900);
                GraphicsContext gc = canvas.getGraphicsContext2D();
                Pane roots = new Pane();
            BackgroundImage bi = new BackgroundImage(backgrounds,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            Background bg = new Background(bi);
                roots.setBackground(bg);
                roots.getChildren().addAll(canvas,textFields,button,headning);
                Scene scene = new Scene(roots,1400,750);
                primaryStage.setScene(scene);
                primaryStage.show();

            button.setOnAction(action -> {
                lang.NaturalLanguageProcessing(textFields);
                texts=textFields.getText();

            });

        } catch (Exception excep) {
            excep.printStackTrace();
        }

    }



 public   biLingual(Stage primaryStage,String Name)
    {
        Language lang=new Language();
        this.Name=Name;
        try {

            TextArea textFields = txtarea();
            Image Ab = new Image(new FileInputStream("src/Pictures/enter.png"));
            ImageView about = new ImageView(Ab);
            Button button = new Button(null,about);
            button.setBackground(null);

            button.setTranslateX(580);
            button.setTranslateY(520);

                Text headning = textAtt();
                Image backgrounds = new Image(getClass().getClassLoader().getResource("Pictures/newbg.png").toString(), true);
                Canvas canvas = new Canvas(1600,900);
                GraphicsContext gc = canvas.getGraphicsContext2D();
                //gc.drawImage(backgrounds,0,0);



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
                lang.NaturalLanguageProcessing(textFields);
                texts=textFields.getText();
                EmotionCalculation emCal = new EmotionCalculation(Name);
                try {
                    emCal.searchEmotion();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    emCal.emotionCalc(primaryStage);
                    if(Name!=null)
                        emCal.DataOutputStreamProf();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    emCal.VisualOutputProf(primaryStage,texts);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

        } catch (Exception excep) {
            excep.printStackTrace();
        }
    }

public    biLingual(Stage primaryStage)
    {
        Language lang=new Language();
        this.primaryStage=primaryStage;
        try {
            primaryStage.setTitle("Text Input");
            TextArea textFields =txtarea();
            Image Ab = new Image(new FileInputStream("src/Pictures/enter.png"));
            ImageView about = new ImageView(Ab);
            Button button = new Button(null,about);
            button.setBackground(null);
            button.setTranslateX(580);
            button.setTranslateY(520);

            try {
                Text headning =textAtt();
                Image backgrounds = new Image(getClass().getClassLoader().getResource("Pictures/newbg.png").toString(), true);
                Canvas canvas = new Canvas(1600,900);
                GraphicsContext gc = canvas.getGraphicsContext2D();

                Pane roots = new Pane();
                BackgroundImage bi = new BackgroundImage(backgrounds, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
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
                texts=textFields.getText();
                EmotionCalculation emCal = new EmotionCalculation();
                try {
                    emCal.searchEmotion();
                    emCal.emotionCalc(primaryStage);
                    if(Name!=null)
                        emCal.DataOutputStreamProf();
                        emCal.VisualOutput(primaryStage,texts);
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
