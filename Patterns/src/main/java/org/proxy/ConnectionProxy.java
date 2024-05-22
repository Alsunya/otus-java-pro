package org.proxy;

import java.sql.SQLException;


public interface ConnectionProxy {
    void commit() throws SQLException;
    void rollback() throws SQLException;
    void setAutoCommit(boolean autoCommit) throws SQLException;
    void createStatement() throws SQLException;
}
