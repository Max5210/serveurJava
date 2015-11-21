package fr.univcorse.mlignereux.projetiot.entity;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by asus on 29/09/2015.
 */
@Entity
@XmlRootElement(name = "Training")
@Table(name = "TRAININGS")
public class CTraining implements Serializable{

    public static final String FIELD_ID = "id";
    public static final String FIELD_DATE = "date";
    public static final String FIELD_ATHLETES = "athletes";
    public static final String FIELD_COACH = "coach";

    @Id
    @XmlElement(name = FIELD_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @XmlElement(name = FIELD_ATHLETES)
    private List<CAthlete> athletes;

    @ManyToOne
    @XmlElement(name = FIELD_COACH)
    private CCoach coach;

    @XmlElement(name = FIELD_DATE)
    @Column
    @Temporal(TemporalType.DATE)
    private Date date;

    public CTraining(){}

    public int getId() {
        return id;
    }

    public List<CAthlete> getAthletes() {
        return athletes;
    }


    public void addPerformance(CPerformance performance) {
        //this.performances.add(performance);
    }

    public void addAthlete(CAthlete pAthlete)
    {
        this.getAthletes().add(pAthlete);
        pAthlete.getTrainings().add(this);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAthletes(List<CAthlete> athletes) {
        this.athletes = athletes;
    }

    public CCoach getCoach() {
        return coach;
    }

    public void setCoach(CCoach coach) {
        this.coach = coach;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
