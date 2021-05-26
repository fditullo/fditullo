package com.mycompany.tennis.core.service;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.dto.MatchDto;
import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.repository.MatchRepositoryImpl;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MatchService {

    private ScoreRepositoryImpl scoreRepository;
    private MatchRepositoryImpl matchRepository;

            public MatchService(){

                this.scoreRepository = new ScoreRepositoryImpl();
                this.matchRepository = new MatchRepositoryImpl();

            }

            public void enregistrerNouveauMatch(Match match) {

                matchRepository.create(match);
                scoreRepository.create(match.getScore());

            }

            public MatchDto getMatch(Long id) {

                Session session = null;
                Transaction tx = null;
                Match match = null;
                MatchDto dto= null;
                JoueurDto joueurDto = null;
                try {

                    session = HibernateUtil.getSessionFactory().getCurrentSession();
                    tx = session.beginTransaction();
                    dto=new MatchDto();

                    match =  matchRepository.getById(id);

                    dto.setId(match.getId());

                    JoueurDto vainqueur = new JoueurDto();
                    JoueurDto finaliste = new JoueurDto();

                    finaliste.setId(match.getFinaliste().getId());
                    finaliste.setNom(match.getFinaliste().getNom());
                    finaliste.setPrenom(match.getFinaliste().getPrenom());
                    finaliste.setSexe(match.getFinaliste().getSexe());
                    dto.setFinaliste(finaliste);

                    vainqueur.setId(match.getVainqueur().getId());
                    vainqueur.setNom(match.getVainqueur().getNom());
                    vainqueur.setPrenom(match.getVainqueur().getPrenom());
                    vainqueur.setSexe(match.getVainqueur().getSexe());
                    dto.setVainqueur(vainqueur);

                    EpreuveFullDto epreuvedto = new EpreuveFullDto();
                    epreuvedto.setId(match.getEpreuve().getId());
                    epreuvedto.setAnnee(match.getEpreuve().getAnnee());
                    epreuvedto.setTypeEpreuve(match.getEpreuve().getTypeEpreuve());

                    TournoiDto tournoiDto = new TournoiDto();
                    tournoiDto.setId(match.getEpreuve().getTournoi().getId());
                    tournoiDto.setNom(match.getEpreuve().getTournoi().getNom());
                    tournoiDto.setCode(match.getEpreuve().getTournoi().getCode());
                    epreuvedto.setTournoi(tournoiDto);
                    dto.setEpreuve(epreuvedto);

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
