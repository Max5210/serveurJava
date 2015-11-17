package fr.univcorse.mlignereux.projetiot.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by asus on 29/09/2015.
 */
@Entity
public class CVideo {

    public static final String FIELD_ID = "id";
    public static final String FIELD_PERFORMANCE = "performance";
    public static final String FIELD_DATA = "data";

    @Id
    @XmlElement(name = FIELD_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @XmlElement(name = FIELD_PERFORMANCE)
    private CPerformance performance;
}
