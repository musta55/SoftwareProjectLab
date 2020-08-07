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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class logIn {


        public  void login(Stage stage) throws IOException {
            stage.setTitle("Registration Form For Calculating Emotion");

            // Create the registration form grid pane
            GridPane gridPane = createLogInFormPane();
            // Add UI controls to the registration form grid pane
            UIControls(gridPane,stage);
            // Create a scene with registration form grid pane as the root node
            Scene scene = new Scene(gridPane, 1400, 750);
            Image background = new Image(logIn.class.getClassLoader().getResource("Pictures/pngfuel.com.png").toString(), true);

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

    private static GridPane createLogInFormPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    public int findFile(String name,File file)
    {
        File[] list = file.listFiles();
        if(list!=null)
            for (File fil : list)
            {
                if (fil.isDirectory())
                {
                    findFile(name,fil);
                }
                else if (name.equalsIgnoreCase(fil.getName()))
                {
                    System.out.println(fil.getParentFile());
                    return 0;
                }
            }
        return 1;
    }
    private final void UIControls(GridPane gridPane, Stage stage) {
        // Add Header
        Label headerLabel = new Label("Log In Form");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        headerLabel.setTextFill(Color.WHITE);
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Add Name Label
        Label nameLabel = new Label("Full Name : ");
        nameLabel.setTextFill(Color.WHITE);
        gridPane.add(nameLabel, 0,1);

        // Add Name Text Field
        TextField nameField = new TextField();
        nameField.setPrefHeight(40);
        gridPane.add(nameField, 1,1);


        Label passwordLabel = new Label("Password : ");
        passwordLabel.setTextFill(Color.WHITE);
        gridPane.add(passwordLabel, 0, 3);

        // Add Age Field
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, 1, 3);



        // Add Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 5, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0,20,0));




        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(nameField.getText().isEmpty()) {
                    showAlerts(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your name");
                    return;
                }

                if(passwordField.getText().isEmpty()) {
                    showAlerts(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a password");
                    return;
                }

                String name =nameField.getText()+passwordField.getText()+".txt";
                String directory = "E:\\SoftwareProjectLab";

                                      if( findFile(name,new File(directory))==0)
                                      {
                                          showAlerts(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "LogIn Successful!", "Welcome " + nameField.getText());
                                          String s=nameField.getText();
                                          System.out.println(s);


                                          FileWriter fw = null;
                                          try {
                                              fw = new FileWriter(s+".txt");
                                              fw.write(s+"\n"+passwordField.getText()+"\n");
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
                                      else {
                                          showAlerts(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "LogIn Error!", "Please enter valid password");
                                            return;
                                      }

                        }

        });
    }

    private static void showAlerts(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

}

