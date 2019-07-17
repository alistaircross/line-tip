package line.tip.schedule;

import line.tip.extractor.shared.ExtractorParentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static line.tip.utils.SharedUtils.EXTRACTOR_PARENT_SERVICE;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class TenMinuteScheduleService {
    private static Logger LOG = LogManager.getLogger(TenMinuteScheduleService.class);
    private static String JOB_IDENTITY = "TEN_MIN_SITE_LOAD_JOB";
    private static String JOB_GROUP = "TEN_MIN_SITE_LOAD_JOB_GROUP";
    private static String TRIGGER_IDENTITY = "SITE_LOAD_TRIGGER_IDENTY";
    private ExtractorParentService extractorParentService;

    public TenMinuteScheduleService(ExtractorParentService extractorParentService) {
        this.extractorParentService = extractorParentService;
    }

    public void run() throws Exception {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        sched.getContext().put(EXTRACTOR_PARENT_SERVICE, extractorParentService);
        extractorParentService.extractAll();
        JobDetail job = newJob(LoadSiteJob.class).withIdentity(JOB_IDENTITY, JOB_GROUP).build();

        Trigger trigger = newTrigger().withIdentity(TRIGGER_IDENTITY, JOB_GROUP).startNow().withSchedule(cronSchedule("0 */10 * ? * *")).build();

        sched.scheduleJob(job, trigger);

        sched.start();

        LOG.info("Started the 10 Minute loader.");

    }

}
