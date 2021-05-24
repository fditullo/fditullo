package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Score;
import org.hibernate.Session;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MatchRepositoryImpl {

    public void create(Match match) {

        Connection conn = null;

        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement preparedStatement = conn.prepareStatement("insert into match_tennis (id_epreuve,id_vainqueur ,id_finaliste) values (?,?,?) ", new String[] { "ID" });

            System.out.println(match.getEpreuve().getId()+" "+match.getVainqueur().getId()+" "+match.getFinaliste().getId());

            preparedStatement.setLong(1,match.getEpreuve().getId());
            preparedStatement.setLong(2,match.getVainqueur().getId());
            preparedStatement.setLong(3,match.getFinaliste().getId());

            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();

            if(rs.next()) {
                match.setId(rs.getLong(1));
            }

            conn.commit();
            System.out.println("Match créé");

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

    public Match getById(Long id) {

        Session session = null;
        Match match = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            match = session.get(Match.class, id);
            System.out.println("Match lu");

        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {

            if (session != null) {
                session.close();
            }
        }
        return match;
    }

}
