package Projet.Model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Module implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nom;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "module_groupe",
            joinColumns = @JoinColumn(name = "module_id"),
            inverseJoinColumns = @JoinColumn(name = "groupe_id")
    )
    private final List<Groupe> groupes = new ArrayList<>();

    @OneToMany(mappedBy = "module", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final List<Note> notes = new ArrayList<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Groupe> getGroupes() {
        return groupes;
    }

    public void addGroupe(Groupe groupe) {
        this.groupes.add(groupe);
        groupe.getModules().add(this);
    }

    public void removeGroupe(Groupe groupe) {
        this.groupes.remove(groupe);
        groupe.getModules().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Module)) return false;
        return id != null && id.equals(((Module) o).id);
    }

    @Override
    public int hashCode() {
        return id;
    }

    public List<Note> getNotes() {
        return this.notes;
    }
}
