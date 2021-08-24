package sample.spl1.FuzzyLogic;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.spl1.AnalysisPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static sample.spl1.visualOut.visualoutputfb.getButton;

public class FuzzyController {
  // private FuzzyFrame frame;
    private final FuzzyVariable POW;
    private final FuzzyVariable CON;
    private final FuzzyVariable REC;
    private final FuzzyVariable STAB;
    private final FuzzyVariable decision;
    private double[] conEvaluate;
    private double[] powEvaluate;
    private final double[] personalityTest;

    private double[] recEvaluate;
    private double[] stabEvaluate;
    private final Stage stage;
    private ArrayList<FuzzySet> fuzzySets;
    private ArrayList<FuzzySet> fuzzySets2;

    public FuzzyController(double[] personalityTest, Stage stage,String Name,String accessToken2) throws FileNotFoundException {
     //   frame = new FuzzyFrame();
        POW = new FuzzyVariable();
        CON = new FuzzyVariable();
        REC = new FuzzyVariable();
        STAB = new FuzzyVariable();
        this.personalityTest=personalityTest;
        this.stage=stage;

        decision = new FuzzyVariable();
        initialiazeVariable(stage,Name,accessToken2);
  //      initializeListener();
    //    frame.setVisible(true);
    }



    private void initialiazeVariable(Stage stage,String Name,String accessToken) throws FileNotFoundException {
        fuzzySets = new ArrayList<>();

        TriangleFuzzySet powerLow = new TriangleFuzzySet(Double.NEGATIVE_INFINITY, 0.7, 0.5, 1);
        TriangleFuzzySet powerMed = new TriangleFuzzySet(0.2, 0.8, 1);
        TriangleFuzzySet powerHigh= new TriangleFuzzySet(.2, Double.POSITIVE_INFINITY, 0.7, 1);

        POW.add(powerLow);
        POW.add(powerMed);
        POW.add(powerHigh);

        TriangleFuzzySet conLow = new TriangleFuzzySet(Double.NEGATIVE_INFINITY, 0.7, 0.5, 1);
        TriangleFuzzySet conMed = new TriangleFuzzySet(0.2, 0.8, 1);
        TriangleFuzzySet conHigh= new TriangleFuzzySet(.2, Double.POSITIVE_INFINITY, 0.7, 1);

        CON.add(conLow);
        CON.add(conMed);
        CON.add(conHigh);


        TriangleFuzzySet recLow = new TriangleFuzzySet(Double.NEGATIVE_INFINITY, 0.7, 0.5, 1);
        TriangleFuzzySet recMed = new TriangleFuzzySet(0.2, 0.8, 1);
        TriangleFuzzySet recHigh= new TriangleFuzzySet(.2, Double.POSITIVE_INFINITY, 0.7, 1);

        REC.add(recLow);
        REC.add(recMed);
        REC.add(recHigh);

        TriangleFuzzySet stabLow = new TriangleFuzzySet(Double.NEGATIVE_INFINITY, 0.7, 0.5, 1);
        TriangleFuzzySet stabMed = new TriangleFuzzySet(0.2, 0.8, 1);
        TriangleFuzzySet stabHigh= new TriangleFuzzySet(0.2, 0.8, 1);

        STAB.add(stabLow);
        STAB.add(stabMed);
        STAB.add(stabHigh);

        fuzzySets2 = new ArrayList<>();
        fuzzySets2.add(new FuzzySet("The Thinker", 0.0,0.6));
        fuzzySets2.add(new FuzzySet("The Supporter",0.0 ,0.7));
        fuzzySets2.add(new FuzzySet("The Socializer", 0.0, 0.8));
        fuzzySets2.add(new FuzzySet("The Director", 0.0,0.9));

        personalityTest(stage,Name,accessToken);

    }

    public void personalityTest(Stage stage,String Name,String accessToken) throws FileNotFoundException {

        double pow =personalityTest[0];
        double con = personalityTest[1];

        conEvaluate = CON.evaluate(con);
        powEvaluate = POW.evaluate(pow);


        double rec = personalityTest[2];
        double stab = personalityTest[3];

        recEvaluate = REC.evaluate(rec);
        stabEvaluate = STAB.evaluate(stab);



        andOperatorRules();
        centroidDefuzz(stage,Name,accessToken);


    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }


