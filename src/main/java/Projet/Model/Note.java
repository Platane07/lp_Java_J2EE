package Projet.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Note implements Serializable {

    @Column(nullable = true)
    private float valeur;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private Etudiant etudiant;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private Module module;


    public float getValeur() {
        return valeur;
    }

    public void setValeur(float valeur) {
        this.valeur = valeur;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }
}
