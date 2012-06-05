package sampletests;

import java.util.concurrent.ArrayBlockingQueue;

import org.base60.testing.mtc.MultithreadedTestCase;
import org.base60.testing.mtc.Threaded;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/**
 * @author <a href="mailto:jvb@newtec.eu">Jan Van Besien</a>
 */
public class MTCBoundedBufferTest extends MultithreadedTestCase
{
    ArrayBlockingQueue<Integer> buf;

    @Before
    public void initialize()
    {
        buf = new ArrayBlockingQueue<Integer>(1);
    }

    @Threaded
    public void threadPutPut() throws InterruptedException
    {
        buf.put(42);
        buf.put(17);
        assertTick(1);
    }

    @Threaded
    public void threadTakeTake() throws InterruptedException
    {
        waitForTick(1);
        assertTrue(buf.take() == 42);
        assertTrue(buf.take() == 17);
    }

    @Test
    public void finish()
    {
        assertTrue(buf.isEmpty());
    }
}
