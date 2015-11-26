package fr.univcorse.mlignereux.projetiot.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

/**
 * Created by asus on 29/09/2015.
 */
@Entity
@Table(name="PERFORMANCES")
@XmlRootElement(name = "Performance")
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


    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @XmlElement(name = FIELD_TRAINING)
    private CTraining training;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @XmlElement(name = FIELD_ATHLETE)
    private CAthlete athlete;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @XmlElement(name = FIELD_CHRONO)
    private CChrono chrono;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @XmlElement(name = FIELD_CARDIACFREQUENCY)
    private CCardiacFrequency cardiacFrequency;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @XmlElement(name = FIELD_DISTANCETRAVELED)
    private CDistanceTraveled distanceTraveled;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @XmlElement(name = FIELD_VIDEO)
    private CVideo video;

    int test;

    public CPerformance(){}

    public CPerformance(CAthlete pAthlete, CTraining pTraining){
        this.athlete = pAthlete;
        this.training = pTraining;
    }

    public int getId() {
        return id;
    }

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

    public void setTraining(CTraining training) {
        this.training = training;
    }

    public void setAthlete(CAthlete athlete) {
        this.athlete = athlete;
    }

    public void setChrono(CChrono chrono) {
        this.chrono = chrono;
    }

    public void setCardiacFrequency(CCardiacFrequency cardiacFrequency) {
        this.cardiacFrequency = cardiacFrequency;
    }

    public void setDistanceTraveled(CDistanceTraveled distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
    }

    public void setVideo(CVideo video) {
        this.video = video;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CPerformance{" +
                "chrono=" + test +
                '}';
    }
}
