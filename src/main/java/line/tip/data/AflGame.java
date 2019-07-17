package line.tip.data;

public class AflGame {
    private AflTeam homeTeam;
    private AflTeam awayTeam;

    public AflTeam getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(AflTeam homeTeam) {
        this.homeTeam = homeTeam;
    }

    public AflTeam getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(AflTeam awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getVsString() {
        return homeTeam.getName() + " vs " + awayTeam.getName();
    }

    @Override
    public String toString() {
        return "AflGame{" +
                "homeTeam=" + homeTeam +
                ", awayTeam=" + awayTeam +
                '}';
    }
}
