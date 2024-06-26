package http.server;

public enum HttpStatus {
    OK(200, "OK"),
    NO_CONTENT(204, "No Content"),
    NOT_ACCEPTABLE(406, "Not Acceptable"),
    NOT_MODIFIED(304, "Not Modified");
    private final int code;
    private final String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    HttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
