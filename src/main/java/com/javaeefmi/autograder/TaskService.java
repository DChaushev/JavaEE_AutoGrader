package com.javaeefmi.autograder;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.json.simple.JSONObject;

/**
 * @author Daniel Angelov
 */
@Path("task")
public class TaskService {
    @GET
    @Path("hello")
    public String me()
    {
       return "Hi!";
    }
    
    @GET
    @Path("all")
    public String all(@PathParam("id") int id)
    {
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
        
        JSONObject user = new JSONObject();

        return user.toJSONString();
    }

    @POST
    @Path("submit")
    public String submit(@FormParam("user_id") int user_id, @FormParam("src_code") String src_code)
    {
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
    public String get(@PathParam("id") int id)
    {
        /*
        
        challenge: {
        
            "chg_id": "id of the challenge",
            "name": "name of the challenge",
            "chg_desc": "decscription of the challenge"
        }
        */
        
        
        JSONObject user = new JSONObject();

        
        
        return user.toJSONString();
    }  

}
