package line.tip.extractor.ladbrokes;

import line.tip.data.AflGame;
import line.tip.data.AflRound;
import line.tip.data.AflTeam;
import line.tip.extractor.shared.Extractor;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static line.tip.utils.LadbrokesUtils.ROUND_STRING;

public class LadbrokesExtractor implements Extractor {

    public Map extractRoundsFromDocument(Document doc) {
        Map<Integer, AflRound> rounds = new HashMap<Integer, AflRound>();
        for (int i = 1; i < 30; i++) {
            Element roundElement = doc.getElementById(ROUND_STRING + i);
            if (roundElement != null) {
                AflRound round = extractRoundFromRoundElement(roundElement, i);
                rounds.put(i, round);
            }
        }
        return rounds;
    }

    private AflRound extractRoundFromRoundElement(Element roundElement, int roundNumber) {
        AflRound round = new AflRound();
        round.setRound(roundNumber);
        Elements sportsMarkets = roundElement.getElementsByClass("sports-market");
        ArrayList<AflGame> games = new ArrayList<AflGame>();
        for (Element market : sportsMarkets) {
            AflGame game = extractGameFromElement(market.getElementsByClass("market-row"));
            games.add(game);
        }
        round.setGames(games);
        return round;
    }

    private AflGame extractGameFromElement(Elements gameTeamsElements) {
        AflGame game = new AflGame();
        AflTeam homeTeam = extractTeamFromElement(gameTeamsElements.get(0));
        AflTeam awayTeam = extractTeamFromElement(gameTeamsElements.get(1));
        game.setAwayTeam(awayTeam);
        game.setHomeTeam(homeTeam);
        return game;
    }

    private AflTeam extractTeamFromElement(Element teamElement) {
        AflTeam team = new AflTeam();
        Elements teamOdds = teamElement.getElementsByClass("team-odd");
        Element oddsElement = teamOdds.get(0);
        Element lineElement = teamOdds.get(1);
        team.setName(oddsElement.attr("data-teamname"));
        String odds = oddsElement.attr("data-odds");
        String line = lineElement.attr("data-handicap");
        team.setOdds(Double.parseDouble(odds));
        team.setLine(Double.parseDouble(line));
        return team;
    }
}
