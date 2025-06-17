package org.example.generatechecks;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class GenCheckController {
    @FXML // this annotation links this variable to the element wih the same id name as the variable in the fxml file
    private Label clock;

    @FXML
    private Label checkTimes;

    @FXML
    private Label timeLeft;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    //a formatter that mskes it so that the time is dysplaied in a certain format

    private LocalTime time;
    private AtomicReference<ArrayList<LocalTime>> checks = new AtomicReference<>(new ArrayList<>());

    //i just realized tha tbc multiple timelines are running at any given time then that means the program
    //is prolly running parallet/multi-threding

    @FXML // the fxml file calls this initialize function when the app is launched
    public void initialize() {
        //set some variables
        Time checksGenerator = new Time();
        LocalTime[] lastHour = {LocalTime.now().withMinute(0).withSecond(0)};
        LocalTime endOfShift = LocalTime.of(8, 0);
        AtomicReference<String> remainingHr = new AtomicReference<>();


        //updates the clock every second wiht the current time
        Timeline sceneClock = new Timeline( new KeyFrame(Duration.ZERO, e ->{
            //live clock
            time = LocalTime.now();
            clock.setText(time.format(formatter));

            //calculates time remaining
            remainingHr.set(String.valueOf(java.time.Duration.between(endOfShift, time).abs().toHours()));
            timeLeft.setText(remainingHr+" hrs "+(60-time.getMinute())+" mins");
        }),
            new KeyFrame(Duration.seconds(1))
        );

        //genenrate the first 4 checks
        checks.set(checksGenerator.getMinutes());
        //used ai for this, gives a clean format to the time
        checkTimes.setText(
                checks.get().stream()
                        .map(time -> time.format(DateTimeFormatter.ofPattern("HH:mm")))
                        .collect(Collectors.joining(" | "))
        );


        //start the clock animation
        sceneClock.setCycleCount(Animation.INDEFINITE);
        sceneClock.play();

        //check if an hr has passed every second
        Timeline hrPassed = new Timeline(new KeyFrame(Duration.seconds(1), e ->{
            LocalTime now = LocalTime.now();
            if(now.getMinute() == 0 && now.getSecond() == 0 &&
                    now.getHour() != lastHour[0].getHour()){
                //if an hr has passed then update the text and set the last hr to now
                checks.set(checksGenerator.getMinutes());
                //used ai for this, gives a clean format to the checks list
                checkTimes.setText(
                    checks.get().stream()
                            .map(time -> time.format(DateTimeFormatter.ofPattern("HH:mm")))
                            .collect(Collectors.joining(" | "))
                );
                lastHour[0] = LocalTime.now();
            }
        }));
        //start the hr checks
        hrPassed.setCycleCount(Animation.INDEFINITE);
        hrPassed.play();

    }

}