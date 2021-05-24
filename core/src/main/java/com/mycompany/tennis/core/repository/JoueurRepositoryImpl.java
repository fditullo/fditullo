package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurRepositoryImpl {

public void rename(Long id, String nouveauNom) {

        Session session = null;
        Joueur joueur = null;
        Transaction tx = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            tx= session.beginTransaction();
            joueur = session.get(Joueur.class, id);
            joueur.setNom(nouveauNom);
            tx.commit();
            System.out.println("Nom du joueur modifié");
        }
        catch(Exception e) {
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


    public void create(Joueur joueur)  {

            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.persist(joueur);

            System.out.println("Joueur créé");

    }

    /*
    public void create(Joueur joueur) throws SQLException {
        Connection conn = null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            Statement statement = conn.createStatement();

            PreparedStatement preparedStatement = conn.prepareStatement("insert into joueur (nom, prenom, sexe) values (?,?,?)", new String[] { "ID" });
            preparedStatement.setString(1,joueur.getNom());
            preparedStatement.setString(2,joueur.getPrenom());
            preparedStatement.setString(3,joueur.getSexe().toString());

            ResultSet rs = preparedStatement.executeQuery();

            conn.commit();

            if (rs.next()) {

                rs.getLong(1);
                joueur.setId(rs.getBigDecimal(1));

            }

        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
  */


    /*
    public void update(Joueur joueur) throws SQLException {
        Connection conn = null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            Statement statement = conn.createStatement();

            PreparedStatement preparedStatement = conn.prepareStatement("update joueur set nom = ?, prenom = ?, sexe = ? where id = ?");
            preparedStatement.setString(1,joueur.getNom());
            preparedStatement.setString(2,joueur.getPrenom());
            preparedStatement.setString(3,joueur.getSexe().toString());
            preparedStatement.setLong(4,joueur.getId());

            preparedStatement.executeQuery();

            System.out.println("Joeur modifié");
            conn.commit();
        } catch (
                SQLException e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    */

    public void delete(Long id) throws SQLException {

        Joueur joueur = getById(id);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.delete(joueur);
        System.out.println("Joeur supprimé");

    }


    /*
    public void delete(Long id) throws SQLException {
        Connection conn = null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            Statement statement = conn.createStatement();

            PreparedStatement preparedStatement = conn.prepareStatement("delete from joueur  where id = ?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeQuery();

            System.out.println("Joeur supprimé");
            conn.commit();
        } catch (
                SQLException e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    */

    /*
    public Joueur getById(Long id) throws SQLException {
        Connection conn = null;
        Joueur joueur = null;
        try {

            BasicDataSource dataSource = new BasicDataSource();

            dataSource.setInitialSize(5);
            dataSource.setUrl("jdbc:oracle:thin:@localhost:1521/devlpdb");

            dataSource.setUsername("tennis");
            dataSource.setPassword("tennis");

            conn = dataSource.getConnection();

            Statement statement = conn.createStatement();

            PreparedStatement preparedStatement = conn.prepareStatement("select nom, prenom, sexe from joueur  where id = ?");

            preparedStatement.setLong(1,id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                joueur = new Joueur();
                joueur.setId(id);
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("SEXE").charAt(0));
            }
            System.out.println("Joueur recupéré");

        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return joueur;

    }
    */

    public Joueur getById(Long id) {


        Session session = null;
        Joueur joueur = null;

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        joueur = session.get(Joueur.class, id);
        System.out.println("Joueur lu");

        return joueur;
    }

    public List<Joueur> list() throws SQLException {
        Connection conn = null;
        List<Joueur> joueurs = new ArrayList<>();
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();
            Statement statement = conn.createStatement();

            PreparedStatement preparedStatement = conn.prepareStatement("select nom, prenom, sexe from joueur");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Joueur joueur = new Joueur();
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("SEXE").charAt(0));
                joueurs.add(joueur);
            }

            System.out.println("Joueurs lus");

        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return joueurs;
    }
}
