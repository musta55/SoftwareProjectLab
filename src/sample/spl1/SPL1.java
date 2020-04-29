package sample.spl1;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Post;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static javafx.application.Application.launch;

public class SPL1 {

    public void spl(Stage stage)  throws IOException {

        Operations operations = new Operations();
   //     PieChartExample pieChartExample=new PieChartExample();
      //  pieChartExample.start(Stage stage);

        int k = 0;
        operations.spliter();

       // while (1 == 1) {


            String inputString="";

            System.out.println("\nWhich language do you want to input ?\n1.English\n2.Bangla\n3.Facebook\n");
            Scanner scanner = new Scanner(System.in);


            String input = scanner.nextLine();
            if (input.equals("1") || input.equals("1.")) {


            } else if (input.equals("2") || input.equals("2.")) {
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (input.equals("3")) {

            }
//            else
//            {
//                break;
//            }
//        }
    }
}


