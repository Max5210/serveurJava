package fr.univcorse.mlignereux.projetiot.dao;

import fr.univcorse.mlignereux.projetiot.entity.CAthlete;
import fr.univcorse.mlignereux.projetiot.entity.CCoach;
import fr.univcorse.mlignereux.projetiot.entity.CUser;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by asus on 11/10/2015.
 */
@Stateless
@LocalBean
public class CCoachDAO  {

    @PersistenceContext
    private EntityManager em;

    public List<CCoach> getAllCoachs(){
        TypedQuery<CCoach> query =  em.createQuery("select c from CCoach c", CCoach.class);
        return query.getResultList();
    }

    public CCoach findByEmail(String pEmail) {
        try {
            return em.createNamedQuery("getByEmail", CCoach.class).setParameter("email", pEmail).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public CCoach create(String pEmail, String pPassword){
        CCoach coach = new CCoach();
        coach.setEmail(pEmail);
        coach.setPassword(pPassword);
        coach.setStatus(CUser.Status.COACH);
        em.persist(coach);
        return coach;
    }

    public CCoach find(Class type, int id){
        TypedQuery<CCoach> query =  em.createQuery("select a from CCoach a where a.id = :coach_id",
                type);
        query.setParameter("coach_id", id);
        return query.getSingleResult();
    }

    public CCoach findByEmail(Class cCoachClass, String email) {
        TypedQuery<CCoach> query =  em.createQuery("select a from CCoach a where a.email = :coach_email",
                cCoachClass);
        query.setParameter("coach_email", email);
        return query.getSingleResult();
    }

}
