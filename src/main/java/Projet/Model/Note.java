package Projet.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Note implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true, nullable = false)
    private int valeur;

    @ManyToOne
    private Etudiant etudiant;

    @ManyToOne
    private Module module;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValeur() {
        return valeur;
    }

    public void setValeur(Integer valeur) {
        this.valeur = valeur;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
        if (!module.getNotes().contains(module)) {
            module.getNotes().add(this);
        }
    }

    public Etudiant getEtudiant (){
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant){
        this.etudiant = etudiant;
        if (!etudiant.getNotes().contains(module)) {
            module.getNotes().add(this);
        }
    }

    @Override
    public int hashCode() {
        return id;
    }

}
