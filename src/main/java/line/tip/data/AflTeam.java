package line.tip.data;

public class AflTeam {
    private String name;
    private Double odds;
    private Double line;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getOdds() {
        return odds;
    }

    public void setOdds(Double odds) {
        this.odds = odds;
    }

    public Double getLine() {
        return line;
    }

    public void setLine(Double line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return "AflTeam{" +
                "name='" + name + '\'' +
                ", odds=" + odds +
                ", line=" + line +
                '}';
    }
}
