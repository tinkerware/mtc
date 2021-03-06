package sanity.basictests;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.base60.testing.mtc.MultithreadedTestCase;
import org.base60.testing.mtc.Threaded;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test order called is init, then thread, then finish
 *
 * @author <a href="mailto:jvb@newtec.eu">Jan Van Besien</a>
 */
public class SanityThreadsBeforeTest extends MultithreadedTestCase
{
    private AtomicInteger v1, v2;

    CountDownLatch c;

    @Before
    public void initialize()
    {
        v1 = new AtomicInteger(0);
        v2 = new AtomicInteger(0);
        assertTrue(v1.compareAndSet(0, 1));
        assertTrue(v2.compareAndSet(0, 1));
        c = new CountDownLatch(2);
    }

    @Threaded("1")
    public void thread1() throws InterruptedException
    {
        assertTrue(v1.compareAndSet(1, 2));
        c.countDown();
        c.await();
    }

    @Threaded("2")
    public void thread2() throws InterruptedException
    {
        assertTrue(v2.compareAndSet(1, 2));
        c.countDown();
        c.await();
    }

    @Test
    public void finish()
    {
        assertEquals(2, v1.intValue());
        assertEquals(2, v2.intValue());
    }
}
