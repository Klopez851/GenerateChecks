package org.example.generatechecks;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class HelloController {
//    @FXML
//    private ImageView alarm_bell;
//    @FXML
//    private Rectangle clipRectangle;

    @FXML
    private Text welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
//    @FXML
//    public void initialize() {
//        alarm_bell.setClip(clipRectangle);
//    }
}