/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaeefmi.autograder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.json.simple.JSONObject;

/**
 * @author Daniel Angelov
 */
@Path("contest")
public class ContestService {
    @GET
    @Path("hello")
    public String me()
    {
       return "Hi!";
    }
    
    @GET
    @Path("{id}")
    public String info(@PathParam("id") int id)
    {
        JSONObject user = new JSONObject();

        return user.toJSONString();
    }  

}
