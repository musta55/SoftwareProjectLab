package sample.spl1;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class textField {
    public void text(Stage primaryStage,String status,String Name)
    {
        Text headning = new Text("POST");
        headning.setScaleX(3);
        headning.setScaleY(3);
        headning.setTranslateX(650);
        headning.setTranslateY(30);
        headning.setFill(Color.WHITE);
        headning.setFont(Font.font(Font.getFontNames().get(12), FontPosture.REGULAR, 14));




        headning.setCache(true);

              Image background = new Image(getClass().getClassLoader().getResource("Pictures/pngfuel.com - Copy.png").toString(), true);
               Canvas canvas = new Canvas(1200, 800);
               TextArea textField = new TextArea();
               textField.setLayoutX(427);
               textField.setLayoutY(130);
               textField.setPrefRowCount(5);
               textField.setPrefColumnCount(6);
               textField.setWrapText(true);
               textField.setMinSize(525, 550);
               textField.setText(status);

               Pane root = new Pane();
               BackgroundImage bi = new BackgroundImage(background,
                       BackgroundRepeat.NO_REPEAT,
                       BackgroundRepeat.NO_REPEAT,
                       BackgroundPosition.DEFAULT,
                       BackgroundSize.DEFAULT);
               Background bg = new Background(bi);
              root.setBackground(bg);
               root.getChildren().addAll(canvas, headning, textField);
               Scene scene = new Scene(root, 600, 800);
               scene.setFill(Color.BLACK);

               Stage st = new Stage();
               st.setScene(scene);

               st.show();

    }


}