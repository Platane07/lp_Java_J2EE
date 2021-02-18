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



        //
        em.getTransaction().begin();

        // create new etudiant
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
}
