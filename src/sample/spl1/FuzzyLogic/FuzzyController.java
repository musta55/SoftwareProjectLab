package sample.spl1.FuzzyLogic;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class FuzzyController {
  // private FuzzyFrame frame;
    private FuzzyVariable POW;
    private FuzzyVariable CON;
    private FuzzyVariable REC;
    private FuzzyVariable STAB;
    private FuzzyVariable decision;
    private double conEvaluate[];
    private double powEvaluate[];
    private double personalityTest[];

    private double recEvaluate[];
    private double stabEvaluate[];
    private Stage stage;
    private ArrayList<FuzzySet> fuzzySets;
    private ArrayList<FuzzySet> fuzzySets2;

    public FuzzyController(double[] personalityTest, Stage stage) throws FileNotFoundException {
     //   frame = new FuzzyFrame();
        POW = new FuzzyVariable();
        CON = new FuzzyVariable();
        REC = new FuzzyVariable();
        STAB = new FuzzyVariable();
        this.personalityTest=personalityTest;
        this.stage=stage;

        decision = new FuzzyVariable();
        initialiazeVariable(stage);
  //      initializeListener();
    //    frame.setVisible(true);
    }



    private void initialiazeVariable(Stage stage) throws FileNotFoundException {
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
        //  fuzzySets2.add(new FuzzySet("Good", 0.0,0.8));
        fuzzySets2.add(new FuzzySet("The Socializer", 0.0, 0.8));
        fuzzySets2.add(new FuzzySet("The Director", 0.0,0.9));

        personalityTest(stage);

    }

    public void personalityTest(Stage stage) throws FileNotFoundException {

        double pow =personalityTest[0];
        double con = personalityTest[1];

        conEvaluate = CON.evaluate(con);
        powEvaluate = POW.evaluate(pow);


        double rec = personalityTest[2];
        double stab = personalityTest[3];

        recEvaluate = REC.evaluate(rec);
        stabEvaluate = STAB.evaluate(stab);

        //gpaEvaluatePrint();
        //greEvaluatePrint();

        andOperatorRules();
        centroidDefuzz(stage);


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

    public void centroidDefuzz(Stage stage) throws FileNotFoundException {
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

        int variable=-1;

        System.out.println("Crisp decision index = " + result+"\n"+"Fuzzy decision index :\n");

        Pane root=new Pane();
        Scene scene = new Scene(root,1400,750);
        stage.setScene(scene);
        stage.show();

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
                variable=i;
                found = true;
            }

            if(result < fuzzySets2.get(i+1).index && result >= (fuzzySets2.get(i+1).index)-0.1){
                double percentage = Math.abs(((fuzzySets2.get(i+1).index-0.1) - result)/0.1);
                percentage = round(percentage, 2);
          //      frame.appendCustomText(fuzzySets2.get(i+1).name +" = "+ percentage*100 + "%\n");
                System.out.println(fuzzySets2.get(i+1).name +" = "+ percentage*100 + "%\n");
                variable=i;
                found = true;
            }

            if(found)
                return;
        }



        Image background = new Image(new FileInputStream("Pictures/Personality Page.png"));

        Text textstrng=null;
        Text textWeak=null;

        Text textPerson=null;



        if(variable==0)
        {
            textPerson.setText("The Thinker");




        }


    else    if(variable==1)
        {
            textPerson.setText("The Supporter");




        }

    else    if(variable==2)
        {
            textPerson.setText("The Socializer");




        }
      else  if(variable==0)
        {
            textPerson.setText("The Director");




        }


        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        root.setBackground(bg);
        root.getChildren().addAll(textstrng,textWeak,textPerson);



        System.out.println("stage must be shown");



    }

    public  void maxDefuzz(){
        Double max = 0.0;
        int index =0 ;
        for (int i=0 ; i < fuzzySets.size() ; i++) {
            if(Double.compare(fuzzySets.get(i).value , max) > 0){
                max = fuzzySets.get(i).value;
                index = i;
            }
        }
//        frame.appendCustomText("\n\nMethod max\n");
//        frame.appendCustomText("Crisp decision index = " + round(fuzzySets.get(index).value, 2) +"\n");
//        frame.appendCustomText("Fuzzy decision index = "+ fuzzySets.get(index).name +"\n");


        System.out.println("\n\nMethod max\n");
        System.out.println("Crisp decision index = " + round(fuzzySets.get(index).value, 2) +"\n");
        System.out.println("Fuzzy decision index = "+ fuzzySets.get(index).name +"\n");

    }

    private class centroidButtonPress implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
//            double pow = frame.getPowValue();
//            double con = frame.getConValue();


        }

    }

    private class maxButtonPress implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            double pow =personalityTest[0];
            double con = personalityTest[1];

            conEvaluate = CON.evaluate(con);
            powEvaluate = POW.evaluate(pow);


            double rec = personalityTest[2];
            double stab = personalityTest[3];

            recEvaluate = REC.evaluate(rec);
            stabEvaluate = STAB.evaluate(stab);

            andOperatorRules();
            maxDefuzz();
        }

    }
}
