package com.mycompany.tennis.controller;
import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.service.TournoiService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;

public class TournoiController {

    private TournoiService tournoiService;

    public TournoiController() {
        this.tournoiService = new TournoiService();
    }

    public void afficheDetailTournoi(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant du tournoi dont vous voulez afficher les informations ?");
        Long identifiant = scanner.nextLong();
        TournoiDto tournoi = tournoiService.getTournoi(identifiant);
        System.out.println("Le tournoi selectionne s'appelle "+tournoi.getNom()+" "+tournoi.getCode());
    }

    public void createTournoi() throws SQLException {


        Scanner scanner = new Scanner(System.in);

        System.out.println("Donnez le nom du tournoi :");
        String nom = scanner.nextLine().toString();

        System.out.println("Donnez le code du tournoi :");
        String code = scanner.nextLine().toString();

        TournoiDto tournoi = new TournoiDto();
        tournoi.setNom(nom);
        tournoi.setCode(code);

        tournoiService.createTournoi(tournoi);
        System.out.println("Le tournoi a été créé, son idéntifiant est "+tournoi.getId());

    }

    public void deleteTournoi() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant du tournoi que vous voulez supprimer ?");
        Long identifiant = scanner.nextLong();
        scanner.nextLine();
        tournoiService.deleteTournoi(identifiant);
    }
}


/*
 public void creerJoueur() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est le nom du joueur ?");
        String nom=scanner.nextLine();
        System.out.println("Quel est le prénom du joueur ?");
        String prenom=scanner.nextLine();
        System.out.println("Quel est le sexe du joueur ?");
        char sexe=scanner.nextLine().charAt(0);
        Joueur joueur=new Joueur();
        joueur.setNom(nom);
        joueur.setPrenom(prenom);
        joueur.setSexe(sexe);
        joueurService.createJoueur(joueur);
        System.out.println("Le joueur a été créé, son idéntifiant est "+joueur.getId());
 */