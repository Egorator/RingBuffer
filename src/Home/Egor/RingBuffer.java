package Home.Egor;

public class RingBuffer {
    private Object[] elements;

    private int bufSize;
    private int writePos;
    private int readPos;

    public RingBuffer(int capacity) {//capacity is how much elements you can store. bufSize is size of buffer (capacity++)
        if (capacity <= 0) {
            capacity = 1;
        }
        bufSize = capacity + 1;
        elements = new Object[bufSize];
        writePos = 0;
        readPos = 0;
    }

    public void reset() {
        writePos = 0;
        readPos = 0;
    }

    public int capacity() {
        return bufSize - 1;
    }

    public int remainingCapacity() {
        if (writePos > readPos)
        return capacity() - writePos + readPos;
        /*if (readPos > writePos)
        return readPos - writePos;*///writePos can't overrun readPos in this implementation
        return capacity();
    }

    public boolean put(Object element) {
        //TODO idiomatic way of error handling in java. return code or throw exception? (in case of buffer overflow) [DONE]
        //TODO p.s. I mean throw your own exception or return boolean of success. In case of bool we need unit test,
        //TODO expecting boolean, in case of throwable exception we need somehow to check wether it thew or not.
        if (remainingCapacity() == 0)
            return false;
        elements[writePos] = element;
        writePos++;
        if (writePos == bufSize)//writePos wrapped
            writePos = 0;
        return true;

/*        if (available < bufSize) {
            if (writePos >= bufSize) { // writePos can't be more than bufSize
                writePos = 0;
            }
            elements[writePos] = element;
            writePos++;
            available++;
            return true;
        }

        return false;*/
    }

    public Object take() {
        Object nextObj = elements[readPos];
        if (readPos == bufSize - 1) {
            readPos = 0;
            return nextObj;
        }
        readPos++;
        return nextObj;
    }
}
