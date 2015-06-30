package com.fmi.javaee.autograder.services;

import com.fmi.javaee.autograder.core.SaveTasks;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.io.IOException;
import java.io.IOException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TemporalType;
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
import org.glassfish.jersey.media.multipart.FormDataBodyPart;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import org.glassfish.jersey.media.multipart.FormDataParam;

import org.json.simple.JSONArray;

import org.json.simple.JSONArray;

import org.json.simple.JSONArray;

import org.json.simple.JSONObject;

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
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String all() throws IOException {

        JSONArray tasks_arr = new JSONArray();

        TypedQuery<Tasks> query = em.createNamedQuery("Tasks.findAll", Tasks.class);
        List<Tasks> tasks = query.getResultList();

        for (int i = 0; i < tasks.size(); i++) {
            JSONObject t = new JSONObject();
            t.put("task_name", tasks.get(i).getTaskName());
            t.put("task_id", tasks.get(i).getId());
            tasks_arr.add(t);
        }

        return tasks_arr.toJSONString();
    }

    @POST
    @Path("submit")
    public String submit(@FormParam("user") String user,
            @FormParam("task_id") int task_id,
            @FormParam("src_code") String src_code) {

        JSONObject result = new JSONObject();

        TypedQuery<Tasks> task_query = em.createNamedQuery("Tasks.findById", Tasks.class);
        task_query.setParameter("id", task_id);

        TypedQuery<User> user_query = em.createNamedQuery("User.findByName", User.class);
        user_query.setParameter("name", user);

        Results newResult = new Results();

        newResult.setUserId(user_query.getSingleResult());
        newResult.setTaskId(task_query.getSingleResult());
        newResult.setSource(src_code);
        if (em != null) {
            em.getTransaction().begin();
            em.persist(newResult);
            em.flush();
            em.getTransaction().commit();
        } else {
            result.put("err", "error in the service!");
        }
        return result.toJSONString();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String get(@PathParam("id") int id) {

        JSONObject result = new JSONObject();
        JSONArray rs_arr = new JSONArray();

        TypedQuery<Tasks> query = em.createNamedQuery("Tasks.findById", Tasks.class);

        Query rs_query = em.createNativeQuery("SELECT * FROM Results WHERE task_id=" + id, Results.class);

        query.setParameter("id", id);
        rs_query.setParameter("tid", id);

        Tasks task = query.getSingleResult();
        List<Results> task_results = rs_query.getResultList();

        result.put("task_name", task.getTaskName());
        result.put("task_file", task.getTaskFile());

        for (Results task_result : task_results) {
            JSONObject r = new JSONObject();
            r.put("score", task_result.getScore());
            r.put("status", task_result.getStatus());
            r.put("source", task_result.getSource());
            rs_arr.add(r);
        }

        result.put("results", rs_arr);
        return result.toJSONString();
    }

    @POST
    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response createTask(FormDataMultiPart formParams) throws URISyntaxException {
        Map<String, List<FormDataBodyPart>> fieldsByName = formParams.getFields();
        FormDataBodyPart namePart = formParams.getField("name");
        String taskName = namePart.getValue();
        String fileName = formParams.getField("pdf").getContentDisposition().getFileName();
        String input = formParams.getField("inputRar").getContentDisposition().getFileName();
        String output = formParams.getField("outputRar").getContentDisposition().getFileName();
        Tasks task = new Tasks();
        task.setTaskName(taskName);
        em.getTransaction().begin();
        em.persist(task);
        em.flush();
        em.getTransaction().commit();
        Integer id;
        TypedQuery<Tasks> query = em.createNamedQuery("Tasks.findByTaskName", Tasks.class);
        query.setParameter("taskName", taskName);
        Tasks t = query.getSingleResult();
        id = t.getId();
        String location = System.getProperty("user.dir") + "Problem Set" + id;
        Test test = new Test();
        test.setInputRar(location + input);
        test.setOutputRar(location + output);
        test.setTaskId(t);
        em.getTransaction().begin();
        em.persist(test);
        em.flush();
        em.getTransaction().commit();
        

        for (List<FormDataBodyPart> fields : fieldsByName.values()) {
            for (FormDataBodyPart field : fields) {
                if (field.getName().equals("name")) {
                    continue;
                }

                InputStream is = field.getEntityAs(InputStream.class);
                FormDataContentDisposition contendDisposion = field.getFormDataContentDisposition();
                SaveTasks.writeToFile(is, contendDisposion.getFileName(), id);
            }
        }

        return Response.ok().build();
    }

}
