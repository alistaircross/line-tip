package line.tip.webserver;

import line.tip.extractor.shared.ExtractorParentService;
import line.tip.schedule.TenMinuteScheduleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    private static Logger LOG = LogManager.getLogger(Application.class);

    private static ExtractorParentService extractorParentService;
    private static TenMinuteScheduleService tss;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        extractorParentService = new ExtractorParentService();
        tss = new TenMinuteScheduleService(extractorParentService);
        try {
            tss.run();
        } catch (Exception e) {
            LOG.error("Can't kick off schedule:",e);
        }
    }

    public static ExtractorParentService getExtractorParentService() {
        return extractorParentService;
    }
}