    public void andOperatorRules(){
        fuzzySets.clear();

        int temp=-1;
        if(powEvaluate[2] > 0){

            if(powEvaluate[2]>0){
                fuzzySets.add(new FuzzySet("The Director", Double.min(powEvaluate[2], powEvaluate[2]),1.0));
                temp=3;
            }
            if(conEvaluate[1] > 0){
                fuzzySets.add(new FuzzySet("The socializer", Double.min(conEvaluate[1], powEvaluate[2]), 0.9));
                temp=2;

            }
            if(conEvaluate[0] > 0){
                fuzzySets.add(new FuzzySet("The supporter", Double.min(conEvaluate[0], powEvaluate[2]) ,0.7));
                temp=1;
            }

        }
        if(powEvaluate[1] > 0){

            if(conEvaluate[2]>0){
                fuzzySets.add(new FuzzySet("The Supporter", Double.min(conEvaluate[2], powEvaluate[1]) ,0.8));
                temp=1;
            }
            if(conEvaluate[1] > 0){
                fuzzySets.add(new FuzzySet("The Supporter", Double.min(conEvaluate[1], powEvaluate[1]), 0.8));
                temp=1;
            }
            if(conEvaluate[0] > 0){
                fuzzySets.add(new FuzzySet("The Thinker", Double.min(conEvaluate[0], powEvaluate[1]) ,0.6));
                temp=0;
            }

        }
        if(powEvaluate[0] > 0){

            if(conEvaluate[2]>0){
                fuzzySets.add(new FuzzySet("The Socializer", Double.min(conEvaluate[2], powEvaluate[0]) ,0.7));
                temp=2;
            }
            if(conEvaluate[1] > 0){
                fuzzySets.add(new FuzzySet("The Thinker", Double.min(conEvaluate[1], powEvaluate[0]),0.6));
                temp=0;
            }
            if(conEvaluate[0] > 0){
                fuzzySets.add(new FuzzySet("The Thinker", Double.min(conEvaluate[0], powEvaluate[0]), 0.6));
                temp=0;
            }

        }




        if(recEvaluate[2] > 0){

            if(recEvaluate[2]>0){
                fuzzySets.add(new FuzzySet("The  Director", Double.min(recEvaluate[2], recEvaluate[2]),1.0));
                temp=3;
            }
            if(stabEvaluate[1] > 0){
                fuzzySets.add(new FuzzySet("The Socializer", Double.min(stabEvaluate[1], recEvaluate[2]), 0.9));
                temp=2;
            }
            if(stabEvaluate[0] > 0){
                fuzzySets.add(new FuzzySet("The Thinker", Double.min(stabEvaluate[0], recEvaluate[2]) ,0.7));
                temp=0;
            }

        }
        if(recEvaluate[1] > 0){

            if(stabEvaluate[2]>0){
                fuzzySets.add(new FuzzySet("The Socializer", Double.min(stabEvaluate[2], powEvaluate[1]) ,0.8));
                temp=2;
            }
            if(stabEvaluate[1] > 0){
                fuzzySets.add(new FuzzySet("The Socializer", Double.min(stabEvaluate[1], powEvaluate[1]), 0.8));
                temp=2;
            }
            if(stabEvaluate[0] > 0){
                fuzzySets.add(new FuzzySet("The Thinker", Double.min(stabEvaluate[0], powEvaluate[1]) ,0.6));
                temp=0;
            }

        }
        if(recEvaluate[0] > 0){

            if(stabEvaluate[2]>0){
                fuzzySets.add(new FuzzySet("The Supporter", Double.min(stabEvaluate[2], recEvaluate[0]) ,0.7));
                temp=1;
            }
            if(stabEvaluate[1] > 0){
                fuzzySets.add(new FuzzySet("The Thinker", Double.min(stabEvaluate[1], recEvaluate[0]),0.6));
                temp=0;
            }
            if(stabEvaluate[0] > 0){
                fuzzySets.add(new FuzzySet("The Thinker", Double.min(stabEvaluate[0], recEvaluate[0]), 0.6));
                temp=0;
            }

        }



    }

