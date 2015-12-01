package fr.univcorse.mlignereux.projetiot.dao;

import fr.univcorse.mlignereux.projetiot.entity.CChrono;
import fr.univcorse.mlignereux.projetiot.entity.CPerformance;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
