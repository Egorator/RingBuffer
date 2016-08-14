package Home.Egor;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestRingBuffer {

    // TODO measure test coverage.

    @Test
    public void testDataPut() {//NOTE put method returns boolean, so we do not need it in assert
        RingBuffer ringBuffer = new RingBuffer(8);
        Object expectedDataPut = 1; // TODO use expectedDataResult as an argument below (repeat for all tests).
        ringBuffer.put(1);//it returns boolean, we can't compare our expected Object with it
        // TODO check that put() succeeded.
        assertEquals(expectedDataPut, ringBuffer.elements[0]); // TODO make elements protected or private, use take() instead.
    }

    @Test
    public void testDataTake() {
        // TODO test is the same as above. Delete.
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
        Object expectedEmptyData = 0; // TODO switch to int here, no need to define variable expectedEmptyData.
        assertEquals(expectedEmptyData, ringBuffer.available());
        assertEquals(expectedEmptyData, ringBuffer.writePos()); // TODO writePos() should be private or protected.
    }

    @Test
    public void testRemainingCapacity() {
        RingBuffer ringBuffer = new RingBuffer(8);
        ringBuffer.put('a');
        ringBuffer.put('b');
        ringBuffer.take();
        int expectedRemainingCapacityValue = 7; // TODO no need to define variable here, instead use 7 below.
        assertEquals(expectedRemainingCapacityValue, ringBuffer.remainingCapacity());
    }

    @Test//TODO do I really need this test? // yes you do :)
    public void testCapacityFunc() {
        RingBuffer ringBuffer = new RingBuffer(8);
        int expectedCapacity = 8; // TODO use this variable above.
        assertEquals(expectedCapacity, ringBuffer.capacity());
    }

    // TODO add more test cases to cover "coner cases" (writePos / readPos moves capacity - 1 to 0, ...).
}
