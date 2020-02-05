package com.bfm.training.jdbc;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:file:C:/data/sample";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";
    private static Connection conn;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if(conn == null || conn.isClosed()) {
            //STEP 2: Register JDBC driver
            Class.forName("org.h2.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//            conn.setAutoCommit(false);
        }
        return conn;
    }
    public static Connection getJdbcTemplate() throws SQLException {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:file:C:/data/sample");
        dataSourceBuilder.username("sa");
        dataSourceBuilder.password("");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourceBuilder.build());
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        return jdbcTemplate.getDataSource().getConnection();
    }
}
	
