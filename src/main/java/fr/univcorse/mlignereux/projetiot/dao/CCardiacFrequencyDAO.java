package fr.univcorse.mlignereux.projetiot.dao;

import fr.univcorse.mlignereux.projetiot.entity.CCardiacFrequency;
import fr.univcorse.mlignereux.projetiot.entity.CCoach;
import fr.univcorse.mlignereux.projetiot.entity.CPerformance;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by asus on 11/10/2015.
 */
@Stateless
@LocalBean
public class CCardiacFrequencyDAO  {

    @PersistenceContext
    private EntityManager em;

    public CCardiacFrequency postCardiacFrequency(CPerformance pPerformance, int pAverage, int pMin, int pMax){
        CCardiacFrequency cardiacFrequency = new CCardiacFrequency(pPerformance);
        cardiacFrequency.setPerformance(pPerformance);
        cardiacFrequency.setAverage(pAverage);
        cardiacFrequency.setMax(pMax);
        cardiacFrequency.setMin(pMin);
        em.persist(cardiacFrequency);
        return cardiacFrequency;
    }

    public List<CCardiacFrequency> getAllCardiacFrequency(){
        TypedQuery<CCardiacFrequency> query =  em.createQuery("select c from CCardiacFrequency c", CCardiacFrequency.class);
        return query.getResultList();
    }

    public CCardiacFrequency find(Class<CCardiacFrequency> cardiacFrequencyClass, int pId) {
        TypedQuery<CCardiacFrequency> query = em.createQuery("select p from CCardiacFrequency p where p.id = :cardiacfrequency_id", cardiacFrequencyClass);
        //TypedQuery<CPerformance> query = em.createQuery("select p from CPerformance p where p.id = :performance_id and p.athlete = :athlete", cPerformanceClass);
        query.setParameter("cardiacfrequency_id", pId);
        //query.setParameter("athlete", athlete);
        return query.getSingleResult();
    }



}
