package com.mycompany.tennis.core;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class DataSourceProvider {

    private static BasicDataSource singleDataSource;

    public static DataSource getSingleDataSourceInstance() {

        if (singleDataSource == null) {

            singleDataSource = new BasicDataSource();

            singleDataSource.setInitialSize(5);
            singleDataSource.setUrl("jdbc:oracle:thin:@localhost:1521/devlpdb");
            singleDataSource.setUsername ("tennis");
            singleDataSource.setPassword("tennis");
        }

        return singleDataSource;

    }

}
