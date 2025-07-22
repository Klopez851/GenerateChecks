package org.example.generatechecks;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import javafx.scene.control.ProgressBar;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class GenCheckController {
    @FXML // this annotation links this variable to the element wih the same id name as the variable in the fxml file
    private Label clock;

    @FXML
    private Label checkTimes;

    @FXML
    private Label timeLeft;

    @FXML
    private Label checksLeft;

    @FXML
    private HBox alert;

    @FXML
    private ProgressBar progressBar;

    //a FORMATTER that mskes it so that the time is dysplaied in a certain format
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("hh:mm:ss");
    private final double TOTALSHIFTMINUTES = 600.0;
    private LocalTime time;
    private AtomicReference<ArrayList<LocalTime>> checks = new AtomicReference<>(new ArrayList<>());
    private Time checksGenerator;
    int numOfChecks;

    @FXML // the fxml file calls this initialize function when the app is launched
    public void initialize() {
        numOfChecks = Integer.parseInt(checksLeft.getText());
        alert.setVisible(false);
        alert.setManaged(false);

        //set some variables
        checksGenerator = new Time();
        LocalTime[] lastHour = {LocalTime.now().withMinute(0).withSecond(0)};
        LocalTime endOfShift = LocalTime.of(8, 0);
        AtomicReference<String> remainingHr = new AtomicReference<>();


        //updates the clock every second with the current time
        Timeline sceneClock = new Timeline( new KeyFrame(Duration.ZERO, e ->{
            //live clock
            time = LocalTime.now();
            clock.setText(time.format(FORMATTER).formatted("h"));

            //calculate how much time is left of the shift
            LocalDate today = LocalDate.now();
            LocalDateTime now = LocalDateTime.of(today, time);
            LocalDateTime shiftEnd = LocalDateTime.of(today, endOfShift);

            if(shiftEnd.isBefore(now)){
                shiftEnd = shiftEnd.plusDays(1);
            }

            //calculates time remaining
            remainingHr.set(String.valueOf(java.time.Duration.between(now, shiftEnd).abs().toHours()));
            timeLeft.setText(remainingHr+" hrs "+(59-time.getMinute())+" mins");

            //set progress bar
            int minutesPassed = (Integer.parseInt(String.valueOf(remainingHr)) * 60) + (60-time.getMinute());
            double progress = 1.0-(minutesPassed/TOTALSHIFTMINUTES);
            progressBar.setProgress(progress);

        }),
            new KeyFrame(Duration.seconds(1))
        );

        //genenrate the first 4 checks
        checks.set(checksGenerator.getMinutes());

        //used ai for this, gives a clean format to the time
        checkTimes.setText(
                checks.get().stream()
                        .map(time -> time.format(DateTimeFormatter.ofPattern("hh:mm")))
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
                            .map(time -> time.format(DateTimeFormatter.ofPattern("hh:mm")))
                            .collect(Collectors.joining(" | "))
                );
                lastHour[0] = LocalTime.now();
            }

        }));
        //start the hr checks
        hrPassed.setCycleCount(Animation.INDEFINITE);
        hrPassed.play();

        //check if its time for a check
        final AtomicBoolean[] hasAlerted = {new AtomicBoolean(false)};
        Timeline timeForCheck = new Timeline(new KeyFrame(Duration.seconds(1), e ->{
            LocalTime now = LocalTime.now();
            for(LocalTime t: checksGenerator.getMinArr()){
                if(now.getMinute() == t.getMinute() && now.getHour() == t.getHour() && now.getSecond() == t.getSecond()) {
                    alert.setVisible(true);
                    alert.setManaged(true);
                }
            }
        }));
        timeForCheck.setCycleCount(Animation.INDEFINITE);
        timeForCheck.play();
    }

    @FXML
    public void alertButtonAction(javafx.event.ActionEvent event){
        alert.setVisible(false);
        alert.setManaged(false);
        numOfChecks--;
        checksLeft.setText(String.valueOf(numOfChecks));

    }

}

