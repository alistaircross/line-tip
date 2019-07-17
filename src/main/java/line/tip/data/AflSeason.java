package line.tip.data;

import java.util.List;

public class AflSeason {
    private Integer year;
    private List<AflRound> rounds;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<AflRound> getRounds() {
        return rounds;
    }

    public void setRounds(List<AflRound> rounds) {
        this.rounds = rounds;
    }
}
