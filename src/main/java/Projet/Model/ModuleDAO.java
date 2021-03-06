package Projet.Model;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ModuleDAO {

    public static Module create(String nom, List<Groupe> groupes) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // create
        em.getTransaction().begin();

        // create new groupe
        Module module = new Module();
        module.setNom(nom);

        em.persist(module);

        for (Groupe groupe : groupes) {
            module.addGroupe(groupe);
        }

        em.merge(module);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return module;
    }

    // Retourne l'ensemble des modules
    public static List<Module> getAll() {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Recherche
        Query q = em.createQuery("SELECT m FROM Module m ORDER BY m.id DESC");

        @SuppressWarnings("unchecked")
        List<Module> listModule = q.getResultList();

        return listModule;
    }

    public static Module getById(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        Module module = em.find(Module.class, id);
        // Récupération d'un module par son id
        // ou NULL si le module n'existe pas


        // Close the entity manager
        em.close();

        return module;
    }

    //Suppression d'un groupe dans un module
    public static void deleteGroupe(int idModule, int idGroupe) {

        EntityManager em = GestionFactory.factory.createEntityManager();
        //
        em.getTransaction().begin();

        Module module = em.find(Module.class, idModule);
        Groupe groupe = em.find(Groupe.class, idGroupe);
        module.removeGroupe(groupe);

        em.merge(groupe);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

    }

    //ajouter un groupe dans un module
    public static void addGroupe(int idGroupe, int idModule) {

        EntityManager em = GestionFactory.factory.createEntityManager();
        //
        em.getTransaction().begin();

        Module module = em.find(Module.class, idModule);
        Groupe groupe = em.find(Groupe.class, idGroupe);
        module.addGroupe(groupe);
        em.merge(groupe);
        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

    }

    public static Module delete(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();
        //
        em.getTransaction().begin();

        Module module = em.find(Module.class, id);
        try {


            em.merge(module);
            em.remove(module);

            // Commit
            em.getTransaction().commit();

            em.close();

            return module;

        } catch (Exception e) {
            System.out.println("erreur lors de la suppression du module " + e);
        }
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

    public static List<Module> getByGroupe(Groupe groupe) {

        EntityManager em = GestionFactory.factory.createEntityManager();

        Query q = em.createQuery("SELECT m FROM Module m WHERE m.groupes = :groupe", Module.class);

        return (List<Module>) q.setParameter("groupe", groupe).getResultList();
    }
}
