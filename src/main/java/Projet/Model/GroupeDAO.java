package Projet.Model;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class GroupeDAO {

    public static Groupe create(String nom, List<Module> modules) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // create
        em.getTransaction().begin();

        // create new groupe
        Groupe groupe = new Groupe();
        groupe.setNom(nom);

        em.persist(groupe);

        for(Module module : modules){
            groupe.addModule(module);
        }

        em.merge(groupe);


        em.getTransaction().commit();





        // Close the entity manager
        em.close();

        return groupe;
    }


    public static int removeAll() {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // RemoveAll
        int deletedCount = em.createQuery("DELETE FROM Groupe").executeUpdate();

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return deletedCount;
    }


    public static List<Groupe> getAll() {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Recherche 
        Query q = em.createQuery("SELECT g FROM Groupe g");

        @SuppressWarnings("unchecked")
        List<Groupe> listGroupe = q.getResultList();

        return listGroupe;
    }

    public static Groupe getById(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Recherche
        Query q = em.createQuery("SELECT g FROM Groupe g WHERE g.id = :id", Groupe.class);

        Groupe groupe = (Groupe) q.setParameter("id", id).getSingleResult();

        return groupe;
    }

    public static Groupe update(Groupe groupe) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // Attacher une entité persistante (etudiant) à l’EntityManager courant pour réaliser la modification
        em.merge(groupe);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return groupe;
    }

    public static Integer getNombreEtudiants(Groupe groupe){
        EtudiantDAO etudiantDAO = new EtudiantDAO();
        int nombreEtudiants = etudiantDAO.retrieveByGroupe(groupe).size();
        return nombreEtudiants;
    }
}
