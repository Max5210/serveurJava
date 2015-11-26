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


    @POST
    @Path("/add")
    @Produces("application/json")
    public void postPerformance(@FormParam("training_id") int pTrainingId,
                                @FormParam("athlete_id") int pAthleteId){

        CAthlete athlete = athleteDAO.find(CAthlete.class, pAthleteId);
        CTraining training = trainingDAO.find(CTraining.class, pTrainingId);

        performanceDAO.createPerformance(athlete,training);

    }

    public void postPerformance(@QueryParam(CTraining.FIELD_ID) int pTrainingId,
                                @QueryParam(CAthlete.FIELD_ID) int pAthleteId,
                                @QueryParam(CChrono.FIEL_ID) int pChronoId,
                                @QueryParam(CCardiacFrequency.FIELD_ID) int pCardiacFrequencyId,
                                @QueryParam(CVideo.FIELD_ID) int pVideoId,
                                @QueryParam(CDistanceTraveled.FIELD_ID) int pDistanceTraveledId){

    }



}
