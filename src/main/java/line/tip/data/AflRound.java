package line.tip.data;

import java.util.List;

public class AflRound {
    private Integer round;
    private List<AflGame> games;

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public List<AflGame> getGames() {
        return games;
    }

    public void setGames(List<AflGame> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return "AflRound{" +
                "round=" + round +
                ", games=" + games +
                '}';
    }
}
