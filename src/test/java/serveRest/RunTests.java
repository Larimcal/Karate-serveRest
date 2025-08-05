package serveRest;

import akka.dispatch.sysmsg.Suspend;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunTests {
    private static final Logger log = LoggerFactory.getLogger(RunTests.class);

    @Test
    void runTests() {
        Runner.Builder testBuilder = Runner.path("classpath:serveRest/features");

        testBuilder.tags("@run").tags("~@ignore");

        String env = System.getProperty("karate.env");
        String tags = System.getProperty("karate.options");

        String threadsProperty = System.getProperty("threads");
        int numberOfthreads = 1;
        if (threadsProperty != null) {
            try {
                numberOfthreads = Integer.parseInt(threadsProperty);
            } catch (NumberFormatException err) {
                log.warn(err.toString());
                log.warn("Fallback to single thread execution");
            }
        }

        Results results = testBuilder.parallel(numberOfthreads);
    }
}
