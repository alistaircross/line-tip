package line.tip.schedule;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;

public class LoadSiteJob implements Job {
    private static Logger LOG = LogManager.getLogger(LoadSiteJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SchedulerContext schedulerContext = null;
        try {
            schedulerContext = jobExecutionContext.getScheduler().getContext();
        } catch (SchedulerException e1) {
            LOG.error("There was a problem retrieving the scheduler context.",e1);
            return;
        }

        try {
            LOG.info("Kicking off scheduled load!");
            //This is where we load the site
        } catch (NullPointerException npe) {
            LOG.error("We were unable to retrieve the live person service from the context.",npe);
        } catch (Exception e) {
            LOG.error("An unexpected exception occurred when trying to load yesterdays data", e);
        }

    }
}
