package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import com.mycompany.tennis.core.service.JoueurService;
import com.mycompany.tennis.core.service.MatchService;


import javax.sql.DataSource;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

public class Cours {
        public static void main(String... args) throws SQLException {

           MatchService matchService=new MatchService();
           Match match = new Match();
           Score score = new Score();

           score.setSet1((byte)3);
           score.setSet2((byte)4);
           score.setSet3((byte)6);

           match.setScore(score);
           score.setMatch(match);

           Joueur federer = new Joueur();
           federer.setId(75L);
           Joueur murray = new Joueur();
           murray.setId(77L);

           match.setVainqueur(federer);
           match.setFinaliste(murray);

           Epreuve epreuve = new Epreuve();
           epreuve.setId(119L);

           match.setEpreuve(epreuve);

           matchService.enregistrerNouveauMatch(match);

            System.out.println("L'identifiant de match est "+match.getId());

        }
    }



