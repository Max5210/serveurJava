package fr.univcorse.mlignereux.projetiot.ressource;

import fr.univcorse.mlignereux.projetiot.dao.CAthleteDAO;
import fr.univcorse.mlignereux.projetiot.dao.CCoachDAO;
import fr.univcorse.mlignereux.projetiot.dao.CTrainingDAO;
import fr.univcorse.mlignereux.projetiot.entity.CAthlete;
import fr.univcorse.mlignereux.projetiot.entity.CCoach;
import fr.univcorse.mlignereux.projetiot.entity.CTraining;
import fr.univcorse.mlignereux.projetiot.entity.CUser;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.persistence.criteria.CriteriaBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 20/10/2015.
 */

@Path("/coachs")
@Stateless
@LocalBean
@Consumes("*/*")
public class CCoachRessource {

    public static final String BAD_REQUEST = "Not enough data is provided in order to create the coach";
    public static final String CONFLICT = "An account has already this id";
    public static final String EMAIL_ALREADY_USED = "An coach in database has already this email";
    public static final String BAD_PATTERN_LOGIN = "Login with a wrong pattern";
    public static final String CREATED = "A new coach has been created at the location you specified";
    public static final String DEFAULT_RESPONSE = "This should not be happened";
    public static final String NOT_FOUND = "The coach with the given id does not exist in the database";
    public static final String UPDATED = "The coach you specified has been fully updated at the location you specified";
    public static final String DELETED = "The coach has been successfully removed from database";
    public static final String NOT_MODIFIED = "The coach with the given id is not modified";
    public static final String BAD_JSON = "Error Syntax JSON.";

    @Inject
    @EJB
    private CCoachDAO coachDao;

    @Inject
    @EJB
    private CAthleteDAO athleteDAO;

    @Inject
    @EJB
    private CTrainingDAO trainingDAO;

    @POST
    @Path("/add")
    @Produces("text/plain")
    public Response postCoach(@FormParam(CCoach.FIELD_EMAIL) String pEmail,
                                @FormParam(CCoach.FIELD_PASSWORD) String pPwd){
        if(pEmail == null || pPwd == null){
            return Response.status(Response.Status.BAD_REQUEST)//400 Bad request if not enough data is provided
                    .entity(BAD_REQUEST)
                    .build();
        }

        if(coachDao.findByEmail(CCoach.class, pEmail) != null){
            return Response.status(Response.Status.FOUND)
                    .entity(EMAIL_ALREADY_USED)
                    .build();
        }

        CCoach coach = coachDao.create(pEmail,pPwd);
        if (coach != null) {
            return Response.status(Response.Status.CREATED)// 201 created
                    .header("Location",
                            "/coachs/"
                                    + String.valueOf(coach.getId())).
                            type(MediaType.APPLICATION_JSON_TYPE).build();
        }
        return Response.status(500).entity(DEFAULT_RESPONSE).build();
    }



    @Path("/all")
    @GET
    @Produces("application/json")
    public List<CCoach> getAllCoachs(){
        List<CCoach> coaches = new ArrayList<CCoach>();
        for(CCoach coach : coachDao.getAllCoachs()){
            coaches.add(coach);
        }
        return coaches;
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getCoachById(@PathParam("id") final int id){
        JsonArrayBuilder builder = Json.createArrayBuilder();
        CCoach coach =  coachDao.find(CCoach.class, id);
        if(coach != null){
            return Response.status(Response.Status.OK)
                    .header("Location",
                            "/coachs/"
                                    + String.valueOf(coach.getId())).
                            type(MediaType.APPLICATION_JSON_TYPE)
                    .entity(coach).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(NOT_FOUND).build();
    }

    @GET
    @Path("/email/{email}")
    @Produces("application/json")
    public JsonArray getCoachByEmail(@PathParam("email") final String pEmail){
        JsonArrayBuilder builder = Json.createArrayBuilder();
        CCoach coach = coachDao.findByEmail(CCoach.class, pEmail);
        if(coach != null){
            builder.add(Json.createObjectBuilder()
                    .add("id", coach.getId())
                    .add("email", coach.getEmail())
                    .add("password", coach.getPassword()));
        }
        return builder.build();
    }

    @GET
    @Path("/connect")
    @Produces("application/json")
    public Response connectCoach(@QueryParam(CCoach.FIELD_EMAIL) String pEmail,
                                   @QueryParam(CCoach.FIELD_PASSWORD) String pPwd,
                                   @QueryParam(CCoach.FIELD_STATUS) String pStatus){

        System.out.println(pEmail);
        System.out.println(pPwd);
        System.out.println(pStatus);

        if(pEmail == null || pPwd == null || pStatus == null){
            return Response.status(Response.Status.BAD_REQUEST)//400 Bad request if not enough data is provided
                    .entity(BAD_REQUEST)
                    .build();
        }

        if(coachDao.findByEmail(CCoach.class, pEmail) == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(NOT_FOUND)
                    .build();
        }

        CCoach coach = coachDao.getCoach(pEmail,pPwd);

        System.out.println("Coach get:" + coach);

        if(coach != null){
            return Response.status(Response.Status.OK)
                    .header("Location",
                            "/coachs/"
                                    + String.valueOf(coach.getId())).
                            type(MediaType.APPLICATION_JSON_TYPE)
                    .entity(coach).build();
        }
        return Response.status(401).entity(Response.Status.UNAUTHORIZED).build();
    }

    @PUT
    @Path("/{id}/addAthlete/{email}")
    @Produces("application/json")
    public Response addAthlete(@PathParam("id")int pId, @PathParam("email")String pEmail){

        CCoach coach = coachDao.find(CCoach.class, pId);

        CAthlete athlete = athleteDAO.findByEmail(CAthlete.class, pEmail);
        if(athlete == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(NOT_FOUND)
                    .build();
        }
        coachDao.addAthlete(coach, athlete);

        return Response.status(201).entity(Response.Status.OK).build();
    }

    @GET
    @Path("/{id}/allAthletes")
    @Produces("application/json")
    public JsonArray getListAthletes(@PathParam("id") int pId){
        JsonArrayBuilder builder = Json.createArrayBuilder();
        CCoach coach = coachDao.find(CCoach.class, pId);
        if(coach != null){
            for (int i = 0; i < coach.getAthletes().size(); i++) {
                builder.add(Json.createObjectBuilder()
                        .add("id", coach.getAthletes().get(i).getId())
                        .add("email", coach.getAthletes().get(i).getEmail()));
            }
        }
        return builder.build();
    }

}
