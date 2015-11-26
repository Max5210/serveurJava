package fr.univcorse.mlignereux.projetiot.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by asus on 29/09/2015.
 */
@Entity
@Table(name = "CARDIACFREQUENCIES")
@XmlRootElement(name = "CardiacFrequency")
public class CCardiacFrequency {

    public static final String FIELD_ID = "id";
    public static final String FIELD_AVERAGE = "average";
    public static final String FIELD_MIN= "min";
    public static final String FIELD_MAX= "max";
    public static final String FIELD_PERFORMANCE= "performance";

    @Id
    @XmlElement(name = FIELD_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @XmlElement(name = FIELD_PERFORMANCE)
    private CPerformance performance;

    @XmlElement(name = FIELD_AVERAGE)
    private int average;

    @XmlElement(name = FIELD_MIN)
    private int min;

    @XmlElement(name = FIELD_MAX)
    private int max;

    public CCardiacFrequency(){}

    public CCardiacFrequency(CPerformance pPerformance){
        this.performance = pPerformance;
    }


    public int getId() {
        return id;
    }

    public int getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public CPerformance getPerformance() {
        return performance;
    }

    public void setPerformance(CPerformance performance) {
        this.performance = performance;
    }
}
