package proxy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.proxy.ConnectionProxy;
import org.proxy.ConnectionProxyFactory;

import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ConnectionProxyTest {

    @Mock
    private Connection connection;

    @Test
    public void commitTest() throws SQLException {
        doNothing().when(connection).setAutoCommit(false);
        doNothing().when(connection).commit();

        ConnectionProxy proxyConnection = ConnectionProxyFactory.createProxy(connection, false);

        proxyConnection.commit();

        verify(connection).setAutoCommit(false);
        verify(connection).commit();
    }

    @Test
    public void rollbackTest() throws SQLException {
        ConnectionProxy proxyConnection = ConnectionProxyFactory.createProxy(connection, false);

        proxyConnection.rollback();

        verify(connection).rollback();
    }

    @Test
    public void testSetAutoCommit() throws SQLException {
        doNothing().when(connection).setAutoCommit(anyBoolean());

        ConnectionProxy proxyConnection = ConnectionProxyFactory.createProxy(connection, false);

        proxyConnection.setAutoCommit(true);

        verify(connection).setAutoCommit(true);
    }


    @Test
    public void createStatementTest() throws SQLException {
        ConnectionProxy proxyConnection = ConnectionProxyFactory.createProxy(connection, true);

        proxyConnection.createStatement();

        verify(connection).createStatement();
    }
}
