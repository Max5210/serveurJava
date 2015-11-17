package fr.univcorse.mlignereux.projetiot.ressource;

import fr.univcorse.mlignereux.projetiot.dao.CCoachDAO;
import fr.univcorse.mlignereux.projetiot.entity.CAthlete;
import fr.univcorse.mlignereux.projetiot.entity.CCoach;
import fr.univcorse.mlignereux.projetiot.entity.CUser;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by asus on 20/10/2015.
 */

@Path("/coach")
public class CCoachRessource {

    public static final String BAD_REQUEST = "Not enough data is provided in order to create the coach";
    public static final String CONFLICT = "An account has already this id";
    public static final String LOGIN_ALREADY_USED = "An coach in database has already this login";
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

    @POST
    @Path("/add")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/plain")
    public Response postCoach(@FormParam(CCoach.FIELD_EMAIL) String pEmail,
                                @FormParam(CCoach.FIELD_PASSWORD) String pPwd){
        if(pEmail == null || pPwd == null){
            return Response.status(Response.Status.BAD_REQUEST)//400 Bad request if not enough data is provided
                    .entity(BAD_REQUEST)
                    .build();
        }

        CCoach coach = coachDao.create(pEmail,pPwd);
        if (coach != null) {
            return Response.status(Response.Status.CREATED)// 201 created
                    .header("Location",
                            "/athletes/"
                                    + String.valueOf(coach.getId())).
                            type(MediaType.APPLICATION_JSON_TYPE).build();
        }
        return Response.status(500).entity(DEFAULT_RESPONSE).build();
    }



    @Path("/all")
    @GET
    @Produces("application/json")
    public JsonArray getAllAthletes(){
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for(CCoach coach : coachDao.getAllCoachs()){
            builder.add(Json.createObjectBuilder()
                    .add("id", coach.getId())
                    .add("email", coach.getEmail())
                    .add("password", coach.getPassword()));
        }
        return builder.build();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public JsonArray getCoachById(@PathParam("id") final int id){
        JsonArrayBuilder builder = Json.createArrayBuilder();
        CCoach coach = coachDao.find(CCoach.class, id);
        if(coach != null){
            builder.add(Json.createObjectBuilder()
                    .add("id", coach.getId())
                    .add("email", coach.getEmail())
                    .add("password", coach.getPassword()));
        }
        return builder.build();
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
    public JsonArray connectCoach(@FormParam(CCoach.FIELD_EMAIL) String pEmail,
                              @FormParam(CCoach.FIELD_PASSWORD) String pPwd){
        return null;

    }
}
