package http.server;

import http.server.application.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private int port;
    private Dispatcher dispatcher;
    private ThreadLocal<byte[]> requestBuffer;

    private static final Logger logger = LoggerFactory.getLogger(HttpServer.class.getName());

    private static final int DEFAULT_BUFFER_SIZE = 8192;

    public HttpServer(int port) {
        this.port = port;
    }

    public void start() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        //requestBuffer = new ThreadLocal<>();
        requestBuffer = ThreadLocal.withInitial(() -> new byte[DEFAULT_BUFFER_SIZE]);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("Сервер запущен на порту: {}", port);
            this.dispatcher = new Dispatcher();
            Storage.init();
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(() -> executeRequest(socket));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    private void executeRequest(Socket socket) {
        try {
            byte[] buffer = requestBuffer.get();
            int n = socket.getInputStream().read(buffer);
            if (n > 0) {
                String rawRequest = new String(buffer, 0, n);
                HttpRequest request = new HttpRequest(rawRequest);
                dispatcher.execute(request, socket.getOutputStream());
                socket.getOutputStream().flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
