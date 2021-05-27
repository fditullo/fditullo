package com.mycompany.tennis.core.dao;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Score;

import javax.sql.DataSource;
import java.sql.*;

public class MatchDaoImpl {

    public void createMatchWithScore(Match match) {

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

            PreparedStatement preparedStatement2 = conn.prepareStatement("insert into score_vainqueur (id_match, set_1, set_2, set_3, set_4, set_5) values (?,?,?,?,?,?) ", new String[] { "ID_MATCH" });

            preparedStatement2.setLong(1,match.getScore().getMatch().getId());
            preparedStatement2.setByte(2,match.getScore().getSet1());
            preparedStatement2.setByte(3,match.getScore().getSet2());

            if (match.getScore().getSet4()==null) {
                preparedStatement2.setNull(5, Types.TINYINT);
            } else {
                preparedStatement2.setByte(5,match.getScore().getSet5());
            }


            preparedStatement2.setByte(4,match.getScore().getSet3());

            if (match.getScore().getSet4()==null) {
                preparedStatement2.setNull(5, Types.TINYINT);
            } else {
                preparedStatement2.setByte(5,match.getScore().getSet5());
            }

            if (match.getScore().getSet5()==null) {
                preparedStatement2.setNull(6, Types.TINYINT);
            } else {
                preparedStatement2.setByte(6,match.getScore().getSet5());
            }


            preparedStatement2.execute();

            ResultSet rs2 = preparedStatement2.getGeneratedKeys();

            if(rs2.next()) {

                match.getScore().setId(rs2.getLong(1));

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

}

