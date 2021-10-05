package listeners;

import org.apache.logging.log4j.ThreadContext;
import org.junit.BeforeClass;

public class LoggerForParallelTests {

    @BeforeClass
    public void addThread(){
        ThreadContext .put("threadName", this.getClass().getName());
    }
}
