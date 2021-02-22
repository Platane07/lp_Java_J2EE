package Projet.Model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Absence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime début;

    @Column(nullable = true, columnDefinition = "TIMESTAMP")
    private LocalDateTime fin;

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

    public LocalDateTime getFin() {
        return fin;
    }

    public void setFin(LocalDateTime fin) {
        this.fin = fin;
    }

    public LocalDateTime getDébut() {
        return début;
    }

    public void setDébut(LocalDateTime début) {
        this.début = début;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
