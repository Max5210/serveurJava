package fr.univcorse.mlignereux.projetiot.dao;

import fr.univcorse.mlignereux.projetiot.entity.CAthlete;
import fr.univcorse.mlignereux.projetiot.entity.CCoach;
import fr.univcorse.mlignereux.projetiot.entity.CTraining;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by asus on 11/10/2015.
 */
@Stateless
@LocalBean
public class CTrainingDAO {

    @PersistenceContext
    private EntityManager em;

    public List<CTraining> getAllTrainings(){
        Query query =  em.createQuery("select c from CTraining c");
        return query.getResultList();
    }

    public CTraining getTraining(CTraining pTraining){
        Query query = em.createQuery("select c from CTraining c where c.id = :training_id");
        query.setParameter("training_id", pTraining.getId());
        return (CTraining) query.getSingleResult();
    }


    public CTraining find(int pTrainingId) {
        Query query =  em.createQuery("select a from CTraining a where a.id = :training_id");
        query.setParameter("training_id", pTrainingId);
        return (CTraining) query.getSingleResult();
    }

    public CTraining postTraining(CCoach pCoach, String pDescription, String pDate, String pHour){
        CTraining training = new CTraining();
        training.setCoach(pCoach);
        training.setDate(pDate);
        training.setDescription(pDescription);
        training.setHour(pHour);
        training.setAthletes(null);
        em.persist(training);
        return training;
    }
}
