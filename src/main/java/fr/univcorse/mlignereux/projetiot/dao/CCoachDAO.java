package fr.univcorse.mlignereux.projetiot.dao;

import fr.univcorse.mlignereux.projetiot.entity.CAthlete;
import fr.univcorse.mlignereux.projetiot.entity.CCoach;
import fr.univcorse.mlignereux.projetiot.entity.CTraining;
import fr.univcorse.mlignereux.projetiot.entity.CUser;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.*;
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

    public CCoach create(String pEmail, String pPassword){
        CCoach coach = new CCoach();
        coach.setEmail(pEmail);
        coach.setPassword(pPassword);
        coach.setStatus(CUser.Status.COACH);
        coach.setAthletes(null);
        coach.setTrainings(null);
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
        CCoach coach = null;
        try{
            TypedQuery<CCoach> query =  em.createQuery("select a from CCoach a where a.email = :coach_email",
                    cCoachClass);
            query.setParameter("coach_email", email);
            coach = query.getSingleResult();
        }catch (NoResultException e){

        }
        return coach;
    }

    public CCoach getCoach(String pEmail, String pPassword){
        CCoach coach = null;
        try{
            TypedQuery<CCoach> query =  em.createQuery("select a from CCoach a where a.email = :coach_email and a.password =:coach_password",
                    CCoach.class);
            query.setParameter("coach_email", pEmail);
            query.setParameter("coach_password", pPassword);
            coach = query.getSingleResult();
        }catch (NoResultException exception){

        }
        return coach;
    }

    public void addAthlete(CCoach pCoach, CAthlete pAthlete){
        find(CCoach.class, pCoach.getId()).getAthletes().add(pAthlete);
        em.persist(pAthlete);
    }

    public List<CAthlete> getListAthletes(CCoach pCoach){
        Query query =  em.createQuery("select c.athletes from CCoach c where c.id = :coach_id");
        query.setParameter("coach_id", pCoach.getId());
        return query.getResultList();
    }

    public List<CTraining> getListTrainings(CCoach pCoach){
        Query query =  em.createQuery("select c.trainings from CCoach c where c.id = :coach_id");
        query.setParameter("coach_id", pCoach.getId());
        return query.getResultList();
    }

}
