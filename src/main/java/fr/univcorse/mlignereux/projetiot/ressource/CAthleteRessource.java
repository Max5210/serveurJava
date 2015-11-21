package fr.univcorse.mlignereux.projetiot.ressource;

import fr.univcorse.mlignereux.projetiot.dao.CAthleteDAO;
import fr.univcorse.mlignereux.projetiot.entity.CAthlete;
import fr.univcorse.mlignereux.projetiot.entity.CUser;
import sun.rmi.runtime.Log;

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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by asus on 20/10/2015.
 */

@Path("/athletes")
@Stateless
@LocalBean
@Consumes("*/*")
public class CAthleteRessource {

    public static final String BAD_REQUEST = "Not enough data is provided in order to create the athlete";
    public static final String CONFLICT = "An account has already this id";
    public static final String EMAIL_ALREADY_USED = "An athlete in database has already this email";
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
    private CAthleteDAO athleteDAO;

    @POST
    @Path("/add")
    @Produces("application/json")
    public Response postAthlete(@FormParam(CAthlete.FIELD_EMAIL) String pEmail,
                                @FormParam(CAthlete.FIELD_PASSWORD) String pPwd){
        System.out.println(pEmail);
        System.out.println(pPwd);

        if(pEmail == null || pPwd == null){
            return Response.status(Response.Status.BAD_REQUEST)//400 Bad request if not enough data is provided
                    .entity(BAD_REQUEST)
                    .build();
        }

        if(athleteDAO.findByEmail(CAthlete.class, pEmail) != null){
            return Response.status(Response.Status.FOUND)
                    .entity(EMAIL_ALREADY_USED)
                    .build();
        }

        CAthlete athleteCreated = athleteDAO.create(pEmail,pPwd);

        if (athleteCreated != null) {
            return Response.status(Response.Status.CREATED)// 201 created
                    .header("Location",
                            "/athletes/"
                                    + String.valueOf(athleteCreated.getId())).
                            type(MediaType.APPLICATION_JSON_TYPE).build();
        }
        return Response.status(500).entity(DEFAULT_RESPONSE).build();
    }

    @Path("/all")
    @GET
    @Produces("application/json")
    public List<CAthlete> getAllAthletes(){
        List<CAthlete> athletes = new ArrayList<CAthlete>(athleteDAO.getAllAthletes());
        for(CAthlete athlete : athleteDAO.getAllAthletes()){
            athletes.add(athlete);
        }
        return athletes;
    }


    /*@Path("/all")
    @GET
    @Produces("application/json")
    public JsonArray getAllAthletes(){
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for(CAthlete athlete : athleteDAO.getAllAthletes()){
            builder.add(Json.createObjectBuilder()
                    .add("id", athlete.getId())
                    .add("email", athlete.getEmail())
                    .add("password", athlete.getPassword()));
        }
        return builder.build();
    }*/

    @GET
    @Path("{id}")
    @Produces("application/json")
    public JsonArray getAthleteById(@PathParam("id") final int id){
        JsonArrayBuilder builder = Json.createArrayBuilder();
        CAthlete athlete = athleteDAO.find(CAthlete.class, id);
        if(athlete != null){
            builder.add(Json.createObjectBuilder()
                    .add("id", athlete.getId())
                    .add("email", athlete.getEmail())
                    .add("password", athlete.getPassword())
                    .add("status", athlete.getStatus().toString()));
        }
        return builder.build();
    }

    @GET
    @Path("/email/{email}")
    @Produces("application/json")
    public JsonArray getAthleteByEmail(@PathParam("email") final String pEmail){
        JsonArrayBuilder builder = Json.createArrayBuilder();
        CAthlete athlete = athleteDAO.findByEmail(CAthlete.class, pEmail);
        if(athlete != null){
            builder.add(Json.createObjectBuilder()
                    .add("id", athlete.getId())
                    .add("email", athlete.getEmail())
                    .add("password", athlete.getPassword())
                    .add("status", athlete.getStatus().toString()));
        }
        return builder.build();
    }

    @GET
    @Path("/connect")
    @Produces("application/json")
    public Response connectAthlete(@QueryParam(CAthlete.FIELD_EMAIL) String pEmail,
                                   @QueryParam(CAthlete.FIELD_PASSWORD) String pPwd,
                                   @QueryParam(CAthlete.FIELD_STATUS) String pStatus){

        System.out.println(pEmail);
        System.out.println(pPwd);
        System.out.println(pStatus);

        if(pEmail == null || pPwd == null || pStatus == null){
            return Response.status(Response.Status.BAD_REQUEST)//400 Bad request if not enough data is provided
                    .entity(BAD_REQUEST)
                    .build();
        }

        if(athleteDAO.findByEmail(CAthlete.class, pEmail) == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(NOT_FOUND)
                    .build();
        }

        CAthlete athlete = athleteDAO.getAthlete(pEmail,pPwd);


        if(athlete != null){
            return Response.status(Response.Status.OK)
                    .header("Location",
                            "/athletes/"
                                    + String.valueOf(athlete.getId())).
                            type(MediaType.APPLICATION_JSON_TYPE).build();
        }
        return Response.status(401).entity(Response.Status.UNAUTHORIZED).build();
    }


}
