package Projet.Model;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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

        // create new note
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
        Note note2 = em.merge(note);
        em.remove(note2);
        em.getTransaction().commit();

        em.close();

    }

    //Récupération d'une note par l'id de l'étudiant et du module, étant donnée qu'une note n'a pas sa propre id
    public static Note getByEtudiantAndModule(int idEtudiant, int idModule) {

        EntityManager em = GestionFactory.factory.createEntityManager();

        Etudiant etudiant = EtudiantDAO.getById(idEtudiant);
        Module module = ModuleDAO.getById(idModule);

        // Recherche
        Query q = em.createQuery("SELECT n FROM Note n WHERE n.etudiant = :etudiant AND n.module = :module", Note.class);

        Note note = (Note) q.setParameter("etudiant", etudiant).setParameter("module", module).getSingleResult();
        return note;
    }
}
