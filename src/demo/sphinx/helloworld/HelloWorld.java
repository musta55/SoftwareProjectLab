///*
// * Copyright 1999-2004 Carnegie Mellon University.
// * Portions Copyright 2004 Sun Microsystems, Inc.
// * Portions Copyright 2004 Mitsubishi Electric Research Laboratories.
// * All Rights Reserved.  Use is subject to license terms.
// *
// * See the file "license.terms" for information on usage and
// * redistribution of this file, and for a DISCLAIMER OF ALL
// * WARRANTIES.
// *
// */
//
//package demo.sphinx.helloworld;
//
//import edu.cmu.sphinx.frontend.util.Microphone;
//import edu.cmu.sphinx.recognizer.Recognizer;
//import edu.cmu.sphinx.result.Result;result
//import edu.cmu.sphinx.util.props.ConfigurationManager;
//import edu.cmu.sphinx.util.props.PropertyException;
//import javafx.scene.Scene;
//import javafx.scene.canvas.Canvas;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextArea;
//import javafx.scene.image.Image;
//import javafx.scene.layout.*;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontPosture;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//import sample.spl1.Operations;
//import sample.spl1.emotioncal.EmotionCalculation;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.net.URL;
//
//
///**
// * A simple HelloWorld demo showing a simple speech application
// * built using Sphinx-4. This application uses the Sphinx-4 endpointer,
// * which automatically segments incoming audio into utterances and silences.
// */
//public class HelloWorld {
//
//    /**
//     * Main method for running the HelloWorld demo.
//     */
//    public static void speech(Stage primaryStage) {
//        String temp=null;
//        try {
//
//
//            URL url;
////            if (args.length > 0) {
////                url = new File(args[0]).toURI().toURL();
////            } else {
//            url = HelloWorld.class.getResource("helloworld.config.xml");
////            }
//
//            System.out.println("Loading...");
//
//            ConfigurationManager cm = new ConfigurationManager(url);
//
//            Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
//            Microphone microphone = (Microphone) cm.lookup("microphone");
//
//
//            /* allocate the resource necessary for the recognizer */
//            recognizer.allocate();
//            Button button = new Button("Get Result");
//            /* the microphone will keep recording until the program exits */
//            if (microphone.startRecording()) {
//
//                System.out.println
//                        (" ( how are you | say hello);\n" +
//                                "( change to voice one  | change to voice two | change to voice three );\n" +
//                                " ( say amazing | what day is today | I am happy );\n" +
//                                " (who is your daddy | obey to me beach | hey boss);\n" +
//                                "( zero | one | two | three | four | five | six | seven | nine | ten\n" +
//                                "                   | eleven | twelve | thirteen | fourteen | fifteen | sixteen | seventeen | eighteen | nineteen | twenty \n" +
//                                "                   | thirty | forty | fifty | sixty | seventy | eighty | ninety |\n" +
//                                "\t\t            hundred | thousand | million | billion)+;                   \n" +
//                                "public <syntax> = <number>{1} (plus | minus | multiply | division){1} <number>{1}; \n");
//
//            //    while (true) {
//                    System.out.println
//                            ("Start speaking. Press Ctrl-C to quit.\n");
//
//                    /*
//                     * This method will return when the end of speech
//                     * is reached. Note that the endpointer will determine
//                     * the end of speech.
//                     */
//                    Result result = recognizer.recognize();
//
//
//                    Operations operations = new Operations();
//
//
//
//                    if (result != null) {
//                        String resultText = result.getBestFinalResultNoFiller();
//                        System.out.println("You said: " + resultText + "\n");
//
//                        temp=resultText;
//
//
//                        button.setTextFill(Color.WHITE);
//                        setStyle(button);
//                        button.setTranslateX(620);
//                        button.setTranslateY(300);
//                        button.setPrefSize(300,70);
//                        button.setOnAction(action -> {
//
//
//
//
//
//
//                            try {
//                                operations.splitInput(resultText);
//                            } catch (FileNotFoundException exc) {
//                                exc.printStackTrace();
//                            }
//                            try {
//                                operations.removeWord();
//                            } catch (FileNotFoundException exc) {
//                                exc.printStackTrace();
//                            }
//                            operations.search();
//                            try {
//                                EmotionCalculation emCal = new EmotionCalculation();
//                                // launch(args);
//                                emCal.searchEmotion();
//                                // launch(args);
//                                emCal.emotionCalc(primaryStage);
//                                emCal.VisualOutput(primaryStage,resultText);
//
//                            } catch (Exception ex) {
//                                ex.printStackTrace();
//
//                            }
//
//
//
//
//                        });
//
//
//
//
//                    } else {
//                        System.out.println("I can't hear what you said.\n");
//                    }
//
//
//             //   }
//            } else {
//                System.out.println("Cannot start microphone.");
//                recognizer.deallocate();
//                System.exit(1);
//            }
//
//
//            try {
//                Button backs = new Button("Back");
//                backs.setTranslateX(50);
//                backs.setTextFill(Color.WHITE);
//                backs.setTranslateY(20);
//                setStyle(backs);
//                backs.setPrefSize(200, 30);
//
//
//                primaryStage.setTitle("User Input");
//
//
//
//
//                try {
//                    Text headning = null;
//
//
//                    headning = new Text("You said :" + temp);
//                    headning.setScaleX(6);
//                    headning.setScaleY(6);
//                    headning.setTranslateX(650);
//                    headning.setTranslateY(100);
//                    headning.setFill(Color.DARKBLUE);
//                    headning.setFont(Font.font(Font.getFontNames().get(12), FontPosture.REGULAR, 7));
//
//
//                 //   Image backgrounds = new Image(getClass().getClassLoader().getResource("emotion(16-9).png").toString(), true);
//
//                    Image backgrounds =new Image("emotion(16-9).png");
//                    Canvas canvas = new Canvas(1400, 750);
//                    GraphicsContext gc = canvas.getGraphicsContext2D();
//                    Pane roots = new Pane();
//                    BackgroundImage bi = new BackgroundImage(backgrounds,
//                            BackgroundRepeat.NO_REPEAT,
//                            BackgroundRepeat.NO_REPEAT,
//                            BackgroundPosition.DEFAULT,
//                            BackgroundSize.DEFAULT);
//                    Background bg = new Background(bi);
//                    roots.setBackground(bg);
//                    roots.getChildren().addAll(canvas,headning,backs,button);
//                    Scene scene = new Scene(roots, 1400, 750);
//                    primaryStage.setScene(scene);
//                    //primaryStage.setFullScreen(true);
//                    primaryStage.show();
//                } catch (Exception ex) {
//                    System.out.println("Picture url Problem");
//                }
//            } catch (Exception excep) {
//                excep.printStackTrace();
//            }
//
//
//        } catch (IOException e) {
//            System.err.println("Problem when loading HelloWorld: " + e);
//            e.printStackTrace();
//        } catch (PropertyException e) {
//            System.err.println("Problem configuring HelloWorld: " + e);
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            System.err.println("Problem creating HelloWorld: " + e);
//            e.printStackTrace();
//        }
//    }
//
//
//    public static Button setStyle(Button b) {
//        b.setStyle("-fx-padding: 8 15 15 15;\n" +
//                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
//                "    -fx-background-radius: 8;\n" +
//                "    -fx-background-color: \n" +
//                "        linear-gradient(from 0% 93% to 0% 100%, #0B2058 0%, #030B21 100%),\n" +
//                "        #030B21,\n" +
//                "        #0B2058,\n" +
//                "        radial-gradient(center 50% 50%, radius 100%, #143389, #09236B);\n" +
//                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
//                "    -fx-font-weight: bold;\n" +
//                "    -fx-font-size: 2.1em;");
//        return b;
//    }
//
//}