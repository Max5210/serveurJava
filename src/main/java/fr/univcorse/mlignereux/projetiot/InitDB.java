package fr.univcorse.mlignereux.projetiot;

/**
 * Created by asus on 04/10/2015.
 */

import fr.univcorse.mlignereux.projetiot.entity.CUser;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class InitDB {
    public static void main(String[] args) {
        // Récupération d'une session Hibernate
        Session s = HibernateUtils.getSession();

        // Début de la transaction
        Transaction t = s.beginTransaction();

        // Création d'un objet Event
        CUser user = new CUser();

        // Enregistrement de l'event
        s.save(user);

        // Fin de la transaction
        t.commit();

        // Fermeture de la session Hibernate
        s.close();
    }
}
