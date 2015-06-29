package com.javaeefmi.autograder;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONObject;

/**
 * @author Daniel Angelov
 */
@Path("user")
public class UserService {

    // @Context private HttpServletRequest m_Request;
    private final EntityManager em;

    public UserService() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.javaeefmi_AutoGrader_war_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
    }

    @POST
    @Path("login")
    
    public String loginUser(@FormParam("name") String name, @FormParam("passwd") String passwd) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        JSONObject response = new JSONObject();
        response.put("srvResponse", "nok");
        if(passwd == null || passwd.equals("")){
            return response.toJSONString();
                   
        }
        boolean flag = false;
        TypedQuery<User> query = em.createNamedQuery("User.findByName", User.class);
        
        System.out.println("user "+ name+" is attempting to login");
        try {
            User user = query.setParameter("name", name).getSingleResult();
            
            System.out.println("Users's password was correct");
                if(user.getPassword().equals(UserSecurity.MD5(passwd))){
                    flag = true;
                    response.replace("srvResponse", "ok");
                    return response.toJSONString();
                }
                
           
        } catch (javax.persistence.NoResultException e) {
            
            System.out.println("Users's username was not correct - no such user");
        } catch (java.lang.IllegalStateException e) {
            System.out.println("This is freaking bugging me, I have no idea why it gives java.lang.IllegalStateException");
        }
        

        /*
         {
         "srvResponce": "ok;nok;"
         }
         */
       
        
        return response.toJSONString();
    }

    @POST
    @Path("create")
    public String createNewUser(@FormParam("name") String name, @FormParam("passwd") String passwd) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        TypedQuery<User> query = em.createNamedQuery("User.findByName", User.class);
        try{
            User user = query.setParameter("name", name).getSingleResult();
            if(user != null){
                return String.format("Username already taken!!");
            }
        }catch(NonUniqueResultException e){
            return String.format("Username already taken!!");
        }catch(NoResultException e){
            
        }
        
        
        passwd = UserSecurity.MD5(passwd);
        User newUser = new User(name, passwd, Roles.User.toString());
        if (em != null) {
            em.getTransaction().begin();
            em.persist(newUser);
            em.flush();
            System.out.println("User " + newUser.getUsername() + " persisted!");
            em.getTransaction().commit();
        } else {
            System.out.println("em is null");
        }

        /*
         {
         "srvResponce": "ok;nok;"
         }
         */
        return newUser.toString();
    }

    @GET
    @Path("me")
    public String me() throws NoSuchAlgorithmException, UnsupportedEncodingException {

        return UserSecurity.MD5("123");
        /* Session rquired */
        //return "my profile!";
    }

    @GET
    @Path("info/{id}")
    public String info(@PathParam("id") int id) {

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

        return null;
    }
}
