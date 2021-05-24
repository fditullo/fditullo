package com.mycompany.tennis.controller;


//import com.mycompany.tennis.controller.JoueurController;
//import com.mycompany.tennis.controller.TournoiController;


import java.sql.SQLException;

public class UI {

    public static void main(String[] args) throws SQLException {

        //JoueurController controller = new JoueurController();
        //controller.deleteJoueur();
        //controller.renameJoueur();

        //controller.changeSexeJoueur();
        //controller.afficheDetailJoueur();
        //controller.creerJoueur();

        //TournoiController tournoiController= new TournoiController();
        //tournoiController.deleteTournoi();
        //tournoiController.createTournoi();
        //tournoiController.afficheDetailTournoi();

        //ScoreController controller = new ScoreController();
        //controller.afficheDetailScore();

        MatchController controller = new MatchController();
        //controller.afficheDerniereEpreuve();
        //controller.afficheRolandGarros();
        controller.afficheDetailMatch();

    }
}

