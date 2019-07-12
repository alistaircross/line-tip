package line.tip.extractor.shared;

import line.tip.extractor.ladbrokes.LadbrokesAflRound;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

public class ExtractorService {

    private LadbrokesAflRound ladbrokesRound;
    private HttpClient client;

    public ExtractorService() {
        createHttpClient();
    }

    private void createHttpClient() {
        HttpClientBuilder builder = HttpClientBuilder.create();
        if(System.getenv("HTTP_PROXY") != null) {
            System.out.println("Using system proxy: " + System.getenv("HTTP_PROXY"));
            try {
                URL proxy = new URL( System.getenv("HTTP_PROXY"));
                builder.setProxy(new HttpHost(proxy.getHost(),proxy.getPort())).build();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        client = builder.build();
    }

    public void extractAll() {
        extractLadbrokes();
    }

    public void extractLadbrokes() {
        ladbrokesRound = new LadbrokesAflRound(client);
        ladbrokesRound.loadAflRound();
    }
}
