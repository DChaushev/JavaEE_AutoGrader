package com.javaeefmi.autograder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;
import javax.management.relation.Role;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Fatma
 */
public class NewClass {
    private EntityManagerFactory emf;
 
    private EntityManager getEntityManager() {
        if (emf == null) {
            
            emf = Persistence.createEntityManagerFactory("com.javaeefmi_AutoGrader_war_1.0-SNAPSHOTPU");
        }
        return emf.createEntityManager();
    }
 
    public void getUsers() {
        EntityManager em = getEntityManager();
        try {
            em.persist(new User("Ivan","1234",Roles.User.toString()));
            
            //em.flush();
            em.close();
            em = getEntityManager();
            javax.persistence.Query q = em.createNativeQuery("select * from Users as c",User.class);
            int p = q.getFirstResult();
            User u = (User)q.getResultList().get(p);
            System.out.println(u.getUsername());
        } finally {
            em.close();
        }
    }
    
    
    
    
   public static void main(String args[]){
       System.out.println("test");
       NewClass test = new NewClass();
       
       test.getUsers();
       
       EntityManager em =  test.getEntityManager();
       em.close();
       
      // Task t = new Task();
       //t.setText("blaaa");
       //em.persist(t);
     //  System.out.println(users[0].getUsername());
       
      // List<Task> tasks = em.createQuery("select t from Task as t");
       test.emf.close();
   } 
}