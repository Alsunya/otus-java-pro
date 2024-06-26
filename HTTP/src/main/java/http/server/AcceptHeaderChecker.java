package http.server;


import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class AcceptHeaderChecker {
    public static boolean checkAccept(HttpRequest httpRequest, OutputStream output, String header) throws IOException {
        var accept = httpRequest.getHeader(HttpHeaders.ACCEPT.getHeader());
        if (!accept.contains(header)) {
            String response = String.format("HTTP/1.1 %s\r\nContent-Type: %s\r\n\r\n", HttpStatus.NOT_ACCEPTABLE, header);
            output.write(response.getBytes(StandardCharsets.UTF_8));
            return false;
        }
        return true;
    }
}
