package com.mycompany.tennis.core.dto;


import com.mycompany.tennis.core.entity.Tournoi;

import javax.persistence.Column;


public class EpreuveFullDto {

    private Long id;
    private Short annee;
    private TournoiDto tournoi;
    private Character typeEpreuve;

    public Long getId() {
        return id;
    }

    public Short getAnnee() {
        return annee;
    }

    public TournoiDto getTournoi() {
        return tournoi;
    }

    public void setTournoi(TournoiDto tournoi) {
        this.tournoi = tournoi;
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
