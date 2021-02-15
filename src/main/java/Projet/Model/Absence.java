package Projet.Model;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Absence {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private Date début;

    @Column(nullable = true, columnDefinition = "TIMESTAMP")
    private Date fin;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean justifié;

    @ManyToOne
    private Etudiant etudiant;

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public boolean isJustifié() {
        return justifié;
    }

    public void setJustifié(boolean justifié) {
        this.justifié = justifié;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public Date getDébut() {
        return début;
    }

    public void setDébut(Date début) {
        this.début = début;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
