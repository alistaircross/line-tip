import line.tip.extractor.ladbrokes.LadbrokesExtractor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class UnmarshallingSportsObjectTest {

    @Test
    public void sportsObjectJsoupTest() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("test.html").getFile());
        Document doc = Jsoup.parse(file, "UTF-8");
        LadbrokesExtractor extractor = new LadbrokesExtractor();
        extractor.extractRoundsFromDocument(doc);
    }
}
