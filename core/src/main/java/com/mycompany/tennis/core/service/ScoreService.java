package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.*;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ScoreService {

    private ScoreRepositoryImpl scoreRepository;

    public ScoreService() {

        this.scoreRepository = new ScoreRepositoryImpl();

    }


    public ScoreFullDto getScore(Long id){

        Session session = null;
        Transaction tx = null;
        Score score = null;
        ScoreFullDto dto= null;

        try {

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            dto=new ScoreFullDto();

            score =  scoreRepository.getById(id);

            dto.setId(score.getId());

            dto.setMatch(score.getMatch());
            dto.setSet1(score.getSet1());
            dto.setSet2(score.getSet2());
            dto.setSet3(score.getSet3());
            dto.setSet4(score.getSet4());
            dto.setSet5(score.getSet5());

            tx.commit();

        } catch(Exception e) {
            if(tx!=null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {

            if (session != null) {
                session.close();
            }
        }
        return dto;
    }

}

