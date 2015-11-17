package fr.univcorse.mlignereux.projetiot.dao;

import fr.univcorse.mlignereux.projetiot.entity.CAthlete;
import fr.univcorse.mlignereux.projetiot.entity.CUser;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.*;
import java.util.List;

/**
 * Created by asus on 11/10/2015.
 */

@Stateless
@LocalBean
public class CAthleteDAO {

    @PersistenceContext
    private EntityManager em;

    public List<CAthlete> getAllAthletes(){
        TypedQuery<CAthlete> query =  em.createQuery("select a from CAthlete a", CAthlete.class);
        return query.getResultList();
    }

    public CAthlete create(String pEmail, String pPassword){
        CAthlete athlete = new CAthlete();
        athlete.setEmail(pEmail);
        athlete.setPassword(pPassword);
        athlete.setStatus(CUser.Status.ATHLETE);
        em.persist(athlete);
        return athlete;
    }

    public CAthlete find(Class type, int id){
        TypedQuery<CAthlete> query =  em.createQuery("select a from CAthlete a where a.id = :athlete_id",
                type);
        query.setParameter("athlete_id", id);
        return query.getSingleResult();
    }

    public CAthlete findByEmail(Class cAthleteClass, String email) {
        TypedQuery<CAthlete> query =  em.createQuery("select a from CAthlete a where a.email = :athlete_email",
                cAthleteClass);
        query.setParameter("athlete_email", email);
        return query.getSingleResult();
    }

    public CAthlete getAthlete(String pEmail, String pPassword){
        TypedQuery<CAthlete> query =  em.createQuery("select a from CAthlete a where a.email = :athlete_email and a.password =:athlete_password",
                CAthlete.class);
        query.setParameter("athlete_email", pEmail);
        query.setParameter("athlete_password", pPassword);
        return query.getSingleResult();
    }
}
