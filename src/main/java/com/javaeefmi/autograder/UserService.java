package com.javaeefmi.autograder;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
    private final EntityManager em;

    public UserService() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.javaeefmi_AutoGrader_war_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
    }

    @POST
    @Path("login")
    public String loginUser(@FormParam("name") String name, @FormParam("passwd") String passwd) {
        TypedQuery<User> query = em.createNamedQuery("User.findByName", User.class);
        try {
            User user = query.setParameter("name", name).getSingleResult();
            System.out.println(user.getPassword());
            if (passwd == null ? user.getPassword() == null : passwd.equals(user.getPassword())) {
                //we go ahead with login
            } else {
                // we give the user a red error
            }
        } catch (javax.persistence.NoResultException e) {
            //no such user at all, should we tell???
        }

        User newUser = new User(name, passwd, Roles.User.toString());

        /*
         {
         "srvResponce": "ok;nok;"
         }
         */
        return newUser.toString();
    }

    @POST
    @Path("create")
    public String createNewUser(@FormParam("name") String name, @FormParam("passwd") String passwd) throws NoSuchAlgorithmException, UnsupportedEncodingException {

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
