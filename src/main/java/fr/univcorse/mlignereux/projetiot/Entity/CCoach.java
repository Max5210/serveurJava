package fr.univcorse.mlignereux.projetiot.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 29/09/2015.
 */
@Entity
@XmlRootElement(name = "Coachs")
@Table(name = "COACHS")
public class CCoach extends CUser implements Serializable {

    public static final String FIELD_ID = "id";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_PASSWORD= "password";
    public static final String FIELD_STATUS= "status";
    public static final String FIELD_ATHLETES= "athletes";
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

    @ManyToMany
    @XmlElement(name = FIELD_ATHLETES)
    private List<CAthlete> athletes = new ArrayList<>();

    @OneToMany
    @XmlElement(name = FIELD_TRAININGS)
    private List<CTraining> trainings = new ArrayList<>();

    public CCoach(){}

    public CCoach(String pEmail, String pPwd, Status pStatus) {
        this.status = pStatus;
        this.email = pEmail;
        this.password = pPwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
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
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public List<CAthlete> getAthletes() {
        return athletes;
    }

    public void setAthletes(List<CAthlete> athletes) {
        this.athletes = athletes;
    }

    public List<CTraining> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<CTraining> trainings) {
        this.trainings = trainings;
    }

    @Override
    public String toString() {
        return "CCoach{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                '}';
    }
}
