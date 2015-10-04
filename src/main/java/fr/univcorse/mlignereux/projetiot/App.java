package fr.univcorse.mlignereux.projetiot;

import fr.univcorse.mlignereux.projetiot.entity.CAthlete;
import fr.univcorse.mlignereux.projetiot.entity.CTraining;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        CAthlete athleteA = new CAthlete("A", "pwd");
        CAthlete athleteB = new CAthlete("B", "pwd");

        List<CTraining> trainings = new ArrayList<CTraining>();

        trainings.add(new CTraining());
        trainings.add(new CTraining());

        athleteA.getTrainings().add(trainings.get(1));


        System.out.println( trainings );
    }
}
