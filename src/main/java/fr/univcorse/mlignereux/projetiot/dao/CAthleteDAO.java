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
        Query query =  em.createQuery("select a from CAthlete a");
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

    public CAthlete find(int id){
        CAthlete athlete = null;
        Query query =  em.createQuery("select a from CAthlete a where a.id = :athlete_id");
        if(query != null){
            query.setParameter("athlete_id", id);
            athlete = (CAthlete) query.getSingleResult();
        }
        return athlete;
    }


    public CAthlete findByEmail(String email) {
        CAthlete athlete = null;
        try{
            Query query = em.createQuery("select a from CAthlete a where a.email = :athlete_email");
            query.setParameter("athlete_email", email);
            athlete = (CAthlete) query.getSingleResult();
        }catch(NoResultException exception){

        }


        return athlete;
    }

    public CAthlete getAthlete(String pEmail, String pPassword){
        CAthlete athlete = null;
        try{
            Query query =  em.createQuery("select a from CAthlete a where a.email = :athlete_email and a.password =:athlete_password");
            query.setParameter("athlete_email", pEmail);
            query.setParameter("athlete_password", pPassword);
            athlete = (CAthlete) query.getSingleResult();
        }catch (NoResultException exception){

        }
        return athlete;
    }
}
