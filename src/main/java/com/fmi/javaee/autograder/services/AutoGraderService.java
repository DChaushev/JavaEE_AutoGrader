/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmi.javaee.autograder.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author tautochrone
 */
@Path("hello")
public class AutoGraderService {
    
    @GET
    @Produces("text/plain")
    public String hello()
    {
        return "dada111111111111";
    }
    
}
