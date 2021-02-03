package Projet.Model;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ModuleDAO {

    public static Module create(String nom) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // create
        em.getTransaction().begin();

        // create new groupe
        Module module = new Module();
        module.setNom(nom);
        em.persist(module);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return module;
    }

    public static Module getById(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        Module module = em.find(Module.class, id);
        // etu est maintenant un objet de la classe Etudiant
        // ou NULL si l'étudiant n'existe pas


        // Close the entity manager
        em.close();

        return module;
    }

    public static Module update(Module module) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // Attacher une entité persistante (etudiant) à l’EntityManager courant  pour réaliser la modification
        em.merge(module);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return module;
    }

    public static List<Module> getByGroupe(Groupe groupe){

        EntityManager em = GestionFactory.factory.createEntityManager();

        Query q = em.createQuery("SELECT m FROM Module m WHERE m.groupes = :groupe", Module.class);

        return (List<Module>) q.setParameter("groupe", groupe).getResultList();
    }
}
