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
    public firstPost()
    {

    }
   public   firstPost(String accessToken)
    {
            this.accessToken=accessToken;
    }
    public void firstpost(Stage stages)
    {
        Pane stat = new Pane();
        Scene scen = new Scene(stat, 2000, 900);


   //     String accessToken = "EAAMF6lCN2rABAL6ZCqIhEAzejYUMuQyccpK5G9Kq1rsvZAghc6Bac8OeVpzL0seJ6BpmaN2FNx5QkIjs7yom44YzruiPdwJISFImQsMAXGt9RG2THGAnaX9WBeBZCq7KBBSQku7pAlXobTZCZCKHThvIGAlePRNXRvekSC2kYJgZDZD ";
//        FacebookClient fbClient = new DefaultFacebookClient(accessToken);
//        FacebookClient.AccessToken exAccessToken = fbClient.obtainExtendedAccessToken("850928862091952", "ddb4cbe10a995e95acc505c91c9e73d5");

//        System.out.println(exAccessToken.getExpires());
//        System.out.println(exAccessToken.getAccessToken());



        Button status1 = new Button("Status 1");
        status1.setTranslateX(300);
        status1.setTranslateY(320);
        status1.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #0B2058 0%, #030B21 100%),\n" +
                "        #030B21,\n" +
                "        #0B2058,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #143389, #09236B);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 1.5em;");
        status1.setPrefSize(100, 30);
        status1.setTextFill(Color.WHITE);

        int numbers=0;
        TextArea textField = new TextArea();
        textField.setLayoutX(650);
        textField.setLayoutY(220);
        textField.setPrefRowCount(2);
        textField.setPrefColumnCount(2);
        textField.setWrapText(true);
        textField.setMinSize(105,3);

        Text headning = new Text("Enter Status No.");
        headning.setScaleX(3);
        headning.setScaleY(3);
        headning.setTranslateX(400);
        headning.setTranslateY(250);
        headning.setFill(Color.BLACK);
        headning.setFont(Font.font(Font.getFontNames().get(12), FontPosture.REGULAR, 14));



        headning.setCache(true);

        Button back = new Button("Back");
        back.setTranslateX(1200);
        back.setTranslateY(350);
        back.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #8d9092 0%, #717375 100%),\n" +
                "        #8d9092,\n" +
                "        #717375,\n" +
                "         radial-gradient(center 50% 50%, radius 100%, #143389, #09236B);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 1.1em;");
        back.setPrefSize(80, 30);


        back.setTextFill(Color.WHITE);
        back.setOnAction(e -> {
            try {
                String Name=null;
                fourthPage fp=new fourthPage();
                fp.runs(stages,accessToken,Name);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button button = new Button("Enter");
        button.setTextFill(Color.WHITE);
        setStyle(button);
        button.setTranslateX(620);
        button.setTranslateY(350);
        button.setPrefSize(150,70);
        button.setOnAction(action -> {

                    try {
                        postOne po = new postOne();
                        po.postone(stages, accessToken, Integer.parseInt(textField.getText())-1);
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }
                }
            );

        stages.setScene(scen);
        //    stages.setFullScreen(true);
        stages.show();
        stages.setTitle("STATUS 1 With Reaction");
        stat.getChildren().addAll(button,textField,headning,back);
        Image background = new Image(getClass().getClassLoader().getResource("emotion(16-9).png").toString(), true);



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
                "        radial-gradient(center 50% 50%, radius 100%, #2471A3    , #17202A);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 2.1em;");
        return b;
    }

}
