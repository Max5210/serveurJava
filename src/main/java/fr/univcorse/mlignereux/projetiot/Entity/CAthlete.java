package fr.univcorse.mlignereux.projetiot.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 29/09/2015.
 */
public class CAthlete extends CUser {
    List<CPerformance> performances;
    List<CTraining> trainings;

    public CAthlete(String pId, String pPwd) {
        super(pId, pPwd);
        trainings = new ArrayList<CTraining>();
        performances = new ArrayList<CPerformance>();
    }

    public List<CTraining> getTrainings(){
        List<CTraining> result = new ArrayList<CTraining>();
        for( CPerformance performance : performances)
            result.add(performance.getTraining());
        return result;
    }

    @Override
    public String toString() {
        return "CAthlete{" +
                "trainings=" + trainings +
                '}';
    }
}
