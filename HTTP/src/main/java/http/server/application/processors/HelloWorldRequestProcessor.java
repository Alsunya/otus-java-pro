package http.server.application.processors;

import http.server.HttpHeaders;
import http.server.HttpRequest;
import http.server.HttpStatus;
import http.server.processors.AcceptProcessor;
import http.server.processors.RequestProcessor;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class HelloWorldRequestProcessor implements RequestProcessor, AcceptProcessor {
    private static final String ACCEPT_HEADER = "text/html";

    @Override
    public void execute(HttpRequest httpRequest, OutputStream output) throws IOException {
        String response = String.format("HTTP/1.1 %s\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Set-Cookie: %s\r\n\r\n" +
                        "<html><body><h1>Hello World!!!</h1></body></html>",
                HttpStatus.OK, httpRequest.getHeader(HttpHeaders.SET_COOKIE.getHeader()));
        output.write(response.getBytes(StandardCharsets.UTF_8));
        output.flush();
    }

    @Override
    public String getAccept() {
        return ACCEPT_HEADER;
    }
}
