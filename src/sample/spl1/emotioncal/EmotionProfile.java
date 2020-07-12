package sample.spl1.emotioncal;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class EmotionProfile {

   private String name;
   public EmotionProfile(String name)
   {
       this.name=name;
   }

    public  void profileScore() throws FileNotFoundException {
        DecimalFormat df = new DecimalFormat("0.00");
        Scanner scan;
        File file = null;
        file = new File(name);
        scan = new Scanner(file);
        double[] out = new double[10000];

        int i = 0;
        int m = 0;
        while (scan.hasNextLine()) {
            //
            String currentLine = scan.nextLine();
            // System.out.println("Current Line "+currentLine);
            String[] firstSplits = currentLine.split(" ", 0);
            for (; m < firstSplits.length; m++) {
                //     System.out.println("String is " + firstSplits[m]);
                try {
                    if (firstSplits[m] == "NaN" || firstSplits[m] == "Infinity") ;
                    else {
                        out[m] = Double.parseDouble(firstSplits[m]);
                        System.out.print("i = "+m+ " "+out[m] + "          ");
                    }
                } catch (Exception q) {
                    out[m] = 0.000;
                }
                //     System.out.println("Value is " + out[m]);
            }
            // double d = Double.parseDouble();
        }


    }

}
