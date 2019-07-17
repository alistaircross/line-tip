package line.tip.extractor.shared;

import line.tip.data.AflGame;
import line.tip.data.AflRound;
import line.tip.extractor.ladbrokes.LadbrokesExtractor;
import line.tip.utils.AflGameProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class ExtractorParentService {
    private LadbrokesExtractor ladbrokesExtractor;
    private URL proxyUrl;
    private Map<Integer, AflRound> ladbrokesRounds;

    private static Logger LOG = LogManager.getLogger(ExtractorParentService.class);

    public ExtractorParentService() {
        this.proxyUrl = resolveProxy();
        this.ladbrokesExtractor = new LadbrokesExtractor();
    }

    private URL resolveProxy() {
        if (System.getenv("HTTP_PROXY") != null) {
            LOG.info("Using system proxy: " + System.getenv("HTTP_PROXY"));
            try {
                return new URL(System.getenv("HTTP_PROXY"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void extractAll() {
        extractLadbrokes();
    }

    private void extractLadbrokes() {
        Document doc = getPage(AflGameProvider.LADBROKES);
        ladbrokesRounds = ladbrokesExtractor.extractRoundsFromDocument(doc);
    }

    private Document getPage(AflGameProvider provider) {
        try {
            Connection connectionBuilder = Jsoup.connect(provider.getUrl());
            if (proxyUrl != null) {
                connectionBuilder.proxy(proxyUrl.getHost(), proxyUrl.getPort());
            }
            return connectionBuilder.url(provider.getUrl()).get();
        } catch (IOException e) {
            LOG.error("Problem with URL: " + provider.getUrl());
            e.printStackTrace();
        }
        return null;
    }

    public Map<Integer, AflRound> getLadbrokesRounds() {
        return ladbrokesRounds;
    }
}
