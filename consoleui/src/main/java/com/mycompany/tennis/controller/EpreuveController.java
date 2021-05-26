package com.mycompany.tennis.controller;
import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.EpreuveLightDto;
import com.mycompany.tennis.core.service.EpreuveService;

import java.util.Scanner;

public class EpreuveController {

    private EpreuveService epreuveService;

    public EpreuveController() {
        this.epreuveService = new EpreuveService();
    }

    public void afficheDerniereEpreuve(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant de l'épreuve dont vous voulez afficher les informations ?");
        Long identifiant = scanner.nextLong();
        EpreuveFullDto epreuve = epreuveService.getEpreuveAvecTournoi(identifiant);
        System.out.println("Le nom du tournoi est "+epreuve.getTournoi().getNom());

    }

    public void afficheRolandGarros(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant de l'épreuve dont vous voulez afficher les informations ?");
        Long identifiant = scanner.nextLong();
        EpreuveLightDto epreuve = epreuveService.getEpreuveSansTournoi(identifiant);

    }


}


