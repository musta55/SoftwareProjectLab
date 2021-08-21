package sample.spl1.emotioncal;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sample.spl1.firstPost;
import sample.spl1.visualOut.LinearRegression;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static sample.spl1.visualOut.visualoutputfb.getButton;

//To print the regression line
public class RegressionOut {

    public void Regressionout(Stage sge,String accessToken,String Name, double[] a, double[] b) throws FileNotFoundException {

        Button back =getButton();

          Pane stat = new Pane();
        Stage stage =new Stage();


        back.setOnAction(e -> {
            try {

                firstPost fp=new firstPost(accessToken);
                fp.firstpost(stage,Name);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });



        LinearRegression lr=   new LinearRegression(a,b);
        System.out.println("Linear Regression is :"+ lr.toString());


        final LineChart<Number, Number> sc = new LineChart<>(new NumberAxis(), new NumberAxis());

        XYChart.Series seriess1 = new XYChart.Series();
        seriess1.setName("Regression Line");
        for (int t = 0; t <= 7; t++) {

            double q=(lr.slope()*a[t] + (lr.intercept()));

            seriess1.getData().add(new XYChart.Data(a[t],q));

        }

        XYChart.Series seriess2 = new XYChart.Series();
        seriess2.setName("Emotion And Reaction");
        for(int t=0;t<8;t++)
            seriess2.getData().add(new XYChart.Data<>(a[t], b[t]));

        sc.setAnimated(false);
        sc.setCreateSymbols(true);

        sc.getData().addAll(seriess2,seriess1);
        sc.setLayoutX(100);
        sc.setLayoutY(100);
        sc.setPrefSize(800, 500);
        Pane root = new Pane();

        Image background = new Image(new FileInputStream("src/Pictures/newbg.png"));
        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        root.setBackground(bg);


        root.getChildren().addAll(sc,back);
      //  Scene scen = new Scene(root, 2000, 900);

        //Setting title to the Stage
        stage.setTitle("Linear Regression");
        Scene scen = new Scene(root, 950, 637);

        //Adding scene to the stage
        stage.setScene(scen);

        //Displaying the contents of the stage
        stage.show();

    }
}
