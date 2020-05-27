package sample.spl1;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class textField {
    public void text(Stage primaryStage,String status)
    {
        Text headning = new Text("POST");
        headning.setScaleX(6);
        headning.setScaleY(6);
        headning.setTranslateX(650);
        headning.setTranslateY(100);
        headning.setFill(Color.WHITE);
        headning.setFont(Font.font(Font.getFontNames().get(12), FontPosture.REGULAR, 14));



        Button back = new Button("Back");
        back.setTranslateX(1100);
        back.setTranslateY(650);
        back.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #8d9092 0%, #717375 100%),\n" +
                "        #8d9092,\n" +
                "        #717375,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #ffffff, #a1a3a6);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 1.1em;");
        back.setPrefSize(60, 30);

        back.setOnAction(e -> {
            try {

                firstPost  fp=new firstPost();
                fp.firstpost(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        headning.setCache(true);




        Image background = new Image(getClass().getClassLoader().getResource("sample/spl1/purssianBlue.jpg").toString(), true);
        //    Image background = new Image(getClass().getResource("sample/spl1/emotion.jpg").toExternalForm());
        Canvas canvas = new Canvas(1400,760);
//            GraphicsContext gc = canvas.getGraphicsContext2D();
//            gc.drawImage(background,0,0);

        Text sa=new Text();
        sa.setText(status);
        sa.getText();
       sa.setFill(Color.WHITE);
//        sa.setScaleX(4);
//        sa.setScaleX(4);
        sa.setTranslateX(50);
        sa.setTranslateY(250);
//        headning.setFill(Color.DARKBLUE);
//        headning.setFont(Font.font(Font.getFontNames().get(12), FontPosture.REGULAR, 14));
        Pane root = new Pane();
        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        root.setBackground(bg);
        root.getChildren().addAll(canvas,headning,sa,back);
        Scene scene = new Scene(root,1000,460);
        scene.setFill(Color.BLACK);

        Stage st=new Stage();
        st.setScene(scene);
        st.show();


    }


}
