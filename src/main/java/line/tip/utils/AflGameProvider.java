package line.tip.utils;

public enum AflGameProvider {
    LADBROKES("https://www.ladbrokes.com.au/sports/australian-rules/");

    private String url;

    AflGameProvider(final String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
