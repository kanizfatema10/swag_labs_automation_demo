package model;

public class UtilMethods {
    public static void waitForSeconds(double seconds){
        try {
            double millisecounds = seconds * 1000;
            Thread.sleep((long)millisecounds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
