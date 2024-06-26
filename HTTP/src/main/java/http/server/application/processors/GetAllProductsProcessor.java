package http.server.application.processors;

import com.google.gson.Gson;
import http.server.HttpHeaders;
import http.server.HttpRequest;
import http.server.HttpStatus;
import http.server.application.Item;
import http.server.application.Storage;
import http.server.processors.AcceptProcessor;
import http.server.processors.RequestProcessor;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class GetAllProductsProcessor implements RequestProcessor, AcceptProcessor {
    private static final String acceptHeader = "application/json";

    @Override
    public void execute(HttpRequest httpRequest, OutputStream output) throws IOException {
        List<Item> items = Storage.getItems();
        Gson gson = new Gson();
        String result = String.format("HTTP/1.1 %s\r\n" +
                        "Content-Type: application/json\r\n" +
                        "Set-Cookie: %s" +
                        "Connection: keep-alive\r\n" +
                        "Access-Control-Allow-Origin: *\r\n\r\n" +
                        "\r\n%s",
                HttpStatus.OK, httpRequest.getHeader(HttpHeaders.SET_COOKIE.getHeader()), gson.toJson(items));
        output.write(result.getBytes(StandardCharsets.UTF_8));
        output.flush();
    }

    @Override
    public String getAccept() {
        return acceptHeader;
    }
}
