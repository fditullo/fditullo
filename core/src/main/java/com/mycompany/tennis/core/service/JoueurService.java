package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.sql.SQLException;

public class JoueurService {

    private JoueurRepositoryImpl joueurRepository;

    public JoueurService() {this.joueurRepository = new JoueurRepositoryImpl();}

    public void createJoueur(Joueur joueur) throws SQLException {

        Session session = null;
        Transaction tx = null;
        try {

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            joueurRepository.create(joueur);

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
    }

    public Joueur getJoueur(Long id) throws SQLException {

        Session session = null;
        Transaction tx = null;
        Joueur joueur = null;
        try {

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueur = joueurRepository.getById(id);
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
       return joueur;
    }

    public void rename(Long id, String nouveauNom) throws SQLException {

        Joueur joueur = getJoueur(id);

        Session session = null;
        Transaction tx = null;

        try {

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            joueur.setNom(nouveauNom);
            Joueur joueur2 = (Joueur)session.merge(joueur);
            tx.commit();

        }catch(Exception e) {
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
    }

    public void changeSex(Long id, Character nouveauSexe) throws SQLException {

        Joueur joueur = getJoueur(id);

        Session session = null;
        Transaction tx = null;

        try {

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            joueur.setSexe(nouveauSexe);
            Joueur joueur2 = (Joueur)session.merge(joueur);
            tx.commit();

        }catch(Exception e) {
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
    }

    public void deleteJoueur(Long id) {

        Session session = null;
        Transaction tx = null;

        try {

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueurRepository.delete(id);
            tx.commit();

        }catch(Exception e) {
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
    }
}
