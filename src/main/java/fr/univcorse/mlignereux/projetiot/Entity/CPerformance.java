package fr.univcorse.mlignereux.projetiot.entity;


import javax.persistence.*;
import javax.xml.bind.annotation.*;

/**
 * Created by asus on 29/09/2015.
 */
@Entity
public class CPerformance {

    public static final String FIELD_ID = "id";
    public static final String FIELD_ATHLETE = "athlete";
    public static final String FIELD_TRAINING = "training";
    public static final String FIELD_CHRONO = "chrono";
    public static final String FIELD_CARDIACFREQUENCY = "cardiacfrequency";
    public static final String FIELD_DISTANCETRAVELED = "distancetraveled";
    public static final String FIELD_VIDEO = "video";

    @Id
    @XmlElement(name = FIELD_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @XmlElement(name = FIELD_TRAINING)
    CTraining training;

    @ManyToOne(cascade = CascadeType.ALL)
    @XmlElement(name = FIELD_ATHLETE)
    CAthlete athlete;

    @OneToOne
    @XmlElement(name = FIELD_CHRONO)
    CChrono chrono;

    @OneToOne
    @XmlElement(name = FIELD_CARDIACFREQUENCY)
    CCardiacFrequency cardiacFrequency;

    @OneToOne
    @XmlElement(name = FIELD_DISTANCETRAVELED)
    CDistanceTraveled distanceTraveled;

    @OneToOne
    @XmlElement(name = FIELD_VIDEO)
    CVideo video;

    int test;

    public CPerformance(){}

    public CTraining getTraining() {
        return training;
    }

    public CAthlete getAthlete(){
        return athlete;
    }

    public CPerformance(int test){
        this.test = test;
    }

    public CChrono getChrono() {
        return chrono;
    }

    public CCardiacFrequency getCardiacFrequency() {
        return cardiacFrequency;
    }

    public CDistanceTraveled getDistanceTraveled() {
        return distanceTraveled;
    }

    public CVideo getVideo() {
        return video;
    }

    @Override
    public String toString() {
        return "CPerformance{" +
                "chrono=" + test +
                '}';
    }
}
