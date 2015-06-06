package com.javaeefmi.autograder;


import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.json.simple.JSONObject;

/**
 * @author Daniel Angelov
 */
@Path("user")
public class UserService {
    
    
    
    
   // @Context private HttpServletRequest m_Request;
    
    private ArrayList<User> m_UsersDb = new ArrayList<User>();
    
    public UserService()
    {
      m_UsersDb.add(new User( "alpha", "5ebe2294ecd0e0f08eab7690d2a6ee69",Roles.User.toString())); //password "secret"
      m_UsersDb.add(new User("beta", "5ebe2294ecd0e0f08eab7690d2a6ee69",Roles.User.toString()));//password "secret"
      m_UsersDb.add(new User("gamma", "5ebe2294ecd0e0f08eab7690d2a6ee69",Roles.User.toString()));//password "secret"
    }
    
    
    @POST
    @Path("login")
    public String loginUser(@FormParam("name") String name, @FormParam("passwd") String passwd)
    {
        
      User newUser = new User(name,passwd,Roles.User.toString());
      
      /*
      {
        "srvResponce": "ok;nok;"
      }
      */
      
      return newUser.toString();
    }
      
    
    @POST
    @Path("create")
    public String createNewUser(@FormParam("name") String name, @FormParam("passwd") String passwd)
    {
        
      User newUser = new User(name,passwd,Roles.User.toString());
      
      /*
      {
        "srvResponce": "ok;nok;"
      }
      */
      return newUser.toString();
    }

    
    @GET
    @Path("me")
    public String me() throws NoSuchAlgorithmException, UnsupportedEncodingException 
    {
        
        return UserSecurity.MD5("123");
        /* Session rquired */
        //return "my profile!";
    }
    
    @GET
    @Path("info/{id}")
    public String info(@PathParam("id") int id)
    {
        
        /*
        {
            "user_id" = 1,
            "username" = "tautochrone",
            "challenges_submited":
            [
                challenge:
                {
                    "chg_id" : "challenge id",
                    "name":"challenge name",
                    "scores": "# of pts"
                 },
                 challenge:
                {
                    "chg_id" : "challenge id",
                    "name":"challenge name",
                    "scores": "# of pts"
                 }
                 ...
            ]
        
        }   
        */
        
        
        JSONObject user = new JSONObject();
        
        for (int i = 0; i < m_UsersDb.size(); i++) {
            if(id == m_UsersDb.get(i).getId())
            {
                user.put("username", m_UsersDb.get(i).getUsername());
                user.put("roleId",m_UsersDb.get(i).getRole());
                user.put("Id",m_UsersDb.get(i).getId());
                
                //jsonResult = m_UsersDb.get(i).toString();
                break;
            }
        }
        return user.toJSONString();
    }  
}
