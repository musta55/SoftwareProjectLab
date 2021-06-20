package sample.spl1.login;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;
import sample.spl1.thirdPage;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationFrormApplication  {


    public void registration(Stage stage) throws Exception {
       // Stage primaryStage=new Stage();
        stage.setTitle("Registration Form For Calculating Emotion");

        // Create the registration form grid pane
        GridPane gridPane = createRegistrationFormPane();
        // Add UI controls to the registration form grid pane
        addUIControls(gridPane,stage);
        // Create a scene with registration form grid pane as the root node
        Scene scene = new Scene(gridPane, 1400, 750);

        Image background = new Image(new FileInputStream("src/Pictures/newbg.png"));
        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);

        gridPane.setBackground(bg);
        // Set the scene in primary stage
        stage.setScene(scene);

        stage.show();

    }


    private GridPane createRegistrationFormPane() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.TOP_CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(60, 60, 60, 60));

        // Set the horizontal gap between columns
        gridPane.setHgap(20);

        // Set the vertical gap between rows
        gridPane.setVgap(20);

        // Add Column Constraints

        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(200, 200, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(300,300, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    private void addUIControls(GridPane gridPane,Stage stage) {
        // Add Header
        Label headerLabel = new Label("Registration Form");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        headerLabel.setTextFill(Color.WHITE);
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Add Name Label
        Label nameLabel = new Label("Full Name : ");
        nameLabel.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 20));

        nameLabel.setTextFill(Color.BLACK);
        gridPane.add(nameLabel, 0,2);

        // Add Name Text Field
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        nameField.setPrefHeight(40);
        nameField.setPrefWidth(80);
        gridPane.add(nameField, 1,2);


        // Add Email Label
        Label emailLabel = new Label("Email ID : ");
        emailLabel.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 20));

        emailLabel.setTextFill(Color.BLACK);
        gridPane.add(emailLabel, 0, 4);

        // Add Email Text Field
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setPrefHeight(40);
        emailField.setPrefWidth(80);
        gridPane.add(emailField, 1, 4);

        // Add Password Label
        Label passwordLabel = new Label("Password : ");

        passwordLabel.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 20));
        passwordLabel.setTextFill(Color.BLACK);
        gridPane.add(passwordLabel, 0, 6);

        // Add Age Field
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setPrefHeight(40);
        passwordField.setPrefWidth(80);
        gridPane.add(passwordField, 1, 6);


        // Add Password Label
        Label ageLabel = new Label("Age : ");
        ageLabel.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 20));

        ageLabel.setTextFill(Color.BLACK);
        gridPane.add(ageLabel, 0, 8);

        // Add Password Field
        TextField ageField = new TextField();
        ageField.setPromptText("Age");
        ageField.setPrefHeight(40);
        ageField.setPrefWidth(80);
        gridPane.add(ageField, 1, 8);


        // Add Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 10, 4, 2);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0,20,0));




        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(nameField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your name");
                    return;
                }

                if(regexChecker("[\\w._%-]+@[\\w._%-]+\\.[A-Za-z]{2,4}",emailField.getText())!=1) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your valid email id");
                    return;
                }
                if(regexChecker("^\\d*[1-9]\\d*$",ageField.getText())!=1) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your valid age (Integer)");
                    return;
                }

                if(emailField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your email id");
                    return;
                }
                if(passwordField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a password");
                    return;
                }

                if(ageField.getText().isEmpty()) {
                   
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your age");
                    return;
                }


else
                {
                    showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Registration Successful!", "Welcome " + nameField.getText());
                  //  stage.close();
                }



                String s=nameField.getText();
                System.out.println(s);


                FileWriter fw = null;
                try {
                    fw = new FileWriter(s+passwordField.getText()+".txt");
                    fw.write(s+"\n"+emailField.getText()+"\n"+passwordField.getText()+"\n"+ageField.getText()+"\n");
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }



                try {
                    thirdPage tp=new thirdPage();
                    tp.app(stage,s+passwordField.getText()+".txt");
                } catch (Exception excep) {
                    excep.printStackTrace();
                }



            }
        });
    }

    public static int regexChecker(String theRegex ,String str2Check) {
        Pattern checkRegex=Pattern.compile(theRegex);
        Matcher regexMatcher =checkRegex.matcher(str2Check);
        while(regexMatcher.find())
        {

            if(regexMatcher.group().length()!=0)
            {
                System.out.println(regexMatcher.group().trim());
            }

            System.out.println("Start index :"+regexMatcher.start());
            System.out.println("End index :"+regexMatcher.end());
            return 1;
        }
        return 0;
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}