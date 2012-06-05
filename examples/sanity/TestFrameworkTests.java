package sanity;

import org.base60.testing.mtc.MultithreadedTest;
import org.base60.testing.mtc.MultithreadedTestCase;
import org.base60.testing.mtc.Threaded;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestFrameworkTests extends MultithreadedTestCase
{

    int i = 0;

    @Threaded("1")
    public void thisWillRunInThread1()
    {
        i++;
    }

    @Threaded("2")
    public void thisWillRunInThread2()
    {
        waitForTick(1);
        i++;
    }

    @Test
    @MultithreadedTest(times = 3)
    public void testRunThreeTimes() throws Throwable
    {
        assertEquals(i, 6);
    }

}
