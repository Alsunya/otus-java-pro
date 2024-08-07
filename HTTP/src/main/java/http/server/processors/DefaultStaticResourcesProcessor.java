package http.server.processors;

import http.server.HttpHeaders;
import http.server.HttpRequest;
import http.server.HttpStatus;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DefaultStaticResourcesProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest httpRequest, OutputStream output) throws IOException {
        String filename = httpRequest.getUri().substring(1);
        Path filePath = Paths.get("static/", filename);

        long lastModified = Files.getLastModifiedTime(filePath).toMillis();
        String etag = Integer.toHexString((filename + lastModified).hashCode());

        String ifNoneMatch = httpRequest.getHeader(HttpHeaders.IF_NONE_MATCH.getHeader());
        String ifModifiedSince = httpRequest.getHeader(HttpHeaders.IF_MODIFIED_SINCE.getHeader());

        if (etag.equals(ifNoneMatch) || (ifModifiedSince != null && lastModified <= parseHttpDate(ifModifiedSince))) {
            String response = String.format("HTTP/1.1 %s\r\n" +
                            "%s: %s\r\n" +
                            "%s: %s\r\n\r\n",
                    HttpStatus.NOT_MODIFIED, HttpHeaders.ETAG.getHeader(), etag,
                    HttpHeaders.LAST_MODIFIED.getHeader(), formatHttpDate(lastModified));
            output.write(response.getBytes());
            return;
        }

        String fileType = filename.substring(filename.lastIndexOf(".") + 1);
        byte[] fileData = Files.readAllBytes(filePath);

        String contentDisposition = "";
        if (fileType.equals("pdf")) {
            contentDisposition = String.format("%s: attachment;filename=%s\r\n",
                    HttpHeaders.CONTENT_DISPOSITION, filename);
        }

        String response = String.format("HTTP/1.1 %s\r\n" +
                        "%s: %d\r\n" +
                        "%s: %s\r\n" +
                        "%s: %s\r\n" +
                        "%s\r\n\r\n",
                HttpStatus.OK,
                HttpHeaders.CONTENT_LENGTH,
                fileData.length,
                HttpHeaders.ETAG,
                etag,
                HttpHeaders.LAST_MODIFIED,
                formatHttpDate(lastModified),
                contentDisposition);

        output.write(response.getBytes());
        output.write(fileData);
    }

    private static String formatHttpDate(long millis) {
        return DateTimeFormatter.RFC_1123_DATE_TIME.format(
                Instant.ofEpochMilli(millis).atZone(ZoneId.of("GMT"))
        );
    }

    private static long parseHttpDate(String date) {
        try {
            return Instant.from(DateTimeFormatter.RFC_1123_DATE_TIME.parse(date)).toEpochMilli();
        } catch (DateTimeParseException e) {
            return 0;
        }
    }
}
