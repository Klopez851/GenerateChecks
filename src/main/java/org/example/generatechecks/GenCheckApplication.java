package org.example.generatechecks;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GenCheckApplication extends Application {

    @Override //this annotation makes it so that the method in the parent class gets overriden by this one
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/org/example/generatechecks/genChecks.fxml")); // root needed to be added to the scene
        Scene scene = new Scene(root); //scene needed to be added to the stage with its appropriate root, there are many different kinds of roots
        Font font = Font.loadFont(
                getClass().getResource("/org/example/generatechecks/PressStart2P-vaV7.ttf").toExternalForm(), 12);
        //you need to load the special font at first bc the system does not recognize it unless you do, this font needs to be initialized in the css as well

        //STAGE SETTINGS
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Check Generator");
        stage.setResizable(false);
        stage.setX(770);
        stage.setY(290);
        stage.setScene(scene);//scene added to the stage wiht its added root in order to get windown working
        stage.show();//last step needed to show the window, w/o it you will not display the screen
    }

    public static void main(String[] args) {
        launch();
    }
}