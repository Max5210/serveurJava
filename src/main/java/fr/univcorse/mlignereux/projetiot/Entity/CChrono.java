package fr.univcorse.mlignereux.projetiot.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by asus on 29/09/2015.
 */

@Entity
@Table (name= "CHRONOS")
@XmlRootElement(name=  "Chrono")
public class CChrono {

    public static final String FIEL_ID = "id";
    public static final String FIELD_HOUR = "hour";
    public static final String FIELD_MINUTE = "minute";
    public static final String FIELD_SECOND = "second";
    public static final String FIELD_MILLISECOND = "millisecond";
    public static final String FIELD_PENALITY = "penality";
    public static final String FIELD_PERFORMANCE = "performance";


    @Id
    @XmlElement(name = FIEL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @XmlElement(name = FIELD_HOUR)
    @Column(nullable = false)
    private int hour;

    @XmlElement(name = FIELD_MINUTE)
    @Column(nullable = false)
    private int minute;

    @XmlElement(name = FIELD_SECOND)
    @Column(nullable = false)
    private int second;

    @XmlElement(name = FIELD_MILLISECOND)
    @Column(nullable = false)
    private long millisecond;

    @XmlElement(name = FIELD_PENALITY)
    @Column(nullable = false)
    private int penality;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @XmlElement(name = FIELD_PERFORMANCE)
    private CPerformance performance;

    public CChrono(){}

    public CChrono(CPerformance pPerformance, int pHour, int pMinute, int pSecond, long pMillisecond, int pPenality){
        this.performance = pPerformance;
        this.hour = pHour;
        this.minute = pMinute;
        this.second  = pSecond;
        this.millisecond = pMillisecond;
        this.penality = pPenality;
    }

    public int getId() {
        return id;
    }

    public int getPenality() {
        return penality;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public long getMillisecond() {
        return millisecond;
    }

    public CPerformance getPerformance() {
        return performance;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public void setMillisecond(long millisecond) {
        this.millisecond = millisecond;
    }

    public void setPenality(int penality) {
        this.penality = penality;
    }

    public void setPerformance(CPerformance performance) {
        this.performance = performance;
    }
}
