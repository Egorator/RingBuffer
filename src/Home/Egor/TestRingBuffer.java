package Home.Egor;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestRingBuffer {

    @Test
    public void testDataIO() {
        RingBuffer ringBuffer = new RingBuffer(8);
        Object expectedDataPut = 1;
        // TODO check that put() succeeded. //put() theoretically always succeeds! [ASK]
        ringBuffer.put(expectedDataPut);
        assertEquals(expectedDataPut, ringBuffer.take());
    }

    @Test
    public void testTakeVoid() {
        RingBuffer ringBuffer = new RingBuffer(3);
        assertEquals(null, ringBuffer.take());
    }

    @Test
    public void testBufferOverflown() {//tests writePos when put() method returns false
        RingBuffer ringBuffer = new RingBuffer(3);
        ringBuffer.put(1);
        ringBuffer.put(2);
        assertEquals(ringBuffer.put(3), false);
    }

    @Test
    public void testWrapped() {//tests both WritePos and ReadPos wrapped
        RingBuffer ringBuffer = new RingBuffer(3);
        ringBuffer.put(1);
        ringBuffer.put(2);
        ringBuffer.take();
        ringBuffer.put(3);
        ringBuffer.take();
        ringBuffer.put(4);
        ringBuffer.take();
        assertEquals(ringBuffer.take(), 4);
    }

    @Test
    public void testResetFunc() {
        RingBuffer ringBuffer = new RingBuffer(8);
        ringBuffer.put('a');
        ringBuffer.put('b');
        ringBuffer.take();
        ringBuffer.reset();
        assertEquals(0, ringBuffer.readPos());
        assertEquals(0, ringBuffer.writePos());
    }

    @Test
    public void testRemainingCapacity() {
        RingBuffer ringBuffer = new RingBuffer(8);
        ringBuffer.put('a');
        ringBuffer.put('b');
        ringBuffer.take();
        assertEquals(7, ringBuffer.remainingCapacity());
    }

    @Test
    public void testCapacityFunc() {
        RingBuffer ringBuffer = new RingBuffer(8);
        int expectedCapacity = 8;
        assertEquals(expectedCapacity, ringBuffer.capacity());
    }
}
