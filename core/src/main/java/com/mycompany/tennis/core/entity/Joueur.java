package com.mycompany.tennis.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Joueur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String nom;
    private String prenom;
    private Character sexe;

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Joueur)) return false;
        Joueur joueur = (Joueur) o;
        return Objects.equals(id, joueur.id) &&
                Objects.equals(nom, joueur.nom) &&
                Objects.equals(nom, joueur.prenom) &&
                Objects.equals(nom, joueur.sexe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, prenom, sexe);
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Character getSexe() {
        return sexe;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setSexe(Character sexe) {
        this.sexe = sexe;
    }
}
