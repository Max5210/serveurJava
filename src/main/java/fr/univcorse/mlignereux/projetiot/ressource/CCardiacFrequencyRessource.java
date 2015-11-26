package fr.univcorse.mlignereux.projetiot.ressource;

import fr.univcorse.mlignereux.projetiot.dao.CAthleteDAO;
import fr.univcorse.mlignereux.projetiot.dao.CCardiacFrequencyDAO;
import fr.univcorse.mlignereux.projetiot.dao.CPerformanceDAO;
import fr.univcorse.mlignereux.projetiot.entity.CAthlete;
import fr.univcorse.mlignereux.projetiot.entity.CCardiacFrequency;
import fr.univcorse.mlignereux.projetiot.entity.CPerformance;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;

/**
 * Created by asus on 20/10/2015.
 */
@Path("/cardiacfrequency")
@Stateless
@LocalBean
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
    public void postCardiacFrequency(@FormParam(CPerformance.FIELD_ID) int pPerformanceId,
                                     @FormParam("athlete_id" )int pAthleteId,
                                     @FormParam(CCardiacFrequency.FIELD_AVERAGE) int pAverage,
                                     @FormParam(CCardiacFrequency.FIELD_MAX) int pMax,
                                     @FormParam(CCardiacFrequency.FIELD_MIN) int pMin){

        CAthlete athlete = athleteDAO.find(CAthlete.class, pAthleteId);
        CPerformance performance = performanceDAO.find(CPerformance.class, pPerformanceId, athlete);

        cardiacFrequencyDAO.postCardiacFrequency(performance, pAverage,pMin,pMax);
    }




}
