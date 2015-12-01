package fr.univcorse.mlignereux.projetiot.ressource;

import fr.univcorse.mlignereux.projetiot.dao.*;
import fr.univcorse.mlignereux.projetiot.entity.*;
import fr.univcorse.mlignereux.projetiot.entity.CDistanceTraveled;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.swing.*;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by asus on 20/10/2015.
 */
@Path("/performances")
@Stateless
@LocalBean
@Consumes("*/*")
public class CPerformanceRessource {


    @Inject
    @EJB
    private CPerformanceDAO performanceDAO;

    @Inject
    @EJB
    private CAthleteDAO athleteDAO;

    @Inject
    @EJB
    private CTrainingDAO trainingDAO;

    @Inject
    @EJB
    private CCardiacFrequencyDAO cardiacFrequencyDAO;

    @Inject
    @EJB
    private CChronoDAO chronoDAO;


    @POST
    @Path("/add")
    @Produces("application/json")
    public void postPerformance(@FormParam("training_id") int pTrainingId,
                                @FormParam("athlete_id") int pAthleteId){

        CAthlete athlete = athleteDAO.find(CAthlete.class, pAthleteId);
        CTraining training = trainingDAO.find(CTraining.class, pTrainingId);

        performanceDAO.createPerformance(athlete,training);

    }

    public void postPerformance(@FormParam(CTraining.FIELD_ID) int pTrainingId,
                                @FormParam(CAthlete.FIELD_ID) int pAthleteId,
                                @FormParam(CChrono.FIEL_ID) int pChronoId,
                                @FormParam(CCardiacFrequency.FIELD_ID) int pCardiacFrequencyId,
                                @FormParam(CVideo.FIELD_ID) int pVideoId,
                                @FormParam(CDistanceTraveled.FIELD_ID) int pDistanceTraveledId){

    }

    @PUT
    @Path("{id}/addCardiacFrequency/{id_cf}")
    @Produces("application/json")
    public void addCardiacFrequency(@PathParam("id") int pPerformanceId,
                                @PathParam("id_cf") int pCardiacFrequency){

        CCardiacFrequency cardiacFrequency = cardiacFrequencyDAO.find(CCardiacFrequency.class, pCardiacFrequency);
        CPerformance performance = performanceDAO.find(CPerformance.class, pPerformanceId);

        performanceDAO.addCardiacFrequency(performance,cardiacFrequency);

    }

    //TODO finir implémenter addChrono
    @PUT
    @Path("{id}/addCardiacFrequency/{id_chrono}")
    @Produces("application/json")
    public void addChrono(@PathParam("id") int pPerformanceId,
                                    @PathParam("id_chrono") int pChrono){

        CCardiacFrequency cardiacFrequency = cardiacFrequencyDAO.find(CCardiacFrequency.class, pChrono);
        CPerformance performance = performanceDAO.find(CPerformance.class, pPerformanceId);

        performanceDAO.addCardiacFrequency(performance,cardiacFrequency);

    }

}
