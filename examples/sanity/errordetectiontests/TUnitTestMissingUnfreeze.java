package sanity.errordetectiontests;

import org.base60.testing.mtc.MultithreadedTestCase;
import org.base60.testing.mtc.Threaded;
import org.junit.Test;

/**
 * @author <a href="mailto:jvb@newtec.eu">Jan Van Besien</a>
 */
public class TUnitTestMissingUnfreeze extends MultithreadedTestCase
{
    @Threaded
    public void thread1() throws InterruptedException
    {
        freezeClock();
        Thread.sleep(200);
    }

    @Threaded
    public void thread2()
    {
        waitForTick(1);
    }

    @Test(expected = IllegalStateException.class)
    public void test()
    {
    }
}
