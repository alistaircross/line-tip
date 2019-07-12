import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class UnmarshallingSportsObjectTest {
    @Test
    public void sportsObjectJsoupTest() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("test.html").getFile());
        Document doc = Jsoup.parse(file, "UTF-8");
        Elements sportsMarkets = doc.getElementsByClass("sports-market");
        for (Element repository : sportsMarkets) {
            // Extract the game
            String marketName = repository.getElementsByClass("market-name").text();
            System.out.println(marketName);
        }
    }
}
