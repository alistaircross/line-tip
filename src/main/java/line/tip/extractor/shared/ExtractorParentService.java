package line.tip.extractor.shared;

import line.tip.extractor.ladbrokes.LadbrokesExtractor;
import line.tip.utils.AflGameProvider;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class ExtractorParentService {
    private LadbrokesExtractor ladbrokesExtractor;
    private URL proxyUrl;

    public ExtractorParentService() {
        this.proxyUrl = resolveProxy();
        this.ladbrokesExtractor = new LadbrokesExtractor();
    }

    private URL resolveProxy() {
        if (System.getenv("HTTP_PROXY") != null) {
            System.out.println("Using system proxy: " + System.getenv("HTTP_PROXY"));
            try {
                return new URL(System.getenv("HTTP_PROXY"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void extractLadbrokes() {
        Document doc = getPage(AflGameProvider.LADBROKES);
        Map ladbrokesRounds = ladbrokesExtractor.extractRoundsFromDocument(doc);
        System.out.println(ladbrokesRounds);
    }

    private Document getPage(AflGameProvider provider) {
        try {
            Connection connectionBuilder = Jsoup.connect(provider.getUrl());
            if (proxyUrl != null) {
                connectionBuilder.proxy(proxyUrl.getHost(), proxyUrl.getPort());
            }
            return connectionBuilder.url(provider.getUrl()).get();
        } catch (IOException e) {
            System.err.println("Problem with URL: " + provider.getUrl());
            e.printStackTrace();
        }
        return null;
    }
}
