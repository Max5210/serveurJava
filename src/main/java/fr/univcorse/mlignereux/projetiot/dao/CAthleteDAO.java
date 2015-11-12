package fr.univcorse.mlignereux.projetiot.dao;

import fr.univcorse.mlignereux.projetiot.entity.CAthlete;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by asus on 11/10/2015.
 */

@Stateless
public class CAthleteDAO extends CCrudServiceBean<CAthlete> {

    public List<CAthlete> getAllAthletes(){
        TypedQuery<CAthlete> query =  em.createQuery("select a from CAthlete a", CAthlete.class);
        return query.getResultList();
    }
}
