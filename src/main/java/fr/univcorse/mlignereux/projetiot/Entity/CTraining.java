package fr.univcorse.mlignereux.projetiot.Entity;

import java.util.List;

/**
 * Created by asus on 29/09/2015.
 */
public class CTraining {
    List<CPerformance> performances;
    List<CAthlete> athletes;

    public CTraining (List<CPerformance> pPerformances){
        this.performances = pPerformances;
    }

    @Override
    public String
    toString() {
        return "CTraining{" +
                "performances=" + performances +
                '}';
    }
}
