package Projet.Model;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class AbsenceDAO {

    public static Absence create(LocalDateTime debut, LocalDateTime fin, Boolean justifie, int idEtudiant) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();


        em.getTransaction().begin();

        // create new absence
        Absence absence = new Absence();
        absence.setDébut(debut);
        absence.setFin(fin);
        absence.setJustifié(justifie);
        Etudiant etudiant = EtudiantDAO.getById(idEtudiant);
        absence.setEtudiant(etudiant);
        em.persist(absence);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return absence;
    }

    public static Absence delete(int idAbsence) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();
        //
        em.getTransaction().begin();

        //Suppression d'une absence
        Absence absence = em.find(Absence.class, idAbsence);
        Absence absence2 = em.merge(absence);
        em.remove(absence2);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return absence;
    }

    public static Absence update(Absence absence) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Ouverture de la transaction
        em.getTransaction().begin();

        // Attacher une entité persistante (absence) à l’EntityManager courant  pour réaliser la modification
        em.merge(absence);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return absence;
    }

    public static Absence getById(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        Absence absence = em.find(Absence.class, id);
        // etu est maintenant un objet de la classe Etudiant
        // ou NULL si l'étudiant n'existe pas


        // Close the entity manager
        em.close();

        return absence;
    }


}
