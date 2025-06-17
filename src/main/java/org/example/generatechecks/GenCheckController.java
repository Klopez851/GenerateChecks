package org.example.generatechecks;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class GenCheckController {
    @FXML // this annotation links this variable to the element wih the same id name as the variable in the fxml file
    private Label clock;

    @FXML
    private Label checkTimes;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    //a formatter that mskes it so that the time is dysplaied in a certain format

    private LocalTime time;

    @FXML // the fxml file calls this initialize function when the app is launched
    public void initialize() {
        //updates the clock every second wiht the current time
        Timeline sceneClock = new Timeline( new KeyFrame(Duration.ZERO, e ->{
            time = LocalTime.now();
            clock.setText(time.format(formatter));
        }),
            new KeyFrame(Duration.seconds(1))
        );
        sceneClock.setCycleCount(Animation.INDEFINITE);
        sceneClock.play();

    }

    public void getChecks(){
        Time checks = new Time();

    }
}