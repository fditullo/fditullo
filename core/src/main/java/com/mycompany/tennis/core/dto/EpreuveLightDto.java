package com.mycompany.tennis.core.dto;


import com.mycompany.tennis.core.entity.Tournoi;

import javax.persistence.Column;


public class EpreuveLightDto {

    private Long id;
    private Short annee;
     @Column(name="TYPE_EPREUVE")
    private Character typeEpreuve;

    public Long getId() {
        return id;
    }

    public Short getAnnee() {
        return annee;
    }

    public Character getTypeEpreuve() {
        return typeEpreuve;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAnnee(Short annee) {
        this.annee = annee;
    }

    public void setTypeEpreuve(Character typeEpreuve) {
        this.typeEpreuve = typeEpreuve;
    }


}
