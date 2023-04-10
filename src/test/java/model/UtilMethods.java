package model;

public class UtilMethods {

    private static Boolean isInTestingPhaseOver = true;

    public static void waitForSeconds(double seconds){
                
        if(isInTestingPhaseOver){
            return;
        }

        try {
            double millisecounds = seconds * 1000;
            Thread.sleep((long)millisecounds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
