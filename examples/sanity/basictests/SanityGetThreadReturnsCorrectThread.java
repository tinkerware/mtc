package sanity.basictests;

import org.base60.testing.mtc.MultithreadedTestCase;
import org.base60.testing.mtc.Threaded;
import org.junit.Test;

import static org.junit.Assert.assertSame;

/**
 * @author <a href="mailto:jvb@newtec.eu">Jan Van Besien</a>
 */
public class SanityGetThreadReturnsCorrectThread extends MultithreadedTestCase
{
    Thread t;

    @Threaded("1")
    public void thread1()
    {
        t = Thread.currentThread();
        waitForTick(2);
    }

    @Threaded("2")
    public void thread2()
    {
        waitForTick(1);
        assertSame(getThread(1), t);
    }

    @Test
    public void test()
    {
    }
}
