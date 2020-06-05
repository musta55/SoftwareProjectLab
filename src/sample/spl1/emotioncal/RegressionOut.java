package sample.spl1.emotioncal;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.spl1.firstPost;
import sample.visualOut.LinearRegression;

public class RegressionOut {

    public RegressionOut(Stage stage, double[] a, double[] b) {

        Button back = new Button("Back");
        back.setTranslateX(1100);
        back.setTranslateY(650);
        back.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #8d9092 0%, #717375 100%),\n" +
                "        #8d9092,\n" +
                "        #717375,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #ffffff, #a1a3a6);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 1.1em;");
        back.setPrefSize(60, 30);

        Image background = new Image(getClass().getClassLoader().getResource("emotionSide.png").toString(), true);
        back.setOnAction(e -> {
            try {

                firstPost fp=new firstPost();
                fp.firstpost(stage);
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
        sc.setPrefSize(1000, 700);
        Pane root = new Pane();
        root.getChildren().addAll(sc,back);
        Scene scene = new Scene(root, 2000, 900);

        //Setting title to the Stage
        stage.setTitle("Linear Regression");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();





    }
}
