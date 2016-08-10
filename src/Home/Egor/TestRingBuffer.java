package Home.Egor;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestRingBuffer {

    @Test
    public void testDataPut() {//NOTE put method returns boolean, so we do not need it in assert
        RingBuffer ringBuffer = new RingBuffer(8);
        Object expectedDataPut = 1;
        ringBuffer.put(1);//it returns boolean, we can't compare our expected Object with it
        assertEquals(expectedDataPut, ringBuffer.elements[0]);
    }

    @Test
    public void testDataTake() {
        RingBuffer ringBuffer = new RingBuffer(8);
        Object expectedDataTaken = 3;
        ringBuffer.put(3);
        assertEquals(expectedDataTaken, ringBuffer.take());
    }

    @Test
    public void testResetFunc() {
        RingBuffer ringBuffer = new RingBuffer(8);
        ringBuffer.put('a');
        ringBuffer.put('b');
        ringBuffer.take();
        ringBuffer.reset();
        Object expectedEmptyData = 0;
        assertEquals(expectedEmptyData, ringBuffer.available());
        assertEquals(expectedEmptyData, ringBuffer.writePos());
    }

    @Test
    public void testRemainingCapacity() {
        RingBuffer ringBuffer = new RingBuffer(8);
        ringBuffer.put('a');
        ringBuffer.put('b');
        ringBuffer.take();
        int expectedRemainingCapacityValue = 7;
        assertEquals(expectedRemainingCapacityValue, ringBuffer.remainingCapacity());
    }

    @Test//TODO do I really need this test?
    public void testCapacityFunc() {
        RingBuffer ringBuffer = new RingBuffer(8);
        int expectedCapacity = 8;
        assertEquals(expectedCapacity, ringBuffer.capacity());
    }
}
