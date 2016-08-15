package Home.Egor;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestRingBuffer {

    // TODO measure test coverage.

    @Test
    public void testDataIO() {
        RingBuffer ringBuffer = new RingBuffer(8);
        Object expectedDataPut = 1; // TODO use expectedDataResult as an argument below (repeat for all tests). [DONE]
        // TODO check that put() succeeded. //put() theoretically always succeeds! [ASK]
        ringBuffer.put(expectedDataPut);
        assertEquals(expectedDataPut, ringBuffer.take()); // TODO make elements protected or private, use take() instead. [DONE]
    }

/*    @Test//TODO is this test useless? Theoretically, if we have full buffer - we just overwrite first element. [ASK]
    public void testPutUnavailability() {//tests put() method in case, when RemainingCapacity = 0. Method should return false
        RingBuffer ringBuffer = new RingBuffer(0);
        assertEquals(false, ringBuffer.put(1));
    }*/

    @Test
    public void testTakeVoid() {
        RingBuffer ringBuffer = new RingBuffer(0);
        assertEquals(null, ringBuffer.take());
    }

    @Test
    public void testWritePosWrapped() {//tests writePos when put() method reverts it to buffer's beginning
        RingBuffer ringBuffer = new RingBuffer(2);
        ringBuffer.put(1);
        ringBuffer.put(2);
        ringBuffer.put(3);
        assertEquals(ringBuffer.writePos(), 1);
    }

/*    @Test
    public void testDataTake() {
        // TODO test is the same as above. Delete. [DONE]
        RingBuffer ringBuffer = new RingBuffer(8);
        Object expectedDataTaken = 3;
        ringBuffer.put(3);
        assertEquals(expectedDataTaken, ringBuffer.take());
    }*/

    @Test
    public void testResetFunc() {
        RingBuffer ringBuffer = new RingBuffer(8);
        ringBuffer.put('a');
        ringBuffer.put('b');
        ringBuffer.take();
        ringBuffer.reset();
        //Object expectedEmptyData = 0; // TODO switch to int here, no need to define variable expectedEmptyData. [DONE]
        assertEquals(0, ringBuffer.available());
        assertEquals(0, ringBuffer.writePos()); // TODO writePos() should be private or protected. [DONE]
    }

    @Test
    public void testRemainingCapacity() {
        RingBuffer ringBuffer = new RingBuffer(8);
        ringBuffer.put('a');
        ringBuffer.put('b');
        ringBuffer.take();
        //int expectedRemainingCapacityValue = 7; // TODO no need to define variable here, instead use 7 below. [DONE]
        assertEquals(7, ringBuffer.remainingCapacity());
    }

    @Test//TODO do I really need this test? // yes you do :)
    public void testCapacityFunc() {
        RingBuffer ringBuffer = new RingBuffer(8);
        int expectedCapacity = 8; // TODO use this variable above. [I ALREADY USE IT THERE, DON'T I?]
        assertEquals(expectedCapacity, ringBuffer.capacity());
    }

    // TODO add more test cases to cover "coner cases" (writePos / readPos moves capacity - 1 to 0, ...).
}