    public void centroidDefuzz(Stage stage,String Name,String accessToken2) throws FileNotFoundException {
  //      frame.appendCustomText("\n\nMethod Centroids\n");

        System.out.println("\n\nMethod Centroids\n");
        double result1 = 0;
        double result2 = 0;
        for (int i=0 ; i < fuzzySets.size() ; i++) {
            result1 += (fuzzySets.get(i).value * fuzzySets.get(i).index);
            result2 += fuzzySets.get(i).value;
        }

        double result = result1/result2;
//        frame.appendCustomText("Crisp decision index = " + result+"\n");
//        frame.appendCustomText("Fuzzy decision index :\n");

        int variable=-1,variable2=-1;
        double temporary=0,temporary2=0;

        System.out.println("Crisp decision index = " + result+"\n"+"Fuzzy decision index :\n");


        for (int i=0 ; i < fuzzySets2.size() ; i++) {

            boolean found = false;

            if(Double.compare(result,fuzzySets2.get(i).index) == 0){
       //         frame.appendCustomText(fuzzySets2.get(i).name +" = "+ "100%" + "\n");
                System.out.println(fuzzySets2.get(i).name +" = "+ "100%" + "\n");
                found = true;

                variable=i;
            }

            if(result > fuzzySets2.get(i).index && result <= fuzzySets2.get(i).index+0.1){
                double percentage = Math.abs((result - (fuzzySets2.get(i).index+0.1))/0.1);
                percentage = round(percentage, 2);
     //           frame.appendCustomText(fuzzySets2.get(i).name +" = "+ percentage*100 + "%\n");
                System.out.println(fuzzySets2.get(i).name +" = "+ percentage*100 + "%\n");
                temporary=percentage*100;
                System.out.println("1st one "+ temporary);

                variable=i+1;
                found = true;
            }

            if(result < fuzzySets2.get(i+1).index && result >= (fuzzySets2.get(i+1).index)-0.1){
                double percentage = Math.abs(((fuzzySets2.get(i+1).index-0.1) - result)/0.1);
                percentage = round(percentage, 2);
          //      frame.appendCustomText(fuzzySets2.get(i+1).name +" = "+ percentage*100 + "%\n");
                System.out.println(fuzzySets2.get(i+1).name +" = "+ percentage*100 + "%\n");
                temporary2=percentage*100;
                System.out.println("2nd one "+ temporary2);

                variable2=i+1;

                System.out.println("2nd one variable is "+ variable2);

                found = true;
            }

            if(found)
                break;
        }



        Image background = new Image(new FileInputStream("src/Pictures/Personality Page.png"));

        Text textstrng=new Text();
        textstrng.setFill(Color.WHITE);
        textstrng.setFont(javafx.scene.text.Font.font("Comic Sans MS", FontWeight.SEMI_BOLD, 20));
        textstrng.setStyle("-fx-font-size: 20px;");
        textstrng.setX(540);
        textstrng.setY(300);

        Text textWeak=new Text();


        textWeak.setFill(Color.WHITE);
        textWeak.setFont(javafx.scene.text.Font.font("Comic Sans MS", FontWeight.SEMI_BOLD, 20));
        textWeak.setStyle("-fx-font-size: 20px;");
        textWeak.setX(960);
        textWeak.setY(300);

        Text textPerson=new Text();
        textPerson.setFill(Color.WHITE);
        textPerson.setFont(javafx.scene.text.Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        textPerson.setStyle("-fx-font-size: 30px;");
        textPerson.setX(190);
        textPerson.setY(430);

        Image happy = new Image(new FileInputStream("src/Pictures/gif/happy.gif"));
        ImageView ivH = new ImageView(happy);
        ivH.setX(100);
        ivH.setY(420);
        ivH.setScaleX(0.7);
        ivH.setScaleY(0.7);
        ivH.setPreserveRatio(true);


        System.out.println(temporary +" and temp 2 is "+temporary2);

        if(temporary>temporary2)
        {

            if(variable==0)
            {
                textPerson.setText("The Thinker");
                textstrng.setText(
                        "Creative\n" +
                        "Dependable\n" +
                        "Imaginative\n" +
                        "Independent\n" +
                        "Detailed\n" +
                        "Organized\n" +
                        "Intelligent\n" +
                        "Analytical\n" +
                        "Critical thinker\n" +
                        "Thoughtful");

                textWeak.setText("Worry about progress\n" +
                        "Can appear unsocial\n" +
                        "Critical behavior\n" +
                        "Likes to do things \n"+"their own way\n" +
                        "Detached behavior\n" +
                        "Can see the glass \n"+" half empty\n" +
                        "Skeptical, disbelieving\n" +
                        "May never have   \n"+"personal expectations met\n" +
                        "Disengagement");

            }


            else    if(variable==1)
            {
                textPerson.setText("The Supporter");

                textstrng.setText(
                        "Caring\n" +
                        "Sincere\n" +
                        "Compassionate\n" +
                        "Stable\n" +
                        "Fair and equitable\n" +
                        "Looks approachable\n" +
                        "Dependable\n" +
                        "Appearance of strength\n" +
                        "Trusting\n" +
                        "Minimal mood swings\n" +
                        "Self-confident\n" +
                        "Reliable\n" +
                        "Consistent\n" +
                        "Observant\n"
                      );

                textWeak.setText("Not speaking up\n" +
                        "Easily used by others\n" +
                        "Going along when \n"+" they don’t agree\n" +
                        "Uncomfortable with  \n"+" constant change\n" +
                        "Going along \n"+"  to avoid confrontation\n" +
                        "Less assertive\n" +
                        "Gets hurt feelings\n" +
                        "Shy\n" +
                        "Resistant to change");



            }

            else    if(variable==2)
            {
                textPerson.setText("The Socializer");
                textstrng.setText("Enthusiasm\n" +
                        "Easily liked by \n"+"most people\n" +
                        "Friendliness\n" +
                        "Charismatic\n" +
                        "motivator\n" +
                        "Dreamer\n" +
                        "People-oriented\n" +
                        "Faster-paced\n" +
                        "Self-confident");

                textWeak.setText("Too self-involved\n" +
                        "Impatient\n" +
                        "Sometimes unrealistic\n" +
                        "Trouble being alone\n" +
                        "Doesn’t finish  \n"+"what was started\n" +
                        "Short attention span\n" +
                        "Arrogant or cocky\n" +
                        "Easily bored\n" +
                        "Self-indulgent\n" +
                        "Prone to sweeping \n"+" generalizations\n" +
                        "Impulsive\n"
                     );




            }
            else
            {
                textPerson.setText("The Director");
                textstrng.setText("Goal-oriented\n" +
                        "Risk-taking\n" +
                        "Good under stress\n"+"Ambitious\n" +
                        "Passionate\n" +
                        "Dominant\n" +
                        "Good administrative skills\n" +
                        "Highly competitive\n" +
                        "Multitasking");

                textWeak.setText("Stubborn\n" +
                        "Workaholic\n" +
                        "Impatient\n" +
                        "Tough\n"
                     +"Insensitive\n" +
                        "Ill-tempered \n" +
                        "Intolerant");


            }

        }
        else
        {

            if(variable2==0)
            {
                textPerson.setText("The Thinker");
                textstrng.setText(
                        "Creative\n" +
                                "Dependable\n" +
                                "Imaginative\n" +
                                "Independent\n" +
                                "Detailed\n" +
                                "Organized\n" +
                                "Intelligent\n" +
                                "Analytical\n" +
                                "Critical thinker\n" +
                                "Thoughtful");

                textWeak.setText("Worry about progress\n" +
                        "Can appear unsocial\n" +
                        "Critical behavior\n" +
                        "Likes to do things \n"+"their own way\n" +
                        "Detached behavior\n" +
                        "Can see the glass \n"+" half empty\n" +
                        "Disengagement");

            }


            else    if(variable2==1)
            {
                textPerson.setText("The Supporter");

                textstrng.setText(
                        "Caring\n" +
                                "Sincere\n" +
                                "Compassionate\n" +
                                "Stable\n" +
                                "Fair and equitable\n" +
                                "Looks approachable\n" +
                                "Dependable\n" +
                                "Observant\n"
                );

                textWeak.setText("Not speaking up\n" +
                        "Easily used by others\n" +
                        "Going along when \n"+" they don’t agree\n" +
                        "Less assertive\n" +
                        "Gets hurt feelings\n" +
                        "Shy\n" +
                        "Resistant to change");



            }

            else    if(variable2==2)
            {
                textPerson.setText("The Socializer");
                textstrng.setText("Enthusiasm\n" +
                        "Easily liked by \n"+"most people\n" +
                        "Friendliness\n" +
                        "Charismatic\n" +
                        "Self-confident");

                textWeak.setText("Too self-involved\n" +
                        "Impatient\n" +
                        "Sometimes unrealistic\n" +
                        "Trouble being alone\n" +
                        "Doesn’t finish  \n"+"what was started\n" 
                );




            }
            else
            {
                textPerson.setText("The Director");
                textstrng.setText("Goal-oriented\n" +
                        "Risk-taking\n" +
                        "Good under stress\n"+"Ambitious\n" +
                        "Passionate\n" +
                        "Dominant\n" +
                        "Good administrative skills\n" +
                        "Highly competitive\n" +
                        "Multitasking");

                textWeak.setText("Stubborn\n" +
                        "Workaholic\n" +
                        "Impatient\n" +
                        "Tough\n"
                        +"Insensitive\n" +
                        "Ill-tempered \n" +
                        "Intolerant");


            }

        }


        Button back = getButton();

        back.setTranslateX(10);
        back.setTranslateY(250);
        back.setPrefSize(1, 5);
        back.setTextFill(Color.YELLOW);
        back.setTextFill(Color.WHITE);
        back.setOnAction(e -> {
            try {
                AnalysisPage ap=new AnalysisPage(stage,Name,accessToken2);
                ap.analysis();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);

        Pane root=new Pane();

        root.setBackground(bg);
        root.getChildren().addAll(textstrng,textWeak,textPerson,back,ivH);


        Scene scene = new Scene(root,1400,750);
        stage.setScene(scene);
        stage.show();

        System.out.println("stage must be shown");



    }

}
