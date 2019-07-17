package line.tip.utils;

public enum RequestError {
    NO_ROUND("The round you requested has no data!"),
    UNKNOWN_ERROR("¯\\_(ツ)_/¯");

    private String message;

    RequestError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
