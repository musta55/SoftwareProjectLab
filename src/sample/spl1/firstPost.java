package sample.spl1;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Post;
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


import java.io.FileNotFoundException;
import java.util.List;

public class firstPost {
    private String  accessToken;

   public   firstPost(String accessToken)
    {
            this.accessToken=accessToken;
    }
    public void firstpost(Stage stages,String Name)
    {
        Pane stat = new Pane();
        Stage stage =new Stage();
        Scene scen = new Scene(stat, 800, 437);

        int numbers=0;
        TextArea textField = new TextArea();
        textField.setLayoutX(520);
        textField.setLayoutY(50);
        textField.setPrefRowCount(2);
        textField.setPrefColumnCount(2);
        textField.setWrapText(true);
        textField.setMinSize(105,3);

        Text headning = new Text("Enter Status No.");
        headning.setScaleX(3);
        headning.setScaleY(3);
        headning.setTranslateX(370);
        headning.setTranslateY(70);
        headning.setFill(Color.BLACK);
        headning.setFont(Font.font(Font.getFontNames().get(12), FontPosture.REGULAR, 9));



        headning.setCache(true);

        Button back = new Button("");
        back.setGraphic(new ImageView("Pictures/backArrow - Copy.png"));
        back.setTranslateX(10);
        back.setTranslateY(150);
        back.setPrefSize(1, 5);
        back.setTextFill(Color.YELLOW);

     //   back.setPrefSize(80, 30);


        back.setTextFill(Color.WHITE);
        back.setOnAction(e -> {
            try {
                fourthPage fp=new fourthPage();
                fp.runs(stages,accessToken,Name);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button button = new Button("Enter");
        button.setTextFill(Color.WHITE);
        setStyle(button);
        button.setTranslateX(520);
        button.setTranslateY(160);
        button.setPrefSize(150,70);
        button.setOnAction(action -> {

                    try {
                        postOne po = new postOne();
                        po.postone(stages, accessToken, Name,Integer.parseInt(textField.getText())-1);
                        stage.close();
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }
                }
            );


        stage.setTitle("FB Post No");
        stat.getChildren().addAll(button,textField,headning,back);
        Image background = new Image(getClass().getClassLoader().getResource("Pictures/1x/yellow.jpg").toString(), true);



        Canvas canvas = new Canvas(1600,900);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //gc.drawImage(background,0,0);
        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        stat.setBackground(bg);
        stage.setScene(scen);
        stage.show();

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
