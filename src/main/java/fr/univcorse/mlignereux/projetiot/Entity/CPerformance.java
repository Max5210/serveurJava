package fr.univcorse.mlignereux.projetiot.Entity;

/**
 * Created by asus on 29/09/2015.
 */
public class CPerformance {
    CTraining training;
    CAthlete athlete;
    CChrono chrono;
    CCardiacFrequency cardiacFrequency;
    CDistanceTraveled distanceTraveled;
    CVideo video;
    int test;


    public CTraining getTraining() {
        return training;
    }

    public CAthlete getAthlete(){
        return athlete;
    }

    public CPerformance(int test){
        this.test = test;
    }
    @Override
    public String toString() {
        return "CPerformance{" +
                "chrono=" + test +
                '}';
    }
}
