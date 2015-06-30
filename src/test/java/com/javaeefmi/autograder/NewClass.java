package com.javaeefmi.autograder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.fmi.javaee.autograder.services.User;
import com.fmi.javaee.autograder.services.Roles;
import com.fmi.javaee.autograder.services.Tasks;
import com.fmi.javaee.autograder.services.Test;
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
            em.persist(new Tasks("test1", "test.pdf"));

            //em.flush();
            em.close();
            em = getEntityManager();
            javax.persistence.Query q = em.createNativeQuery("select * from Tasks as c", Tasks.class);
            int p = q.getFirstResult();
            Tasks u = (Tasks) q.getResultList().get(p);
            System.out.println(u.getTaskName());
        } finally {
            em.close();
        }
    }

    public static void main(String args[]) {
        System.out.println("test");
        NewClass test = new NewClass();

       

        EntityManager em = test.getEntityManager();
       
        Tasks task = new Tasks();
        task.setTaskName("taskName");
        Test t = new Test();
        t.setTaskId(task);
        em.getTransaction().begin();
        em.persist(t);
        em.persist(task);
        em.flush();
        em.getTransaction().commit();
      // Task t = new Task();
        //t.setText("blaaa");
        //em.persist(t);
        //  System.out.println(users[0].getUsername());

        // List<Task> tasks = em.createQuery("select t from Task as t");
        test.emf.close();
    }
}
