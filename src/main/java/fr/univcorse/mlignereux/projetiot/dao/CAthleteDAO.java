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
        CAthlete athlete = null;
        TypedQuery<CAthlete> query =  em.createQuery("select a from CAthlete a where a.id = :athlete_id",
                type);
        if(query != null){
            query.setParameter("athlete_id", id);
            athlete = query.getSingleResult();
        }
        return athlete;
    }


    public CAthlete findByEmail(Class cAthleteClass, String email) {
        CAthlete athlete = null;
        try{
            TypedQuery<CAthlete> query = em.createQuery("select a from CAthlete a where a.email = :athlete_email",
                    cAthleteClass);
            query.setParameter("athlete_email", email);
            athlete =  query.getSingleResult();
        }catch(NoResultException exception){

        }


        return athlete;
    }

    public CAthlete getAthlete(String pEmail, String pPassword){
        CAthlete athlete = null;
        try{
            TypedQuery<CAthlete> query =  em.createQuery("select a from CAthlete a where a.email = :athlete_email and a.password =:athlete_password",
                    CAthlete.class);
            query.setParameter("athlete_email", pEmail);
            query.setParameter("athlete_password", pPassword);
            athlete = query.getSingleResult();
        }catch (NoResultException exception){

        }
        return athlete;
    }
}
