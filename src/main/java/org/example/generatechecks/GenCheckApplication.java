package org.example.generatechecks;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GenCheckApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/generatechecks/genChecks.fxml"));
        Scene scene = new Scene(root, 400,400 );

        //STAGE SETTINGS
        stage.initStyle(StageStyle.UNDECORATED); // MAKE SURE TO ADD EXIT BUTTON
        stage.setTitle("Check Generator");
        stage.setResizable(false);
        stage.setX(877);
        stage.setY(350);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}