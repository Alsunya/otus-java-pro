package org.proxy;

import java.sql.Connection;

public class ConnectionProxyFactory {
    public static ConnectionProxy createProxy(Connection connection, boolean autoCommit) {
        return new ConnectionProxyImpl(connection, autoCommit);
    }
}
