package sample.visualOut;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sample.spl1.Main;

public class visualOutInd {
    public double JoyCal = 0;
    public double SurpriseCal = 0;
    public double AngerCal = 0;
    public double SadnessCal = 0;
    public double FearCal = 0;
    public double anticipationCal = 0;
    public double TrustCal = 0;
    public double DisgustCal = 0;

    public void VisualOutputs(Stage stage) {

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
        Pane root = new Pane();
        back.setOnAction(e -> {
            try {
                Main goBack = new Main();
                goBack.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Joy", JoyCal),
                new PieChart.Data("Surprise", SurpriseCal),
                new PieChart.Data("Anger", AngerCal),
                new PieChart.Data("anticipation", anticipationCal),
                new PieChart.Data("Sadness", SadnessCal),
                new PieChart.Data("Disgust", DisgustCal),
                new PieChart.Data("Fear", FearCal),
                new PieChart.Data("Trust", TrustCal));

        //Creating a Pie chart
        PieChart pieChart = new PieChart(pieChartData);

        //Setting the title of the Pie chart
        pieChart.setTitle("Emotion ");


        //setting the direction to arrange the data
        pieChart.setClockwise(true);

        //Setting the length of the label line
        pieChart.setLabelLineLength(70);

        //Setting the labels of the pie chart visible
        pieChart.setLabelsVisible(true);

        //Setting the start angle of the pie chart
        pieChart.setStartAngle(180);

        pieChart.setTranslateX(300);
        pieChart.setTranslateY(200);
        pieChart.setScaleX(1.5);
        pieChart.setScaleY(1.5);

        //Creating a Group object
        Group roots = new Group(pieChart);


//        Text headning = new Text(s);
//        s=null;
//        headning.setFont(Font.font(Font.getFontNames().get(7), FontPosture.REGULAR, 12));
//        headning.setScaleX(1);
//        headning.setScaleY(1);
//        headning.setTranslateX(50);
//        headning.setTranslateY(40);
//        headning.setFill(Color.DARKCYAN);

        //Creating a scene object
        Scene scene = new Scene(root, 1400, 750);

        //Setting title to the Stage
        stage.setTitle("Pie Chart");
        root.getChildren().addAll(pieChart, back);
        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
        back.setOnAction(e -> {
            try {
                Main goBack = new Main();
                goBack.start(stage);
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
        root.setBackground(bg);


        // Scene scene = new Scene(root, 1600, 800);
        stage.setScene(scene);
        //primaryStage.setFullScreen(true);
        stage.show();


//        CategoryAxis xAxis = new CategoryAxis();
//        xAxis.setCategories(FXCollections.<String>
//                observableArrayList(Arrays.asList("Speed", "User rating", "Milage", "Safety")));
//        xAxis.setLabel("category");
//
//        NumberAxis yAxis = new NumberAxis();
//        yAxis.setLabel("score");
//
//        //Creating the Bar chart
//        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
//        barChart.setTitle("Comparison between various cars");
//
//        //Prepare XYChart.Series objects by setting data
//        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
//        series1.setName("Fiat");
//        series1.getData().add(new XYChart.Data<>("Speed", 1.0));
//        series1.getData().add(new XYChart.Data<>("User rating", 3.0));
//        series1.getData().add(new XYChart.Data<>("Milage", 5.0));
//        series1.getData().add(new XYChart.Data<>("Safety", 5.0));
//
//        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
//        series2.setName("Audi");
//        series2.getData().add(new XYChart.Data<>("Speed", 5.0));
//        series2.getData().add(new XYChart.Data<>("User rating", 6.0));
//        series2.getData().add(new XYChart.Data<>("Milage", 10.0));
//        series2.getData().add(new XYChart.Data<>("Safety", 4.0));
//
//        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
//        series3.setName("Ford");
//        series3.getData().add(new XYChart.Data<>("Speed", 4.0));
//        series3.getData().add(new XYChart.Data<>("User rating", 2.0));
//        series3.getData().add(new XYChart.Data<>("Milage", 3.0));
//        series3.getData().add(new XYChart.Data<>("Safety", 6.0));
//
//        //Setting the data to bar chart
//        barChart.getData().addAll(series1, series2, series3);
//
//        //Creating a Group object
//        Group root = new Group(barChart);
//
//        //Creating a scene object
//        Scene scene = new Scene(root, 600, 400);
//
//        //Setting title to the Stage
//        stage.setTitle("Bar Chart");
//
//        //Adding scene to the stage
//        stage.setScene(scene);
//
//        //Displaying the contents of the stage
//        stage.show();

    }


}
