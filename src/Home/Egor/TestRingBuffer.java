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
        RingBuffer ringBuffer = new RingBuffer(2);
        assertEquals(null, ringBuffer.take());
    }

    @Test
    public void testBufferOverflown() {//tests writePos when put() method returns false
        RingBuffer ringBuffer = new RingBuffer(2);
        ringBuffer.put(1);
        ringBuffer.put(2);
        assertEquals(ringBuffer.put(3), false);
    }

    @Test
    public void testWrapped() {//tests both WritePos and ReadPos wrapped
        RingBuffer ringBuffer = new RingBuffer(2);
        ringBuffer.put(1);
        ringBuffer.put(2);
        ringBuffer.take();
        ringBuffer.put(3);
        ringBuffer.take();
        assertEquals(ringBuffer.take(), 3);
    }

    @Test
    public void testResetFunc() {
        int remainingCapacity = 8;
        RingBuffer ringBuffer = new RingBuffer(remainingCapacity);
        ringBuffer.put('a');
        ringBuffer.put('b');
        ringBuffer.reset();
        assertEquals(8, ringBuffer.remainingCapacity());
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
    public void testRemainingCapacity2() {
        RingBuffer ringBuffer = new RingBuffer(2);
        ringBuffer.put('a');
        ringBuffer.put('b');
        ringBuffer.take();
        ringBuffer.put('c');
        assertEquals(0, ringBuffer.remainingCapacity());
    }

    @Test
    public void testCapacityFunc() {
        RingBuffer ringBuffer = new RingBuffer(8);
        int expectedCapacity = 8;
        assertEquals(expectedCapacity, ringBuffer.capacity());
    }
}
