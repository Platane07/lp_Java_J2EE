package Projet.Model;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class NoteDAO {



    public static Note update(Note note) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // Attacher une entité persistante (note) à l’EntityManager courant  pour réaliser la modification
        em.merge(note);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return note;
    }

    public static Note create(float value, int idEtudiant, int idModule) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // create new etudiant
        Note note = new Note();
        note.setValeur(value);
        Etudiant etudiant = EtudiantDAO.getById(idEtudiant);
        note.setEtudiant(etudiant);
        Module module = ModuleDAO.getById(idModule);
        note.setModule(module);
        em.persist(note);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return note;
    }

    public static void deleteById(int idEtudiant, int idModule) {
        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();
        Note note = NoteDAO.getByEtudiantAndModule(idEtudiant, idModule);
        em.remove(note);
        em.getTransaction().commit();

        em.close();

    }

    public static Note getByEtudiantAndModule(int idEtudiant, int idModule){

        EntityManager em = GestionFactory.factory.createEntityManager();

        Etudiant etudiant = EtudiantDAO.getById(idEtudiant);
        Module module = ModuleDAO.getById(idModule);

        // Recherche
        Query q = em.createQuery("SELECT n FROM Note n WHERE n.etudiant = :etudiant AND n.module = :module", Note.class);

        Note note = (Note) q.setParameter("etudiant", etudiant).setParameter("module", module).getSingleResult();

        return note;
    }
}
