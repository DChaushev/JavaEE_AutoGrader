/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.javaee.autograder.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;

import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author Petar
 */
@Path("results")
public class ResultsService {
    private final EntityManager em;

    public ResultsService() {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.javaeefmi_AutoGrader_war_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
    }

    @GET
    @Path("{user_id}/{task_id}")
    public String getUserResults(@PathParam("user_id") String userId,@PathParam("task_id") String taskId) {
        
        TypedQuery<User> query = em.createNamedQuery("Results.findByUserByTask", User.class);
        
        return "not yet implemented";
    }

    /**
     * PUT method for updating or creating an instance of ResultsService
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    
    @POST
    @Path("create")
    public String createResult(@FormParam("solution") String solution, @FormParam("user_id") String user_id,@FormParam("task_id") String task_id) {
        
        return "test";
    }
}
