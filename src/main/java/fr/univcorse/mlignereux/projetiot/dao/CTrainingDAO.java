package fr.univcorse.mlignereux.projetiot.dao;

import fr.univcorse.mlignereux.projetiot.entity.CTraining;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by asus on 11/10/2015.
 */
@Stateless
@LocalBean
public class CTrainingDAO {

    @PersistenceContext
    private EntityManager em;


}
