package plain_vs_mtc;

import java.util.concurrent.atomic.AtomicInteger;

import org.base60.testing.mtc.MultithreadedTestCase;
import org.base60.testing.mtc.Threaded;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:jvb@newtec.eu">Jan Van Besien</a>
 */
public class CompareAndSetTestsMTC extends MultithreadedTestCase
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
        assertTrue(ai.compareAndSet(1, 2));
    }

    @Test
    public void finish()
    {
        assertEquals(ai.get(), 3);
    }
}
