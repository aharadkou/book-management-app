package com.epam.training.helpers.db;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public final class ConnectionManager {

    private static final Logger LOGGER =
            LogManager.getLogger(ConnectionManager.class);

    private static final SQLiteDataSource DATA_SOURCE = new SQLiteDataSource();

    private ConnectionManager() { }

    /**
     * Creates connection string from path of DB file.
     * @param path path to DB file
     */
    public static void initDatabaseUrl(final String path) {
        final String urlHead = "jdbc:sqlite:";
        DATA_SOURCE.setUrl(urlHead + path);
    }

    /**
     * Gets connection instance from SQLiteDataSource.
     * @return connection instance
     * @throws SQLException if problem with establishing connection occured
     */
    public static Connection getConnection() throws SQLException {
        try {
            return DATA_SOURCE.getConnection();
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw ex;
        }
    }
}
