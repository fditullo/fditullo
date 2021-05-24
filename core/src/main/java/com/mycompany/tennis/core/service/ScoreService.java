package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;


public class ScoreService {

    private ScoreRepositoryImpl scoreRepository;

    public ScoreService() {

        this.scoreRepository = new ScoreRepositoryImpl();

    }


    public Score getScore(Long id){

        Score score = scoreRepository.getById(id);
        return score;
    }


}
