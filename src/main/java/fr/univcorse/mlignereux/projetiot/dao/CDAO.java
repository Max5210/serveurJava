package fr.univcorse.mlignereux.projetiot.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by asus on 04/10/2015.
 */
public abstract class CDAO {

    private static EntityManager entityManager;

    private static EntityManagerFactory entityManagerFactory;

    public static EntityManager getEntityManager() {
        return entityManager;
    }
}
