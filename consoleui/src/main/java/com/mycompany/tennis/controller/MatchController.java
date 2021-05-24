package com.mycompany.tennis.controller;
import com.mycompany.tennis.core.dto.MatchDto;
import com.mycompany.tennis.core.service.MatchService;
import com.mycompany.tennis.core.service.ScoreService;

import java.util.Scanner;

import static java.lang.Math.abs;

public class MatchController {

    private MatchService matchService;
    private ScoreService scoreService;

    public MatchController() {
        this.matchService = new MatchService();
    }

    public void afficheDetailMatch(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant du match dont vous voulez afficher les informations ?");
        Long idMatch = scanner.nextLong();
        scanner.nextLine();

        //Score score = scoreService.getScore(idScore);
        MatchDto match = matchService.getMatch(idMatch);


        System.out.println("Vainqueur :"+match.getVainqueur().getPrenom()+" "+match.getVainqueur().getNom());
        System.out.println("Finaliste :"+match.getFinaliste().getPrenom()+" "+match.getFinaliste().getNom());


        /*System.out.println("Premier set :"+(6-abs(score.getSet1()))+" "+abs(score.getSet1()));
        System.out.println("Deuxième set :"+(6-abs(score.getSet2()))+" "+abs(score.getSet2()));
        if(score.getSet3() != null){
            System.out.println("Troisième set :"+(6-abs(score.getSet3()))+" "+abs(score.getSet3()));
        }
        if(score.getSet4() != null) {
            System.out.println("Quatrième set :" + (6 - abs(score.getSet4())) + " " + abs(score.getSet4()));
        }
        if(score.getSet4() != null) {
            System.out.println("Cinquième set :" + (6 - abs(score.getSet5())) + " " + abs(score.getSet5()));
        }
        */

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