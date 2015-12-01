package fr.univcorse.mlignereux.projetiot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by asus on 29/09/2015.
 */
@Entity
@Table(name = "CARDIACFREQUENCIES")
@XmlRootElement(name = "CardiacFrequency")
@JsonIgnoreProperties(value = {CCardiacFrequency.FIELD_PERFORMANCE})
public class CCardiacFrequency {

    public static final String FIELD_ID = "id";
    public static final String FIELD_AVERAGE = "average";
    public static final String FIELD_MIN= "min";
    public static final String FIELD_MAX= "max";
    public static final String FIELD_PERFORMANCE= "performance";

    @Id
    @XmlElement(name = FIELD_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @XmlElement(name = FIELD_PERFORMANCE)
    private CPerformance performance;

    @XmlElement(name = FIELD_AVERAGE)
    @Column
    private int average;

    @XmlElement(name = FIELD_MIN)
    @Column
    private int min;

    @XmlElement(name = FIELD_MAX)
    @Column
    private int max;

    public CCardiacFrequency(){}

    public CCardiacFrequency(CPerformance pPerformance){
        this.performance = pPerformance;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "CCardiacFrequency{" +
                "id=" + id +
                ", average=" + average +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}
