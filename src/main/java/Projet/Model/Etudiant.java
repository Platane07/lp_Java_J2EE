package Projet.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Entity implementation class for Entity: Etudiant
 */
@Entity
public class Etudiant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String nom;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "integer", name = "groupe_id", nullable = true)
    private Groupe groupe;

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Absence> absences;

    @OneToMany(mappedBy = "etudiant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Note> notes;

    private static final long serialVersionUID = 1L;

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<Absence> getAbsences() {
        return absences;
    }

    public void setAbsences(List<Absence> absences) {
        this.absences = absences;
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Groupe getGroupe() {
        return this.groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
        if (!groupe.getEtudiants().contains(this)) {
            groupe.getEtudiants().add(this);
        }
    }

    public List<Note> getNotes() {
        return this.notes;
    }

    public float getMoyenne() {
        List<Note> notes = this.getNotes();

        float moyenne = 0;
        int ind = 0;

        for (Note note : notes) {
            moyenne = moyenne + note.getValeur();
            ind++;
        }
        return moyenne / ind;
    }

    public Note getNoteByModule(Module module) {
        List<Note> notes = this.getNotes();

        for (Note note : notes) {
            if (note.getModule() == module) {
                return note;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "[" + this.getId() + "] " + this.getPrenom() + " " + this.getNom();
    }
}
