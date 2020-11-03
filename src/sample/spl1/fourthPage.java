package sample.spl1;


import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.spl1.languageClassification.Language;
import sample.spl1.login.RegistrationFrormApplication;
import sample.spl1.visualOut.Progression;
import sample.spl1.visualOut.overAll;

import java.io.FileNotFoundException;
import java.io.IOException;

public class fourthPage {

    public void runs(Stage stages,String accessToken,String Name)
    {

        // String accessToken = "EAADoSRqMjgEBAAmg9aWgK5AvAjkFvZBvpn10r3kLzcbPmHkGlBWer1bsoBix7ZBceTCfZBIz4CTEjPVImS23NM0ZCMmeOxTz4ooS2wrt2ergNcdvTX6k83DnR8TCQfHnE70mVcfkAB0ssoCXT86qjfEWFCiWuX6obQhqvyCa6QZDZD";
        //  String accessToken="EAAMF6lCN2rABANG7fFJVwktoiJjKiZCDP7k1v4uZB48GRH1J2GCU1HAJMPc6389TS6EwTdp9ilfqZBZABNGobGhy3bH0zhYh5x2LQ55SicVEOtVmLO8poNvPVpZB3F3aSperHycu8VZAEQY4jfNxVsWrG7ZBZCP1L6bnYTvqrXb5EEIumHvpZBAFT79hR0r67ZCGoZD";
        FacebookClient fbClient = new DefaultFacebookClient(accessToken);
        Button proa = new Button("Total Emotion");
        proa.setTranslateX(70);
        proa.setTranslateY(150);
        setStyle(proa);
        proa.setPrefSize(300, 80);
        proa.setTextFill(Color.rgb(237, 134, 18));

        Button progression = new Button("Emotional Progress");
        progression.setTranslateX(70);
        progression.setTranslateY(550);
        setStyle(progression);
        progression.setPrefSize(300, 80);
        progression.setTextFill(Color.WHITE);


        Button reaction = new Button("Emotion & Reaction");
        reaction.setTranslateX(70);
        reaction.setTranslateY(350);
        setStyle(reaction);
        reaction.setPrefSize(300, 80);
        reaction.setTextFill(Color.WHITE);




        Button pro = new Button("Experiment");
        pro.setTranslateX(610);
        pro.setTranslateY(60);
        setStyle(pro);
        pro.setPrefSize(200, 70);
        pro.setTextFill(Color.WHITE);


        Button others = new Button("Others");
        others.setTranslateX(1180);
        others.setTranslateY(60);
        setStyle(others);
        others.setPrefSize(200, 70);
        others.setTextFill(Color.WHITE);

        Button application = new Button("Application");
        application.setTranslateX(895);
        application.setTranslateY(60);
        setStyle(application);
        application.setPrefSize(200, 70);
        application.setTextFill(Color.WHITE);



        Button back = new Button("");
        back.setGraphic(new ImageView("Pictures/backArrow - Copy.png"));
        back.setTranslateX(0);
        back.setTranslateY(340);
        back.setPrefSize(1, 5);
        back.setTextFill(Color.YELLOW);

        application.setOnAction(e -> {
            try {
                RegistrationFrormApplication reg=new RegistrationFrormApplication();
                reg.registration(stages);

            } catch (Exception excep) {
                excep.printStackTrace();
            }
        });


        pro.setOnAction(e -> {
            try {
                Language PMenu = new Language();
                PMenu.TheThird(stages);
            } catch (Exception excep) {
                excep.printStackTrace();
            }
        });

        Pane roots = new Pane();



        back.setOnAction(esb->{
            try {
                thirdPage goBack = new thirdPage();
                String filefb=Name.substring(2);

                goBack.app(stages,filefb);
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });

        progression.setOnAction(e->
                {
                    Progression statusProg =new Progression();

                    statusProg.statusProgress(stages,accessToken,Name);

                }
        );

        proa.setOnAction(e->
                {
                    overAll  over =new overAll();
                    try {
                        over.overall(stages,accessToken,Name);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
        );

        reaction.setOnAction(e->
                {
                    firstPost fp=new firstPost(accessToken);
                    try {
                        fp.firstpost(stages,Name);
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
        );



        Scene scene = new Scene(roots, 1400, 700);
        stages.setScene(scene);
        stages.setFullScreen(true);
        stages.show();
        Image background = new Image(getClass().getClassLoader().getResource("Pictures/1x/emotion(16-9)-0-3.jpg").toString(), true);

        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        roots.setBackground(bg);

        roots.getChildren().addAll(proa,progression,reaction,back,pro,application,others);

    }


    public Button setStyle ( Button b)
    {
        b.setStyle("-fx-background-color: \n" +
                "        linear-gradient(\t#FFFFFF, \t#FFFFFF),\n" +
                "        linear-gradient(#FFFFFF, #FFFFFF),\n" +
                "        linear-gradient(\t#FFFFFF, #efaa22),\n" +
                "        linear-gradient(#ffe657 0%, #3CF53C 50%, #1ED71E 100%),\n" +
                "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));\n" +
                "    -fx-background-radius: 30;\n" +
                "    -fx-background-insets: 0,1,2,3,0;\n" +
                "    -fx-text-fill: #654b00;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 2.1em;\n" +
                "    -fx-padding: 10 20 10 20;");
        return b;
    }

}