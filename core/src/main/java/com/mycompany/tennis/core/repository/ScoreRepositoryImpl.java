package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.entity.Tournoi;
import org.hibernate.Session;

import javax.sql.DataSource;
import java.sql.*;

public class ScoreRepositoryImpl {

    public void create(Score score) {

        Connection conn = null;

        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement preparedStatement = conn.prepareStatement("insert into score_vainqueur (id_match, set_1, set_2, set_3, set_4, set_5) values (?,?,?,?,?,?) ", new String[] { "ID_MATCH" });

            preparedStatement.setLong(1,score.getMatch().getId());
            preparedStatement.setByte(2,score.getSet1());
            preparedStatement.setByte(3,score.getSet2());

            if (score.getSet4()==null) {
                preparedStatement.setNull(5, Types.TINYINT);
            } else {
                preparedStatement.setByte(5,score.getSet5());
            }


            preparedStatement.setByte(4,score.getSet3());

            if (score.getSet4()==null) {
                preparedStatement.setNull(5, Types.TINYINT);
            } else {
                preparedStatement.setByte(5,score.getSet5());
            }

            if (score.getSet5()==null) {
                preparedStatement.setNull(6, Types.TINYINT);
            } else {
                preparedStatement.setByte(6,score.getSet5());
            }

            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();

            if(rs.next()) {

                score.setId(rs.getLong(1));

            }

            conn.commit();
            System.out.println("Score inséré");

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

    public Score getById(Long id) {

        Session session = null;
        Score score = null;
        System.out.println("Score lu");

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            score = session.get(Score.class, id);

        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {

            if (session != null) {
                session.close();
            }
        }
        return score;
    }

}
