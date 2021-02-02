package Projet.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Note implements Serializable {

    @Column(nullable = false)
    private int valeur;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private Etudiant etudiant;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private Module module;


    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
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
