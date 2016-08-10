package Home.Egor;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestRingBuffer.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}

class TestRingBuffer {

    @Test
    public void testDataPut() {
        RingBuffer ringBuffer = new RingBuffer(8);
        Object expectedDataPut = 1;
        assertEquals(expectedDataPut, ringBuffer.put(1));
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
        Object expectedEmptyData = null;
        assertEquals(expectedEmptyData, ringBuffer.available());
        assertEquals(expectedEmptyData, ringBuffer.writePos());
    }

    @Test
    public void testRemainingCapacity() {
        RingBuffer ringBuffer = new RingBuffer(8);
        ringBuffer.put('a');
        ringBuffer.put('b');
        ringBuffer.take();
        int expectedRemainingCapacityValue = 1;
        assertEquals(expectedRemainingCapacityValue, ringBuffer.remainingCapacity());
    }

    @Test//TODO do I really need this test?
    public void testCapacityFunc() {
        RingBuffer ringBuffer = new RingBuffer(8);
        int expectedCapacity = 8;
        assertEquals(expectedCapacity, ringBuffer.capacity());
    }
}

class RingBuffer {
    public Object[] elements = null;

    private int capacity  = 0;//TODO should I initialize them somwhere else, but not there?
    private int writePos  = 0;
    private int available = 0;

    public RingBuffer(int capacity) {
        this.capacity = capacity;
        this.elements = new Object[capacity];
    }

    public void reset() {
        this.writePos = 0;
        this.available = 0;
    }

    public int capacity() {
        return this.capacity;
    }
    public int available() {
        return this.available;
    }

    public int writePos() {
        return this.writePos;
    }

    public int remainingCapacity() {
        return this.capacity - this.available;
    }

    public boolean put(Object element) {

        if(available < capacity){
            if(writePos >= capacity){
                writePos = 0;
            }
            elements[writePos] = element;
            writePos++;
            available++;
            return true;
        }

        return false;
    }

    public Object take() {
        if(available == 0){
            return null;
        }
        int nextSlot = writePos - available;
        if(nextSlot < 0){
            nextSlot += capacity;
        }
        Object nextObj = elements[nextSlot];
        available--;
        return nextObj;
    }
}