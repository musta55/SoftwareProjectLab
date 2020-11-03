package sample.spl1.visualOut;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Post;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class viewPost {

    public void view(int number,String accessToken) throws FileNotFoundException {

        Stage st=new Stage();


        Pane root = new Pane();

        Image background = new Image(new FileInputStream("Pictures/rectangle.png"));

        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        root.setBackground(bg);

        Scene scene = new Scene(root,1400,750);
        st.setScene(scene);
        st.show();

        FacebookClient fbClient = new DefaultFacebookClient(accessToken);
        Connection<Post> result;
        result = fbClient.fetchConnection("me/feed", Post.class);

        int counter=0;
        String str=" ";
        for (List<Post> apost : result) {
            for (Post aPost : apost) {
                counter++;
                if (counter == number) {
                  str  += aPost.getMessage();
                }
            }
        }

        TextArea textArea = new TextArea();
        textArea.setLayoutX(150);
        textArea.setLayoutY(180);
        textArea.setPrefRowCount(5);
        textArea.setPrefColumnCount(6);
        textArea.setWrapText(true);
        textArea.setMinSize(1125, 80);
        textArea.setText(str);
        root.getChildren().add(textArea);

    }
}