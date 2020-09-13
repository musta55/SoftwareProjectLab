package sample.spl1.FuzzyLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
    private ArrayList<FuzzySet> fuzzySets;
    private ArrayList<FuzzySet> fuzzySets2;

    public FuzzyController(double[] personalityTest) {
     //   frame = new FuzzyFrame();
        POW = new FuzzyVariable();
        CON = new FuzzyVariable();
        REC = new FuzzyVariable();
        STAB = new FuzzyVariable();
        this.personalityTest=personalityTest;

        decision = new FuzzyVariable();
        initialiazeVariable();
  //      initializeListener();
    //    frame.setVisible(true);
    }



    private void initialiazeVariable(){
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

        personalityTest();

    }

    public void personalityTest()
    {

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
        centroidDefuzz();


    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

//    public void initializeListener(){
//        frame.addCentroidButtonListener(new centroidButtonPress());
//        frame.addMaxButtonListener(new maxButtonPress());
//    }
//
//    public void powEvaluatePrint(){
//        frame.appendCustomText("Emotion Power Low = "+round(powEvaluate[0], 2)+"\n");
//        frame.appendCustomText("Emotion Power Med = "+round(powEvaluate[1], 2)+"\n");
//        frame.appendCustomText("Emotion Power High = "+round(powEvaluate[2], 2)+"\n");
//    }
//
//    public void conEvaluatePrint(){
//        frame.appendCustomText("Consistency Low = "+round(conEvaluate[0], 2)+"\n");
//        frame.appendCustomText("Consistency Med = "+round(conEvaluate[1], 2)+"\n");
//        frame.appendCustomText("Consistency High = "+round(conEvaluate[2], 2)+"\n");
//    }
//
//
//
//    public void RECEvaluatePrint(){
//        frame.appendCustomText("Recent Emotion Low = "+round(recEvaluate[0], 2)+"\n");
//        frame.appendCustomText("Recent Emotion Med = "+round(recEvaluate[1], 2)+"\n");
//        frame.appendCustomText("Recent Emotion High = "+round(recEvaluate[2], 2)+"\n");
//    }
//
//    public void STABEvaluatePrint(){
//        frame.appendCustomText("Emotional Stability Low = "+round(stabEvaluate[0], 2)+"\n");
//        frame.appendCustomText("Emotional Stability Med = "+round(stabEvaluate[1], 2)+"\n");
//        frame.appendCustomText("Emotional Stability High = "+round(stabEvaluate[2], 2)+"\n");
//    }

    public void andOperatorRules(){
        fuzzySets.clear();
        if(powEvaluate[2] > 0){

            if(powEvaluate[2]>0){
                fuzzySets.add(new FuzzySet("The Director", Double.min(powEvaluate[2], powEvaluate[2]),1.0));
            }
            if(conEvaluate[1] > 0){
                fuzzySets.add(new FuzzySet("The socializer", Double.min(conEvaluate[1], powEvaluate[2]), 0.9));

            }
            if(conEvaluate[0] > 0){
                fuzzySets.add(new FuzzySet("The supporter", Double.min(conEvaluate[0], powEvaluate[2]) ,0.7));
            }

        }
        if(powEvaluate[1] > 0){

            if(conEvaluate[2]>0){
                fuzzySets.add(new FuzzySet("The Supporter", Double.min(conEvaluate[2], powEvaluate[1]) ,0.8));
            }
            if(conEvaluate[1] > 0){
                fuzzySets.add(new FuzzySet("The Supporter", Double.min(conEvaluate[1], powEvaluate[1]), 0.8));
            }
            if(conEvaluate[0] > 0){
                fuzzySets.add(new FuzzySet("The Thinker", Double.min(conEvaluate[0], powEvaluate[1]) ,0.6));
            }

        }
        if(powEvaluate[0] > 0){

            if(conEvaluate[2]>0){
                fuzzySets.add(new FuzzySet("The Socializer", Double.min(conEvaluate[2], powEvaluate[0]) ,0.7));
            }
            if(conEvaluate[1] > 0){
                fuzzySets.add(new FuzzySet("The Thinker", Double.min(conEvaluate[1], powEvaluate[0]),0.6));
            }
            if(conEvaluate[0] > 0){
                fuzzySets.add(new FuzzySet("The Thinker", Double.min(conEvaluate[0], powEvaluate[0]), 0.6));
            }

        }




        if(recEvaluate[2] > 0){

            if(recEvaluate[2]>0){
                fuzzySets.add(new FuzzySet("The  Director", Double.min(recEvaluate[2], recEvaluate[2]),1.0));
            }
            if(stabEvaluate[1] > 0){
                fuzzySets.add(new FuzzySet("The Socializer", Double.min(stabEvaluate[1], recEvaluate[2]), 0.9));
            }
            if(stabEvaluate[0] > 0){
                fuzzySets.add(new FuzzySet("The Thinker", Double.min(stabEvaluate[0], recEvaluate[2]) ,0.7));
            }

        }
        if(recEvaluate[1] > 0){

            if(stabEvaluate[2]>0){
                fuzzySets.add(new FuzzySet("The Socializer", Double.min(stabEvaluate[2], powEvaluate[1]) ,0.8));
            }
            if(stabEvaluate[1] > 0){
                fuzzySets.add(new FuzzySet("The Socializer", Double.min(stabEvaluate[1], powEvaluate[1]), 0.8));
            }
            if(stabEvaluate[0] > 0){
                fuzzySets.add(new FuzzySet("The Thinker", Double.min(stabEvaluate[0], powEvaluate[1]) ,0.6));
            }

        }
        if(recEvaluate[0] > 0){

            if(stabEvaluate[2]>0){
                fuzzySets.add(new FuzzySet("The Supporter", Double.min(stabEvaluate[2], recEvaluate[0]) ,0.7));
            }
            if(stabEvaluate[1] > 0){
                fuzzySets.add(new FuzzySet("The Thinker", Double.min(stabEvaluate[1], recEvaluate[0]),0.6));
            }
            if(stabEvaluate[0] > 0){
                fuzzySets.add(new FuzzySet("The Thinker", Double.min(stabEvaluate[0], recEvaluate[0]), 0.6));
            }

        }






    }

    public void centroidDefuzz(){
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


        System.out.println("Crisp decision index = " + result+"\n"+"Fuzzy decision index :\n");
        for (int i=0 ; i < fuzzySets2.size() ; i++) {

            boolean found = false;

            if(Double.compare(result,fuzzySets2.get(i).index) == 0){
       //         frame.appendCustomText(fuzzySets2.get(i).name +" = "+ "100%" + "\n");
                System.out.println(fuzzySets2.get(i).name +" = "+ "100%" + "\n");
                found = true;
            }

            if(result > fuzzySets2.get(i).index && result <= fuzzySets2.get(i).index+0.1){
                double percentage = Math.abs((result - (fuzzySets2.get(i).index+0.1))/0.1);
                percentage = round(percentage, 2);
     //           frame.appendCustomText(fuzzySets2.get(i).name +" = "+ percentage*100 + "%\n");
                System.out.println(fuzzySets2.get(i).name +" = "+ percentage*100 + "%\n");
                found = true;
            }

            if(result < fuzzySets2.get(i+1).index && result >= (fuzzySets2.get(i+1).index)-0.1){
                double percentage = Math.abs(((fuzzySets2.get(i+1).index-0.1) - result)/0.1);
                percentage = round(percentage, 2);
          //      frame.appendCustomText(fuzzySets2.get(i+1).name +" = "+ percentage*100 + "%\n");
                System.out.println(fuzzySets2.get(i+1).name +" = "+ percentage*100 + "%\n");
                found = true;
            }

            if(found)
                return;
        }


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
