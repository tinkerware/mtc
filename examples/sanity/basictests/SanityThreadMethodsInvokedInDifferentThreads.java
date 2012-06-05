package sanity.basictests;

import org.base60.testing.mtc.MultithreadedTestCase;
import org.base60.testing.mtc.Threaded;
import org.junit.Test;

import static org.junit.Assert.assertNotSame;

/**
 * @author <a href="mailto:jvb@newtec.eu">Jan Van Besien</a>
 */
public class SanityThreadMethodsInvokedInDifferentThreads extends MultithreadedTestCase
{
    Thread t1, t2;

    @Threaded
    public void thread1()
    {
        t1 = Thread.currentThread();
        waitForTick(2);
    }

    @Threaded
    public void thread2()
    {
        t2 = Thread.currentThread();
        waitForTick(2);
    }

    @Threaded
    public void thread3()
    {
        waitForTick(1);
        assertNotSame(t1, t2);
    }

    @Test
    public void test()
    {
    }
}
