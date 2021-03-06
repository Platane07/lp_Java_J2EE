package Projet.Model;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EtudiantDAO {


    public static Etudiant getById(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        em.getTransaction().begin();
        //
        Etudiant etu = em.find(Etudiant.class, id);
        // etu est maintenant un objet de la classe Etudiant
        // ou NULL si l'étudiant n'existe pas


        // Close the entity manager
        em.close();

        return etu;
    }


    public static Etudiant create(String prenom, String nom, Groupe groupe) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // create new etudiant
        Etudiant etudiant = new Etudiant();
        etudiant.setPrenom(prenom);
        etudiant.setNom(nom);
        if (groupe != null) {
            etudiant.setGroupe(groupe);
        }
        em.persist(etudiant);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return etudiant;
    }

    public static void update(Etudiant etudiant) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // Attacher une entité persistante (etudiant) à l’EntityManager courant  pour réaliser la modification
        em.merge(etudiant);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

    }


    public static void remove(Etudiant etudiant) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // Retrouver l'entité persistante et ses liens avec d'autres entités en vue de la suppression
        etudiant = em.find(Etudiant.class, etudiant.getId());
        em.remove(etudiant);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        // if EclipseLink cache enable -->
        // GestionFactory.factory.getCache().evictAll();
    }

    public static void delete(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        //
        em.createQuery("DELETE FROM Etudiant AS e WHERE e.id = :id")
                .setParameter("id", id)
                .executeUpdate();

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        // if EclipseLink cache enable -->
        // GestionFactory.factory.getCache().evictAll();
    }

    public static int removeAll() {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // RemoveAll
        int deletedCount = em.createQuery("DELETE FROM Etudiant").executeUpdate();

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return deletedCount;
    }

    // Retourne l'ensemble des etudiants
    public static List<Etudiant> getAll() {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        em.getTransaction().begin();

        // Recherche 
        Query q = em.createQuery("SELECT e FROM Etudiant e ORDER BY e.id DESC");

        @SuppressWarnings("unchecked")
        List<Etudiant> listEtudiant = q.getResultList();

        em.close();

        return listEtudiant;
    }


    public static List<Etudiant> retrieveByGroupe(Groupe groupe) {
        EntityManager em = GestionFactory.factory.createEntityManager();

        Query q = em.createQuery("SELECT e FROM Etudiant e WHERE e.groupe = :groupe", Etudiant.class);
        return (List<Etudiant>) q.setParameter("groupe", groupe).getResultList();

    }

    public static List<Etudiant> getEtudiantsWithoutGroupe() {
        EntityManager em = GestionFactory.factory.createEntityManager();


        Query q = em.createQuery("SELECT e FROM Etudiant e WHERE e.groupe IS NULL", Etudiant.class);
        return (List<Etudiant>) q.getResultList();

    }

}
