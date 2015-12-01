package fr.univcorse.mlignereux.projetiot.ressource;

import fr.univcorse.mlignereux.projetiot.dao.CAthleteDAO;
import fr.univcorse.mlignereux.projetiot.dao.CCoachDAO;
import fr.univcorse.mlignereux.projetiot.dao.CTrainingDAO;
import fr.univcorse.mlignereux.projetiot.entity.CAthlete;
import fr.univcorse.mlignereux.projetiot.entity.CCoach;
import fr.univcorse.mlignereux.projetiot.entity.CTraining;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by asus on 20/10/2015.
 */

@Path("/trainings")
@Stateless
@LocalBean
@Consumes("*/*")
public class CTrainingRessource {

    public static final String BAD_REQUEST = "Not enough data is provided in order to create the athlete";
    public static final String CONFLICT = "An account has already this id";
    public static final String LOGIN_ALREADY_USED = "An athlete in database has already this login";
    public static final String BAD_PATTERN_LOGIN = "Login with a wrong pattern";
    public static final String BAD_STATUS = "This athlete has a wrong status";
    public static final String CREATED = "A new athlete has been created at the location you specified";
    public static final String DEFAULT_RESPONSE = "This should not be happened";
    public static final String NOT_FOUND = "The athlete with the given id does not exist in the database";
    public static final String UPDATED = "The athlete you specified has been fully updated at the location you specified";
    public static final String DELETED = "The athlete has been successfully removed from database";
    public static final String NOT_MODIFIED = "The athlete with the given id is not modified";
    public static final String BAD_JSON = "Error Syntax JSON.";

    @Inject
    @EJB
    private CTrainingDAO trainingDAO;

    @Inject
    @EJB
    private CCoachDAO coachDAO;

    @Inject
    @EJB
    private CAthleteDAO athleteDAO;


    @GET
    @Path("/all")
    @Produces("application/json")
    public List<CTraining> getAllTraining(){
       return trainingDAO.getAllTrainings();
    }

    @POST
    @Path("/add")
    @Produces("application/json")
    public Response addTraining(@FormParam(CTraining.FIELD_COACH) int pCoach,
                                @FormParam(CTraining.FIELD_DATE) String pDate,
                                @FormParam(CTraining.FIELD_HOUR) String pHour,
                                @FormParam(CTraining.FIELD_DESCRIPTION) String pDescription){

        CCoach coach = coachDAO.find(CCoach.class, pCoach);

        CTraining training = trainingDAO.postTraining(coach,pDescription,pDate,pHour);

        if(training != null) {
            return Response.status(Response.Status.CREATED)// 201 created
                    .header("Location",
                            "/trainings/"
                                    + String.valueOf(training.getId())).
                            type(MediaType.APPLICATION_JSON_TYPE).build();
        }
        return Response.status(500).entity(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("{id}/addAthletes/{id_athlete}")
    @Produces("application/json")
    public void addAthletes(@PathParam("id") int pId, @PathParam("id_athlete")int pAthleteId){
        CTraining training = trainingDAO.find(CTraining.class,pId);
        CAthlete athlete = athleteDAO.find(CAthlete.class, pAthleteId);


        List<CAthlete> athleteList = training.getAthletes();

        athleteList.add(athlete);

    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getTrainingById(@PathParam("id") final int id){
        JsonArrayBuilder builder = Json.createArrayBuilder();
        CTraining training =  trainingDAO.find(CTraining.class, id);
        if(training != null){
            return Response.status(Response.Status.OK)
                    .header("Location",
                            "/trainings/"
                                    + String.valueOf(training.getId())).
                            type(MediaType.APPLICATION_JSON_TYPE)
                    .entity(training).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(NOT_FOUND).build();
    }


}
