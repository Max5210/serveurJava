package fr.univcorse.mlignereux.projetiot.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.ws.rs.ext.ParamConverter;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 29/09/2015.
 */
@Entity
@Table(name = "ATHLETES")
@XmlRootElement(name = "Athlete")
@JsonIgnoreProperties(value = {CAthlete.FIELD_COACHS, CAthlete.FIELD_TRAININGS})
public class CAthlete extends CUser implements Serializable {

    public static final String FIELD_ID = "id";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_PASSWORD= "password";
    public static final String FIELD_STATUS= "status";
    public static final String FIELD_COACHS= "coachs";
    public static final String FIELD_PERFORMANCE= "performance";
    public static final String FIELD_TRAININGS= "trainings";

    @Id
    @XmlElement(name = FIELD_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @XmlElement(name = FIELD_EMAIL)
    @Column
    private String email;

    @XmlElement(name = FIELD_PASSWORD)
    @Column
    private String password;

    @XmlElement(name = FIELD_STATUS)
    @Enumerated(EnumType.STRING)
    @Column
    private Status status;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "athletes")
    @XmlElement(name = FIELD_TRAININGS)
    @Column
    private List<CTraining> trainings = new ArrayList<CTraining>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "athletes")
    @XmlElement(name = FIELD_COACHS)
    @Column
    private List<CCoach> coachs = new ArrayList<CCoach>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "athlete")
    @XmlElement(name = FIELD_PERFORMANCE)
    @Column
    private List<CPerformance> performances = new ArrayList<CPerformance>();

    public CAthlete(){}

    public CAthlete(String pEmail, String pPwd, Status pStatus) {
        this.status = pStatus;
        this.email = pEmail;
        this.password = pPwd;
    }

    public CAthlete(CAthlete pAthlete){
        this.status = pAthlete.getStatus();
        this.email = pAthlete.getEmail();
        this.password = pAthlete.getPassword();
        this.trainings = pAthlete.getTrainings();
        this.coachs = pAthlete.getCoachs();
    }

    public List<CPerformance> getPerformances(CTraining pTraining){
        List<CPerformance> result = new ArrayList<CPerformance>();

        return result;
    }

    public static CAthlete create(String pEmail, String pPwd){
        return new CAthlete(pEmail, pPwd, Status.ATHLETE);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<CTraining> getTrainings(){ return trainings; }

    public void setTrainings(List<CTraining> trainings) {
        this.trainings = trainings;
    }

    public List<CCoach> getCoachs() {
        return coachs;
    }

    public void setCoachs(List<CCoach> coachs) {
        this.coachs = coachs;
    }

    public List<CPerformance> getPerformances() {
        return performances;
    }

    public void setPerformances(List<CPerformance> performances) {
        this.performances = performances;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CAthlete{" +
                "status=" + status +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}
