package com.fmi.javaee.autograder.services;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
    @Produces(MediaType.APPLICATION_JSON)
    public User loginUser(@FormParam("name") String name, @FormParam("passwd") String passwd) {

        TypedQuery<User> query = em.createNamedQuery("User.findByName", User.class);
        User newUser = new User(name, passwd, Roles.User.toString());
        System.out.println("user " + name + " is attempting to login");
        try {
            User user = query.setParameter("name", name).getSingleResult();

            if (passwd == null ? user.getPassword() == null : passwd.equals(user.getPassword())) {
                //we go ahead with login
                System.out.println("Users's password was correct");
                user.setPassword("");
                return user;
            } else {
                // we give the user a red error
                System.out.println("Users's password was not correct");
                newUser.setPassword("error");
            }
        } catch (javax.persistence.NoResultException e) {
            newUser.setPassword("error");
            System.out.println("Users's username was not correct - no such user");
        } catch (java.lang.IllegalStateException e) {
            System.out.println("This is freaking bugging me, I have no idea why it gives java.lang.IllegalStateException");
        }


        /*
         {
         "srvResponce": "ok;nok;"
         }
         */
        return newUser;
    }

    @POST
    @Path("create")
    public String createNewUser(@FormParam("name") String name, @FormParam("passwd") String passwd) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        TypedQuery<User> query = em.createNamedQuery("User.findByName", User.class);
        try {
            User user = query.setParameter("name", name).getSingleResult();
            if (user != null) {
                return String.format("Username already taken!!");
            }
        } catch (NonUniqueResultException e) {
            return String.format("Username already taken!!");
        } catch (NoResultException e) {

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

    /**
     *
     * @param from
     * @param count
     * @return list of users after from and counting count
     */
    @GET
    @Path("all/{first}/{max}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> info(@PathParam("first") int first, @PathParam("max") int max) {
        TypedQuery<User> query;
        if (first == 0 && max == 0) {
            query = em.createNamedQuery("User.findAll", User.class);
        } else {
            query = em.createNamedQuery("User.findAll", User.class);
            query.setFirstResult(first);
            query.setMaxResults(max);
        }
        List<User> results = query.getResultList();
        for (User u : results) {
            u.setPassword(null);
            u.setResultsCollection(null);
        }
        return results;

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

    @POST
    @Path("role")
    public String changeRole(@FormParam("username") String username) {
        TypedQuery<User> query = em.createNamedQuery("User.findByName", User.class);
        try {
            User user = query.setParameter("name", username).getSingleResult();
            if (user != null) {
                em.getTransaction().begin();
                em.persist(user);
                //System.out.println(user.getRole());
                if (user.getRole().toString().equals("User")) {
                    user.setRole("Administrator");
                } else {
                    user.setRole("User");
                }

                em.flush();
                System.out.println("User " + user.getUsername() + " changed its role to " + user.getRole());
                em.getTransaction().commit();

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return "OK";
    }
}
