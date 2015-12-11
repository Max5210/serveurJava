package fr.univcorse.mlignereux.projetiot.dao;

import fr.univcorse.mlignereux.projetiot.entity.CCardiacFrequency;
import fr.univcorse.mlignereux.projetiot.entity.CChrono;
import fr.univcorse.mlignereux.projetiot.entity.CPerformance;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Created by asus on 11/10/2015.
 */
@Stateless
@LocalBean
public class CChronoDAO {

    @PersistenceContext
    private EntityManager em;

    public CChrono createChrono(CPerformance pPerformance, int pHour, int pMinute, int pSeconde, int pMillisecond, int pPenality){
        CChrono chrono = new CChrono();
        chrono.setPerformance(pPerformance);
        chrono.setHour(pHour);
        chrono.setMillisecond(pMillisecond);
        chrono.setMinute(pMinute);
        chrono.setSecond(pSeconde);
        chrono.setPenality(pPenality);
        em.persist(chrono);
        return chrono;
    }

    public CChrono find(int pId) {
        Query query = em.createQuery("select p from CChrono p where p.id = :chrono_id");
        query.setParameter("chrono_id", pId);
        return (CChrono) query.getSingleResult();
    }
}
