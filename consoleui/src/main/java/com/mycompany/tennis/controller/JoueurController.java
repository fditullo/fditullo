package com.mycompany.tennis.controller;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.service.JoueurService;

import java.sql.SQLException;
import java.util.Scanner;

public class JoueurController {

    private JoueurService joueurService;

    public JoueurController() {
        this.joueurService = new JoueurService();
    }

    public void afficheDetailJoueur() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant du joeur dont vous voulez afficher les informations ?");
        Long identifiant = scanner.nextLong();
        Joueur joueur = joueurService.getJoueur(identifiant);
        System.out.println("Le joueur séléctionné s'appelle "+joueur.getNom()+" "+joueur.getPrenom());
    }

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

    }

    public void renameJoueur() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant du joueur que vous voulez renommer ?");
        Long identifiant = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Quel est le nouveau nom ?");
        String nom=scanner.nextLine();
        joueurService.rename(identifiant, nom);
    }

    public void deleteJoueur() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant du joueur que vous voulez supprimer ?");
        Long identifiant = scanner.nextLong();
        scanner.nextLine();
        joueurService.deleteJoueur(identifiant);
    }


    public void changeSexeJoueur() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant du joueur dont vous voulez changer le sexe ?");
        Long identifiant = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Quel est le nouveau sexe ?");
        char sexe=scanner.nextLine().charAt(0);
        joueurService.changeSex(identifiant, sexe);
    }

}

