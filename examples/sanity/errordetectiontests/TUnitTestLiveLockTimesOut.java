package sanity.errordetectiontests;

import java.util.concurrent.atomic.AtomicInteger;

import org.base60.testing.mtc.MultithreadedTestCase;
import org.base60.testing.mtc.Threaded;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/**
 * @author <a href="mailto:jvb@newtec.eu">Jan Van Besien</a>
 */
public class TUnitTestLiveLockTimesOut extends MultithreadedTestCase
{
    AtomicInteger ai;

    @Before
    public void initialize()
    {
        ai = new AtomicInteger(1);
    }

    @Threaded
    public void thread1()
    {
        while (!ai.compareAndSet(2, 3)) Thread.yield();
    }

    @Threaded
    public void thread2()
    {
        while (!ai.compareAndSet(3, 2)) Thread.yield();
    }

    @Test(expected = IllegalStateException.class)
    public void finish()
    {
        assertTrue(ai.get() == 2 || ai.get() == 3);
    }
}
