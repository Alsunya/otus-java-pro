package http.server.application.processors;

import http.server.HttpHeaders;
import http.server.HttpRequest;
import http.server.HttpStatus;
import http.server.processors.AcceptProcessor;
import http.server.processors.RequestProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class CalculatorRequestProcessor implements RequestProcessor, AcceptProcessor {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorRequestProcessor.class);
    private static final String ACCEPT_HEADER = "text/html";

    @Override
    public void execute(HttpRequest httpRequest, OutputStream output) throws IOException {
        try {
            int a = Integer.parseInt(httpRequest.getParameter("a"));
            int b = Integer.parseInt(httpRequest.getParameter("b"));
            int result = a + b;
            String outMessage = a + " + " + b + " = " + result;

            String response = String.format("HTTP/1.1 %s\r\n" +
                            "Content-Type: text/html\r\n" +
                            "Set-Cookie: %s\r\n\r\n" +
                            "<html><body><h1>%s</h1></body></html>",
                    HttpStatus.OK, httpRequest.getHeader(HttpHeaders.SET_COOKIE.getHeader()), outMessage);
            output.write(response.getBytes(StandardCharsets.UTF_8));
        } catch (NumberFormatException e) {
            logger.error("Неверный формат входных данных");
        }

    }

    @Override
    public String getAccept() {
        return ACCEPT_HEADER;
    }
}
