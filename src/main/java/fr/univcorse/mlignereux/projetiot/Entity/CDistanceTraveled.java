package fr.univcorse.mlignereux.projetiot.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by asus on 29/09/2015.
 */
@Entity
public class CDistanceTraveled {

    public static final String FIELD_ID = "id";
    public static final String FIELD_DISTANCE = "distance";
    public static final String FIELD_PERFORMANCE= "performance";

    @Id
    @XmlElement(name = FIELD_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @XmlElement(name = FIELD_PERFORMANCE)
    private CPerformance performance;

    @XmlElement(name = FIELD_DISTANCE)
    private int distance;

    public int getId() {
        return id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public CPerformance getPerformance() {
        return performance;
    }

    public void setPerformance(CPerformance performance) {
        this.performance = performance;
    }
}
