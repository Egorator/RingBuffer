package Home.Egor;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestRingBuffer {

    @Test
    public void RingBufferStates() {
        RingBuffer ringBuffer = new RingBuffer(3);
        assertEquals(3, ringBuffer.capacity());

        assertEquals(3, ringBuffer.remainingCapacity());
        assertEquals(0, ringBuffer.numElementsInBuffer());
        assertFalse(ringBuffer.isFull());
        assertTrue(ringBuffer.isEmpty());

        assertTrue(ringBuffer.put(1));

        assertEquals(2, ringBuffer.remainingCapacity());
        assertEquals(1, ringBuffer.numElementsInBuffer());
        assertFalse(ringBuffer.isFull());
        assertFalse(ringBuffer.isEmpty());

        assertTrue(ringBuffer.put(2));

        assertEquals(1, ringBuffer.remainingCapacity());
        assertEquals(2, ringBuffer.numElementsInBuffer());
        assertFalse(ringBuffer.isFull());
        assertFalse(ringBuffer.isEmpty());

        assertTrue(ringBuffer.put(3));

        assertEquals(0, ringBuffer.remainingCapacity());
        assertEquals(3, ringBuffer.numElementsInBuffer());
        assertTrue(ringBuffer.isFull());
        assertFalse(ringBuffer.isEmpty());

        assertFalse(ringBuffer.put(4));

        assertEquals(0, ringBuffer.remainingCapacity());
        assertEquals(3, ringBuffer.numElementsInBuffer());
        assertTrue(ringBuffer.isFull());
        assertFalse(ringBuffer.isEmpty());

        assertEquals(1, ringBuffer.take());

        assertEquals(1, ringBuffer.remainingCapacity());
        assertEquals(2, ringBuffer.numElementsInBuffer());
        assertFalse(ringBuffer.isFull());
        assertFalse(ringBuffer.isEmpty());

        assertTrue(ringBuffer.put(5));

        assertEquals(0, ringBuffer.remainingCapacity());
        assertEquals(3, ringBuffer.numElementsInBuffer());
        assertTrue(ringBuffer.isFull());
        assertFalse(ringBuffer.isEmpty());

        assertFalse(ringBuffer.put(6));

        assertEquals(0, ringBuffer.remainingCapacity());
        assertEquals(3, ringBuffer.numElementsInBuffer());
        assertTrue(ringBuffer.isFull());
        assertFalse(ringBuffer.isEmpty());

        assertEquals(2, ringBuffer.take());

        assertEquals(1, ringBuffer.remainingCapacity());
        assertEquals(2, ringBuffer.numElementsInBuffer());
        assertFalse(ringBuffer.isFull());
        assertFalse(ringBuffer.isEmpty());

        assertEquals(3, ringBuffer.take());

        assertEquals(2, ringBuffer.remainingCapacity());
        assertEquals(1, ringBuffer.numElementsInBuffer());
        assertFalse(ringBuffer.isFull());
        assertFalse(ringBuffer.isEmpty());

        assertEquals(5, ringBuffer.take());

        assertEquals(3, ringBuffer.remainingCapacity());
        assertEquals(0, ringBuffer.numElementsInBuffer());
        assertFalse(ringBuffer.isFull());
        assertTrue(ringBuffer.isEmpty());

        assertNull(ringBuffer.take());

        assertEquals(3, ringBuffer.remainingCapacity());
        assertEquals(0, ringBuffer.numElementsInBuffer());
        assertFalse(ringBuffer.isFull());
        assertTrue(ringBuffer.isEmpty());

        assertTrue(ringBuffer.put(7));

        assertEquals(2, ringBuffer.remainingCapacity());
        assertEquals(1, ringBuffer.numElementsInBuffer());
        assertFalse(ringBuffer.isFull());
        assertFalse(ringBuffer.isEmpty());

        assertEquals(7, ringBuffer.take());

        assertEquals(3, ringBuffer.remainingCapacity());
        assertEquals(0, ringBuffer.numElementsInBuffer());
        assertFalse(ringBuffer.isFull());
        assertTrue(ringBuffer.isEmpty());

        assertNull(ringBuffer.take());

        assertEquals(3, ringBuffer.remainingCapacity());
        assertEquals(0, ringBuffer.numElementsInBuffer());
        assertFalse(ringBuffer.isFull());
        assertTrue(ringBuffer.isEmpty());
    }

    @Test
    public void testDataIO() {
        RingBuffer ringBuffer = new RingBuffer(8);
        Object expectedDataPut = 1;
        ringBuffer.put(expectedDataPut);
        assertEquals(expectedDataPut, ringBuffer.take());
    }

    @Test
    public void testTakeVoid() {
        RingBuffer ringBuffer = new RingBuffer(2);
        assertNull(ringBuffer.take());
    }

    @Test
    public void testBufferOverflown() {//tests writePos when put() method returns false
        RingBuffer ringBuffer = new RingBuffer(2);
        ringBuffer.put(1);
        ringBuffer.put(2);
        assertFalse(ringBuffer.put(3));
    }

    @Test
    public void testWrapped() {//tests both WritePos and ReadPos wrapped
        RingBuffer ringBuffer = new RingBuffer(2);
        ringBuffer.put(1);
        ringBuffer.put(2);
        ringBuffer.take();
        ringBuffer.put(3);
        ringBuffer.take();
        assertEquals(3, ringBuffer.take());
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
        RingBuffer ringBuffer = new RingBuffer(2);
        int expectedCapacity = 2;
        assertEquals(expectedCapacity, ringBuffer.capacity());
    }

    @Test
    public void testNumElementsInBuffer() {
        RingBuffer ringBuffer = new RingBuffer(2);
        ringBuffer.put(3);
        assertEquals(1, ringBuffer.numElementsInBuffer());
    }

    @Test
    public void testPutSucceeds() {
        RingBuffer ringBuffer = new RingBuffer(2);
        assertTrue(ringBuffer.put(1));
    }

    @Test
    public void testPutFails() {
        RingBuffer ringBuffer = new RingBuffer(2);
        ringBuffer.put(1);
        ringBuffer.put(2);
        assertFalse(ringBuffer.put(3));
    }

    @Test
    public void testTakeSucceeds() {
        RingBuffer ringBuffer = new RingBuffer(2);
        ringBuffer.put(1);
        assertEquals(1, ringBuffer.take());
    }

    @Test
    public void testTakeFails() {
        RingBuffer ringBuffer = new RingBuffer(2);
        assertNull(ringBuffer.take());
    }

    @Test
    public void testTakeFails2() {
        RingBuffer ringBuffer = new RingBuffer(2);
        ringBuffer.put(1);
        ringBuffer.put(2);
        ringBuffer.take();
        ringBuffer.take();
        ringBuffer.put(3);
        ringBuffer.take();
        assertNull(ringBuffer.take());
    }

    @Test
    public void testIsFullTrue() {
        RingBuffer ringBuffer = new RingBuffer(2);
        ringBuffer.put(1);
        ringBuffer.put(2);
        assertTrue(ringBuffer.isFull());
    }

    @Test
    public void testIsFullFalse() {
        RingBuffer ringBuffer = new RingBuffer(2);
        ringBuffer.put(1);
        assertFalse(ringBuffer.isFull());
    }

    @Test
    public void testIsEmptyTrue() {
        RingBuffer ringBuffer = new RingBuffer(2);
        assertTrue(ringBuffer.isEmpty());
    }

    @Test
    public void testIsEmptyFalse() {
        RingBuffer ringBuffer = new RingBuffer(2);
        ringBuffer.put(1);
        assertFalse(ringBuffer.isEmpty());
    }

    @Test
    public void testSuccessfulReadFromEndOfBuffer() {
        RingBuffer ringBuffer = new RingBuffer(2);
        ringBuffer.put(1);
        ringBuffer.put(2);
        ringBuffer.take();
        ringBuffer.put(3);
        ringBuffer.take();
        assertEquals(3, ringBuffer.take());
    }
}
