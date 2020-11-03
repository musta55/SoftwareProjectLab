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

import java.io.*;
import java.util.List;

public class firstPost {
    private final String  accessToken2;

   public   firstPost(String accessToken)
    {
            this.accessToken2=accessToken;
    }
    public void firstpost(Stage stages,String Name) throws IOException {
        Pane stat = new Pane();
        Stage stage =new Stage();
        Scene scen = new Scene(stat, 880, 437);

        int numbers=0;
        TextArea textField = new TextArea();
        textField.setLayoutX(520);
        textField.setLayoutY(180);
        textField.setPrefRowCount(2);
        textField.setPrefColumnCount(2);
        textField.setWrapText(true);
        textField.setMinSize(105,3);

        Text headning = new Text("Enter Status No.");
        headning.setScaleX(3);
        headning.setScaleY(3);
        headning.setTranslateX(320);
        headning.setTranslateY(200);
        headning.setFill(Color.WHITE);
        headning.setFont(Font.font(Font.getFontNames().get(12), FontPosture.REGULAR, 9));



        headning.setCache(true);

        File file = new File("tokenfb"+Name);

        System.out.println("file Name :"+"tokenfb"+Name);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st,tok = "";
        while ((st = br.readLine()) != null)
        {
            System.out.print(st);
            tok+=st;
        }
        
        System.out.print("First post e access Token : "+tok);


     FacebookClient fbClient = new DefaultFacebookClient(tok);
     //   FacebookClient fbClient = new DefaultFacebookClient("EAADoSRqMjgEBAE46YlWViaAFvekennjF4sMplaxwUroVVrLA0kD3jwQOHBxZClRJZBOLEiZAfSw3tDGvaA1BquxFq1IBMgO1lPqAZAdo4MFX8geCUOk53DwoKfqNALnaANFjw4uwIvSBElKHl06eUTwWZBMdE4fZAgrGGr3ot0W92NKs98E0LAzoVvPxnWuLcZD");
        //   FacebookClient.AccessToken exAccessToken = fbClient.obtainExtendedAccessToken("668106823992484 ", "f63f747f31e390a44f93891920364794");

        Connection<Post> result;
        result = fbClient.fetchConnection("me/feed", Post.class);


        String userInput = null;
        EmotionCalculation emCal = new EmotionCalculation();
        try
        {
            File file2=new File(Name);
            emCal.fileOpen(file2);

        }catch (Exception e)
        {
            thirdPage tp=new thirdPage();
            tp.app(stage,Name);
            System.out.println("Exception in openning file");
        }


        int counter=0;
        String status=null;
        int flag=-1;
        for (List<Post> apost : result) {
            for (Post aPost : apost) {
                counter++;
        if(aPost.getIsPublished()==null)
        {
            flag=0;
            break;

        }
            }

            if( flag==0)break;


        }
        Text postNo = new Text("Available Posts "+counter+1);
        postNo.setScaleX(4);
        postNo.setScaleY(4);
        postNo.setTranslateX(180);
        postNo.setTranslateY(340);
        postNo.setFill(Color.WHITE);
        postNo.setFont(Font.font(Font.getFontNames().get(12), FontPosture.REGULAR, 9));




        Button back = new Button("");
        back.setGraphic(new ImageView("Pictures/backArrow - Copy.png"));
        back.setTranslateX(10);
        back.setTranslateY(250);
        back.setPrefSize(1, 5);
        back.setTextFill(Color.YELLOW);

     //   back.setPrefSize(80, 30);


        back.setTextFill(Color.WHITE);
        back.setOnAction(e -> {
            try {
               AnalysisPage ap=new AnalysisPage(stage,Name,accessToken2);
               ap.analysis();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Image Ab = new Image(new FileInputStream("src/Pictures/enter.png"));
        ImageView about = new ImageView(Ab);
        Button button = new Button(null,about);
        button.setBackground(null);

        button.setTranslateX(400);
        button.setTranslateY(250);
        button.setOnAction(action -> {

                    try {
                        postOne po = new postOne();
                        po.postone(stages, accessToken2, Name,Integer.parseInt(textField.getText())-1);
                        stage.close();
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }
                }
            );


        stage.setTitle("FB Post No ");
        stat.getChildren().addAll(button,textField,headning,back,postNo);
        Image background = new Image(getClass().getClassLoader().getResource("Pictures/newbg.png").toString(), true);



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
