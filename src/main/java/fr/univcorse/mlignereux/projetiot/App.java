package fr.univcorse.mlignereux.projetiot;

import fr.univcorse.mlignereux.projetiot.entity.CAthlete;
import fr.univcorse.mlignereux.projetiot.entity.CTraining;
import fr.univcorse.mlignereux.projetiot.entity.CUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{

    private static EntityManager em;
    public static void main( String[] args )
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("createUser");
        em = emf.createEntityManager();

        createUser("p1","pwd", CUser.Status.ATHLETE);


    }

    private static void createUser(String pseudo, String pwd, CUser.Status status){
        em.getTransaction().begin();
        CUser user = new CUser(pseudo,pwd, status);
        em.persist(user);
        em.getTransaction().commit();
    }
}
