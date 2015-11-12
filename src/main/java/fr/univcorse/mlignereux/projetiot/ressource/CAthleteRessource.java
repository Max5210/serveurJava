package fr.univcorse.mlignereux.projetiot.ressource;

import fr.univcorse.mlignereux.projetiot.App;
import fr.univcorse.mlignereux.projetiot.dao.CAthleteDAO;
import fr.univcorse.mlignereux.projetiot.dao.CDAO;
import fr.univcorse.mlignereux.projetiot.entity.CAthlete;

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

@Path("/athletes")
public class CAthleteRessource {

    public static final String BAD_REQUEST = "Not enough data is provided in order to create the athlete";
    public static final String CONFLICT = "An account has already this id";
    public static final String LOGIN_ALREADY_USED = "An athlete in database has already this login";
    public static final String BAD_PATTERN_LOGIN = "Login with a wrong pattern";
    public static final String CREATED = "A new athlete has been created at the location you specified";
    public static final String DEFAULT_RESPONSE = "This should not be happened";
    public static final String NOT_FOUND = "The athlete with the given id does not exist in the database";
    public static final String UPDATED = "The athlete you specified has been fully updated at the location you specified";
    public static final String DELETED = "The athlete has been successfully removed from database";
    public static final String NOT_MODIFIED = "The athlete with the given id is not modified";
    public static final String BAD_JSON = "Error Syntax JSON.";

    @Inject
    private CAthleteDAO athleteDAO;

    public CAthleteRessource(){ athleteDAO = CDAO.getAthleteDAO();}

    @POST
    @Consumes("application/json")
    public Response postAthlete(@FormParam(CAthlete.FIELD_PSEUDO) String pPseudo,
                                @FormParam(CAthlete.FIELD_PASSWORD) String pPwd){
        if(pPseudo == null || pPwd == null){
            return Response.status(Response.Status.BAD_REQUEST)//400 Bad request if not enough data is provided
                    .entity(BAD_REQUEST)
                    .build();
        }

        CAthlete athlete = CAthlete.create(pPseudo,pPwd);

        CAthlete athleteCreated = athleteDAO.create(athlete);
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
    public JsonArray getAllAthletes(){
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for(CAthlete athlete : athleteDAO.getAllAthletes()){
            builder.add(Json.createObjectBuilder().add("pseudo", athlete.getPseudo()).add("password",athlete.getPassword()));
        }
        return builder.build();
    }

}
