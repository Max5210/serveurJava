package fr.univcorse.mlignereux.projetiot;

import fr.univcorse.mlignereux.projetiot.Entity.CAthlete;
import fr.univcorse.mlignereux.projetiot.Entity.CPerformance;
import fr.univcorse.mlignereux.projetiot.Entity.CTraining;

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

        List<CPerformance> performances = new ArrayList<CPerformance>();
        performances.add(new CPerformance(2));
        performances.add(new CPerformance(5));
        List<CPerformance> performances2 = new ArrayList<CPerformance>();
        performances2.add(new CPerformance(3));
        performances2.add(new CPerformance(4));
        athleteA.getTrainings().add(new CTraining(performances));
        athleteA.getTrainings().add(new CTraining(performances2));

        System.out.println( athleteA );
    }
}
