package com.fmi.javaee.autograder.services;

import com.fmi.javaee.autograder.core.SaveTasks;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * @author Daniel Angelov
 */
@Path("task")
public class TaskService {

    private final EntityManager em;

    public TaskService() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.javaeefmi_AutoGrader_war_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
    }

    @GET
    @Path("hello")
    public String me() {
        return "Hi!";
    }

    @GET
    @Path("all")
    public String all(@PathParam("id") int id) {
        TypedQuery<Tasks> query = em.createNamedQuery("Tasks.findAll", Tasks.class);
        query.setParameter("id", id);
        List<Tasks> tasks = query.getResultList();
        Map<String, String> map = new LinkedHashMap<>();

        /*
        
         {
         challenges:
         [
         challenge: {
         "chg_id" : "challenge id"
         "name": "challenge name",

         },
         challenge: {
         "chg_id" : "challenge id"
         "name": "challenge name",

         },
         challenge: {
         "chg_id" : "challenge id"
         "name": "challenge name",

         }
         ...
         ]
         }
         */
        for (Tasks t : tasks) {
            map.put("chg_id", "" + t.getId());
            map.put("name", t.getTaskName());
        }

        return JSONValue.toJSONString(map);
    }

    @POST
    @Path("submit")
    public String submit(@FormParam("user_id") int user_id, @FormParam("src_code") String src_code) {

        /*
         {
         "srvResponce": "ok;nok;"
         }
         */
        JSONObject user = new JSONObject();

        return user.toJSONString();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String get(@PathParam("id") int id) {

        TypedQuery<Tasks> query = em.createNamedQuery("Tasks.findById", Tasks.class);
        query.setParameter("id", id);
        Tasks task = query.getSingleResult();

        /*
        
         challenge: {
        
         "chg_id": "id of the challenge",
         "name": "name of the challenge",
         "chg_desc": "decscription of the challenge"
         }
         */
        JSONObject user = new JSONObject();
        user.put("chg_id", task.getId());
        user.put("name", task.getTaskName());
        user.put("chg_desc", task.getTaskFile());
        return user.toJSONString();
    }
    
    @POST
    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response createTask(@FormDataParam("pdf") InputStream pdfFile, @FormDataParam("pdf") FormDataContentDisposition fileDetail){
        //System.out.println("Task name " + taskName);
        SaveTasks.writeToFile(pdfFile, "pdf");
       // SaveTasks.writeToFile(outputRar, "outTests");
       // SaveTasks.writeToFile(inputRar, "inputRar");
        return Response.status(200).entity("Its ok").build();
    }
    
  
}
