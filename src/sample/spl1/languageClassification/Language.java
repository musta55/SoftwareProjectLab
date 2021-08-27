package sample.spl1.languageClassification;

//import demo.sphinx.helloworld.HelloWorld;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.spl1.Dictionary;
import sample.spl1.Operations;
import sample.spl1.OperationsBangla;
import sample.spl1.secondPage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Language extends secondPage{

    public Text textAtt()
    {
        Text headning = new Text("User Input");
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
        textFields.setMinSize(1125,200);
        return textFields;
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
    public void NaturalLanguageProcessing(TextArea textFields)
    {
        Operations operations = new Operations();

        OperationsBangla operationsBangla = new OperationsBangla();

        operationsBangla.splitInputBangla();
        String inputStringBan = "";
        ArrayList<String> inListBan = new ArrayList<String>();//to store the input words
        String[] inArray = new String[10000];



        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Dictionary dictionary = null;
        try {
            dictionary = new Dictionary("isrc/spl1/Translaton.txt");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        inArray = textFields.getText().split("[ ,/;>.*'|\"(){+></@$%^&_=}]", 0);         //Bangla tokenization
        String[] inArray2 = new String[inArray.length];
        String[] inArray3 = new String[inArray.length];

//        for (int j = 0; j < inArray.length; j++) {
//            inArray2[j] = operationsBangla.searchBan(inArray[j]);       //Bangla lemmatization word by word
//        }

        String inp = "";
        for (int j = 0; j < inArray.length; j++) {
            inArray3[j] = dictionary.search(inArray[j]);
            inp = inp + inArray3[j] + " ";
        }

//        System.out.println("nlp theke bangla r value"+inp);
        try {
            operations.splitInput(inp);         //English tokenization
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        try {
            operations.removeWord();        //English stop word removal
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        operations.search();
    }
    public void NaturalLanguageProcessing(String Texts)
    {
        Operations operations = new Operations();

        OperationsBangla operationsBangla = new OperationsBangla();

        operationsBangla.splitInputBangla();
        String inputStringBan = "";
        ArrayList<String> inListBan = new ArrayList<String>();//to store the input words
        String[] inArray = new String[10000];



        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Dictionary dictionary = null;
        try {
            dictionary = new Dictionary("isrc/spl1/Translaton.txt");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        inArray = Texts.split("[ ,/;>.*'|\"(){+></@$%^&_=}]", 0);         //Bangla tokenization
        String[] inArray2 = new String[inArray.length];
        for (int j = 0; j < inArray.length; j++) {
            inArray2[j] = operationsBangla.searchBan(inArray[j]);       //Bangla lemmatization word by word
        }

        String inp = "";
        for (int j = 0; j < inArray2.length; j++) {
            inArray2[j] = dictionary.search(inArray2[j]);
            inp = inp + inArray2[j] + " ";
        }

//        System.out.println("nlp theke bangla r value"+inp);
        try {
            operations.splitInput(inp);         //English tokenization
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        try {
            operations.removeWord();        //English stop word removal
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        operations.search();
    }


    public void TheThird(Stage primaryStage) throws FileNotFoundException {

        Text text = new Text();

        text.setX(340);
        text.setY(140);
        text.setFont(Font.font("Abyssinica SIL", FontWeight.MEDIUM, FontPosture.REGULAR,60));
        text.setFill(Color.WHITE);// setting colour of the text to blue
        text.setStroke(Color.WHEAT); // setting the stroke for the text
        text.setStrokeWidth(1); // setting stroke width to 2
        text.setText("Select Your Language");


        Image Ab = new Image(new FileInputStream("src/Pictures/bangla.png"));
        ImageView about = new ImageView(Ab);
        Button ab = new Button(null,about);
        ab.setBackground(null);

        ab.setTranslateX(380);
        ab.setTranslateY(200);

        Image Ab2 = new Image(new FileInputStream("src/Pictures/bilingual.png"));
        ImageView about2 = new ImageView(Ab2);
        Button abi = new Button(null,about2);
        abi.setBackground(null);

        abi.setTranslateX(380);
        abi.setTranslateY(600);

        Image Ab3 = new Image(new FileInputStream("src/Pictures/english.png"));
        ImageView about3 = new ImageView(Ab3);
        Button eng = new Button(null,about3);
        eng.setBackground(null);

        eng.setTranslateX(380);
        eng.setTranslateY(400);

        Button back = getButton();

        back.setTranslateX(0);
        back.setTranslateY(340);
        back.setPrefSize(1, 5);
        back.setTextFill(Color.YELLOW);


        Image background = new Image(new FileInputStream("src/Pictures/newbg.png"));
        Pane root = new Pane();
        root.getChildren().addAll(eng,back,ab,abi,text);
        //       TextField nameInput=new TextField();


        abi.setOnAction(e -> {
          biLingual bl =new biLingual(primaryStage);
          {
          }
        });

        ab.setOnAction(e -> {
            Bengali bang =new Bengali();
            {
                bang.ban(primaryStage);
            }

        });

        eng.setOnAction(e->{

            English en =new English();
            {
                en.Eng(primaryStage);
            }

        });
        back.setOnAction(e->{
            try {
                secondPage goBack = new secondPage();
                goBack.TheSecond(primaryStage);
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });

        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        root.setBackground(bg);


        Scene scene = new Scene(root, 1400, 750);
        primaryStage.setScene(scene);
        //primaryStage.setFullScreen(true);
        primaryStage.show();
    }
}