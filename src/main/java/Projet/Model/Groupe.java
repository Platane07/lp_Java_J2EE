package Projet.Model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity implementation class for Entity: Groupe
 */
@Entity
public class Groupe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY )
    private Integer id;

    @Column(unique = false, nullable = false)
    private String nom;

    @OneToMany(mappedBy = "groupe", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})    // LAZY = fetch when needed, EAGER = fetch immediately
    private List<Etudiant> etudiants;

    @ManyToMany(mappedBy = "groupes", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private final List<Module> modules = new ArrayList<>();

    public Groupe() {
        super();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom.toUpperCase();
    }

    public List<Etudiant> getEtudiants() {
        return this.etudiants;
    }


	public List<Module> getModules() {
		return modules;
	}

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public void addModule(Module module) {
			this.modules.add(module);
			module.getGroupes().add(this);
	}

	public void removeModule(Module module){
        this.modules.remove(module);
        module.getGroupes().remove(this);
    }
    public void removeEtudiant(Etudiant etudiant){
        this.etudiants.remove(etudiant);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Groupe)) return false;
        return id != null && id.equals(((Groupe) o).id);
    }

    @Override
    public int hashCode() {
        return id;
    }
}
