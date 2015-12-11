package fr.univcorse.mlignereux.projetiot.ressource;

import fr.univcorse.mlignereux.projetiot.dao.CAthleteDAO;
import fr.univcorse.mlignereux.projetiot.dao.CCardiacFrequencyDAO;
import fr.univcorse.mlignereux.projetiot.dao.CPerformanceDAO;
import fr.univcorse.mlignereux.projetiot.entity.CAthlete;
import fr.univcorse.mlignereux.projetiot.entity.CCardiacFrequency;
import fr.univcorse.mlignereux.projetiot.entity.CCoach;
import fr.univcorse.mlignereux.projetiot.entity.CPerformance;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 20/10/2015.
 */
@Path("/cardiacfrequency")
@Consumes("*/*")
public class CCardiacFrequencyRessource {

    @Inject
    @EJB
    private CCardiacFrequencyDAO cardiacFrequencyDAO;

    @Inject
    @EJB
    private CPerformanceDAO performanceDAO;

    @Inject
    @EJB
    private CAthleteDAO athleteDAO;

    @POST
    @Path("/add")
    @Produces("application/json")
    public void postCardiacFrequency(@FormParam(CCardiacFrequency.FIELD_PERFORMANCE) int pPerformanceId,
                                     @FormParam(CCardiacFrequency.FIELD_AVERAGE) int pAverage,
                                     @FormParam(CCardiacFrequency.FIELD_MAX) int pMax,
                                     @FormParam(CCardiacFrequency.FIELD_MIN) int pMin){

        CPerformance performance = performanceDAO.find(pPerformanceId);
        if(performance != null)
            cardiacFrequencyDAO.postCardiacFrequency(performance, pAverage,pMin,pMax);

    }

    @Path("/all")
    @GET
    @Produces("application/json")
    public List<CCardiacFrequency> getAllCardiacFrequency(){
        List<CCardiacFrequency> cardiacFrequencies= new ArrayList<CCardiacFrequency>();
        for(CCardiacFrequency cardiacFrequency : cardiacFrequencyDAO.getAllCardiacFrequency()){
            cardiacFrequencies.add(cardiacFrequency);
        }
        return cardiacFrequencies;
    }




}
