package line.tip.extractor.ladbrokes;

import line.tip.extractor.shared.AflRound;
import line.tip.utils.AflGameProvider;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static line.tip.utils.AflGameProvider.LADBROKES;

public class LadbrokesAflRound extends AflRound {
    protected AflGameProvider provider = LADBROKES;
    private HttpClient client;

    public LadbrokesAflRound(HttpClient client) {
        this.client = client;
    }

    @Override
    public void loadAflRound() {
        System.out.println("Loading " + provider.getUrl());
        getPage();
    }

    private void getPage() {
        HttpGet request = new HttpGet(provider.getUrl());
        try {
            HttpResponse response = client.execute(request);
            System.out.println("Response Code : "
                    + response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line.trim()+"\n");
            }
            System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
