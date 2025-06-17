package org.example.generatechecks;
//imports
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

//the purpose of this class is to generate a random set of times to do 4 checks during my nightshifts at RFK
public class Time {
    ///////////////
    ///VARIABLES///
    ////////////////
    private static final int MIN_OF_CHECK = 10;
    private static final int MAX_OF_CHECK = 20;
    private LocalTime currentTime;
    private ArrayList<LocalTime> minArr = new ArrayList<>(4);
    private int[] tempArr = new int[4];

    /////////////////
    ///CONSTRUCTOR///
    /////////////////
    public Time(){
        currentTime = LocalTime.now();
    }

    /////////////
    ///GETTERS///
    /////////////

    // generates and returns the minutes at which the checks should be done
    public ArrayList<LocalTime> getMinutes(){
        minArr.clear();
        genChecks();
        return minArr;
    }

    ///////////////////
    ///OTHER METHODS///
    ///////////////////

    // takes the current time and randomely generates numbers between 10 and 20 if there is more than 40 mins left of
    // the hr, and random checks w/o any bounds if there is less than 40
    private void genChecks(){
        updateCurrentTime();
        int timeLeft = 60-currentTime.getMinute();
        boolean timeGen = true;
        Random rand = new Random();
        int randMin;

        //runs if there is more than 40 mins left of the hr
        if(timeLeft <=60 && timeLeft >=40){
            while (timeGen) {
                for (int i = 0; i < 4; i++) {
                    //assures the numbers generates are greater than 9 but less than 21
                    do{
                        randMin = rand.nextInt(MAX_OF_CHECK) + 1;
                    }while(!(randMin>=MIN_OF_CHECK && randMin<=MAX_OF_CHECK));
                    tempArr[i] = randMin;
                }
                // checks if 'Checks' sum up to take up the remaining time of the hour
                if (Arrays.stream(tempArr).sum() == timeLeft) {
                    timeGen = false;
                }
            }

        }else if (timeLeft >3){
            while (timeGen) {
                //generates random checking times w/ no bounds
                for (int i = 0; i < 4; i++) {
                    randMin = rand.nextInt(timeLeft) + 1;
                    tempArr[i] = randMin;
                }
                //assures that the checks take up the remaining time of the shift
                if (Arrays.stream(tempArr).sum() == timeLeft) {
                    timeGen = false;
                }
            }

        }else{
            System.out.println("Too Little Time Left to  generate checks");
        }
        for(int i = 0;i<4;i++){
            int min = 0;
            //if index is xero there is no index to call back to
            if(i== 0){
                minArr.add(LocalTime.of(currentTime.getHour(),currentTime.getMinute()));
                minArr.set(i,minArr.get(i).plusMinutes(tempArr[i]));
            }
            else{
                minArr.add(minArr.get(i-1).plusMinutes(tempArr[i]));
            }
        }
    }

    //this method updates the currentTime variable stores in a Time instance
    private void updateCurrentTime(){
        currentTime = LocalTime.now();
    }

    //public boolean checkTime(//localtime)
    //if localtime in array, return true, else return false
}