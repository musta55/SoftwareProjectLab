package sample.spl1.visualOut;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.spl1.login.loginOrReg;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class accessToken {
    String tok=null;
    public String token(Stage stage, String Name,int num)
    {

        try {
            stage.setTitle("Text Input");

            TextArea textFields = new TextArea();

            Image Ab = new Image(new FileInputStream("src/Pictures/enter.png"));
            ImageView about = new ImageView(Ab);
            Button button = new Button(null,about);
            button.setBackground(null);

            button.setTranslateX(580);
            button.setTranslateY(438);

            try {
                Text headning = new Text("Enter Access Token");
                headning.setFont(Font.font(Font.getFontNames().get(12), FontPosture.REGULAR, 11));
                headning.setFill(Color.WHITE);
                headning.setScaleX(6);
                headning.setScaleY(6);
                headning.setTranslateX(650);
                headning.setTranslateY(90);
                Image backgrounds = new Image(getClass().getClassLoader().getResource("Pictures/newbg.png").toString(), true);
                Canvas canvas = new Canvas(1600,900);
                GraphicsContext gc = canvas.getGraphicsContext2D();
                //gc.drawImage(backgrounds,0,0);

                textFields.setLayoutX(150);
                textFields.setLayoutY(180);
                textFields.setPrefRowCount(5);
                textFields.setPrefColumnCount(6);
                textFields.setWrapText(true);
                textFields.setMinSize(1125,80);
                 tok=textFields.getText();


                Hyperlink link = new Hyperlink();
                link.setText("Click to get Access Token");

                link.setLayoutX(900);
                link.setLayoutY(450);
                link.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        try {
                            Desktop.getDesktop().browse(new URL("https://developers.facebook.com/apps/").toURI());
                        } catch (IOException eq) {
                            eq.printStackTrace();
                        } catch (URISyntaxException ee) {
                            ee.printStackTrace();
                        }
                    }
                });



                Hyperlink link2 = new Hyperlink();
                link2.setText("Help");

                link2.setLayoutX(200);
                link2.setLayoutY(450);
                link2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        try {
                          loginOrReg lg=new loginOrReg();
                          lg.logOrReg();
                        } catch (IOException eq) {
                            eq.printStackTrace();
                        } catch (URISyntaxException ee) {
                            ee.printStackTrace();
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                });
                Pane roots = new Pane();
                BackgroundImage bi = new BackgroundImage(backgrounds,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
                Background bg = new Background(bi);
                roots.setBackground(bg);
                roots.getChildren().addAll(canvas,textFields,button,headning,link,link2);

                Scene scene = new Scene(roots,1400,750);
                stage.setScene(scene);
                //primaryStage.setFullScreen(true);
                stage.show();
            }catch (Exception ex)
            {
                System.out.println("Picture url Problem");
            }

            button.setOnAction(action -> {


               if(num==1)
                {

                }

                if(num==0)
                {
                    Progression statusProg =new Progression();

                    statusProg.statusProgress(stage,textFields.getText(),Name);
                }
                else if(num==2 )
                {


//                    Progression p=new Progression();
//                    p.statusProgress(stage,textFields.getText());
                }
            });



        } catch (Exception excep) {
            excep.printStackTrace();
        }

        return tok;
    }
    public Button setStyle ( Button b)
    {
        b.setStyle("-fx-background-color: \n" +
                "        linear-gradient(\t#FFFFFF, \t#FFFFFF),\n" +
                "        linear-gradient(#FFFFFF, #FFFFFF),\n" +
                "        linear-gradient(\t#FFFFFF, #efaa22),\n" +
                "        linear-gradient(#ffe657 0%, #3CF53C 50%, #1ED71E 100%),\n" +
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
