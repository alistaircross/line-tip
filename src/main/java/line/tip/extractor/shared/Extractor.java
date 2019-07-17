package line.tip.extractor.shared;

import org.jsoup.nodes.Document;

import java.util.Map;

public interface Extractor {
    public Map extractRoundsFromDocument(Document doc);
}
