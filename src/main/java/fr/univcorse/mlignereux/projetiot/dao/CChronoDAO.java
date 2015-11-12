package fr.univcorse.mlignereux.projetiot.dao;

import fr.univcorse.mlignereux.projetiot.entity.CChrono;

/**
 * Created by asus on 11/10/2015.
 */
public class CChronoDAO extends CCrudServiceBean<CChrono> {

    public void delete(final long pId){
        this.delete(CChrono.class, pId);
    }


}
