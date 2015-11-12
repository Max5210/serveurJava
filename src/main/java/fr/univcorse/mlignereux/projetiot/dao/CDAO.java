package fr.univcorse.mlignereux.projetiot.dao;

import fr.univcorse.mlignereux.projetiot.entity.CCoach;
import fr.univcorse.mlignereux.projetiot.entity.CUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by asus on 04/10/2015.
 */
public abstract class CDAO {

    private static EntityManager entityManager;

    private static EntityManagerFactory entityManagerFactory;

    private static CAthleteDAO athleteDAO;
    private static CCoachDAO coachDAO;
    private static CUser user;

    static {
        Map<String, String> map = new HashMap<>();
        String temp;
        if ((temp = System.getProperty("javax.persistence.target-database")) != null) {
            map.put("javax.persistence.target-database", temp);
        }

        if ((temp = System.getProperty("javax.persistence.jdbc.url")) != null) {
            map.put("javax.persistence.jdbc.url", temp);
        }

        if ((temp = System.getProperty("javax.persistence.jdbc.user")) != null) {
            map.put("javax.persistence.jdbc.user", temp);
        }

        if ((temp = System.getProperty("javax.persistence.jdbc.password")) != null) {
            map.put("javax.persistence.jdbc.password", temp);
        }

        entityManagerFactory = Persistence.createEntityManagerFactory("connection", map);
        entityManager = entityManagerFactory.createEntityManager();

    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }

    public static CAthleteDAO getAthleteDAO() {
        return athleteDAO;
    }

    public static CCoachDAO getCoachDAO() {return coachDAO;}
}
