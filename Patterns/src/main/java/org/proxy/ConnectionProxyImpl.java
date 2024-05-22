package org.proxy;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * ТЗ: Реализуйте управление JDBC транзакциями через паттерн Proxy
 */
public class ConnectionProxyImpl implements ConnectionProxy {
    private final Connection connection;
    private boolean autoCommit;

    public ConnectionProxyImpl(Connection connection, boolean autoCommit) {
        this.connection = connection;
        this.autoCommit = autoCommit;
    }


    @Override
    public void commit() throws SQLException {
        if (autoCommit) {
            throw new SQLException("Can't commit when autoCommit is enabled");
        }
        connection.setAutoCommit(false);
        try {
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            if (!autoCommit) {
                connection.setAutoCommit(true);
            }
        }
    }

    @Override
    public void rollback() throws SQLException {
        connection.rollback();
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        this.autoCommit = autoCommit;
        connection.setAutoCommit(autoCommit);
    }

    @Override
    public void createStatement() throws SQLException {
        connection.createStatement();
    }
}
