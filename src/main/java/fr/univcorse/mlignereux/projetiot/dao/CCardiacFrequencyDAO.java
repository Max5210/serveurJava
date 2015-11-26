package fr.univcorse.mlignereux.projetiot.dao;

import fr.univcorse.mlignereux.projetiot.entity.CCardiacFrequency;
import fr.univcorse.mlignereux.projetiot.entity.CPerformance;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by asus on 11/10/2015.
 */
@Stateless
@LocalBean
public class CCardiacFrequencyDAO  {

    @PersistenceContext
    private EntityManager em;

    public void postCardiacFrequency(CPerformance pPerformance, int pAverage, int pMin, int pMax){
        CCardiacFrequency cardiacFrequency = new CCardiacFrequency(pPerformance);
        cardiacFrequency.setAverage(pAverage);
        cardiacFrequency.setMax(pMax);
        cardiacFrequency.setMin(pMin);
        em.persist(cardiacFrequency);
    }

}
