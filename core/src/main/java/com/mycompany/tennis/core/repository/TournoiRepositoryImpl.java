package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Tournoi;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournoiRepositoryImpl {

    public void create(Tournoi tournoi) throws SQLException {
        Session session = null;
        Transaction tx=null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            tx=session.beginTransaction();
            session.persist(tournoi);
            tx.commit();

            System.out.println("Tournoi créé");


        } catch(Throwable t) {
            t.printStackTrace();
        }
        finally {

            if (session != null) {
                session.close();
            }
        }

    }

    /*
    public void create(Tournoi tournoi) {

        Connection conn = null;

        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement("insert into tournoi(nom,code) values (?,?) ", new String[] { "ID" });

            preparedStatement.setString(1,tournoi.getNom());
            preparedStatement.setString(2,tournoi.getCode());

            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();

            if(rs.next()) {

                tournoi.setId(rs.getLong(1));

            }

            conn.commit();
            System.out.println("Tournoi inséré");

        } catch (SQLException e) {
            e.printStackTrace();
            try{
                if (conn != null) conn.rollback();
            } catch(SQLException e1){
                e1.printStackTrace();
            }

        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    */

    public void update(Tournoi tournoi) {

        Connection conn = null;

        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement("update tournoi set nom = ?,code = ? where id = ? ");

            preparedStatement.setString(1,tournoi.getNom());
            preparedStatement.setString(2,tournoi.getCode());
            preparedStatement.setLong(4,tournoi.getId());

            preparedStatement.executeUpdate();

            conn.commit();
            System.out.println("Tournoi modifié");

        } catch (SQLException e) {
            e.printStackTrace();
            try{
                if (conn != null) conn.rollback();
            } catch(SQLException e1){
                e1.printStackTrace();
            }

        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void delete(Long id) throws SQLException {

        Tournoi tournoi = getById(id);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.delete(tournoi);
        System.out.println("Tournoi supprimé");

    }

    /*
    public void delete(Long id) {

        Connection conn = null;

        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement("delete from tournoi  where id = ? ");

            preparedStatement.setLong(1,id);

            preparedStatement.executeUpdate();

            conn.commit();
            System.out.println("Tournoi supprimé");

        } catch (SQLException e) {
            e.printStackTrace();
            try{
                if (conn != null) conn.rollback();
            } catch(SQLException e1){
                e1.printStackTrace();
            }

        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    */


    public Tournoi getById(Long id) {

        System.out.println("Tournoi lu");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Tournoi tournoi = session.get(Tournoi.class, id);
        return tournoi;
    }
/*
    public Tournoi getById(Long id) {

        Connection conn = null;
        Tournoi tournoi = null;

        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement preparedStatement = conn.prepareStatement("select nom , code from tournoi where id = ? ");

            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                tournoi = new Tournoi();
                tournoi = new Tournoi();
                tournoi.setId(id);
                tournoi.setNom(rs.getString("NOM"));
                tournoi.setCode(rs.getString("CODE"));
            }

            conn.commit();
            System.out.println("Tournoi lu");

        } catch (SQLException e) {
            e.printStackTrace();
            try{
                if (conn != null) conn.rollback();
            } catch(SQLException e1){
                e1.printStackTrace();
            }

        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tournoi;
    } */

    public List<Tournoi>  liste() {

        Connection conn = null;
        List<Tournoi> tournois = new ArrayList<>();

        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement("select id, nom , code from tournoi ");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Tournoi tournoi = new Tournoi();
                tournoi = new Tournoi();
                tournoi.setId(rs.getLong("ID"));
                tournoi.setNom(rs.getString("NOM"));
                tournoi.setCode(rs.getString("CODE"));
                tournois.add(tournoi);

            }

            conn.commit();
            System.out.println("Tournoi lu");

        } catch (SQLException e) {
            e.printStackTrace();
            try{
                if (conn != null) conn.rollback();
            } catch(SQLException e1){
                e1.printStackTrace();
            }

        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tournois;
    }
}
