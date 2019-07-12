package line.tip;

import line.tip.extractor.shared.ExtractorService;

public class Main {
    public static void main(String[] args) {
        ExtractorService es = new ExtractorService();
        es.extractAll();
    }
}
