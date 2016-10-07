package Home.Egor;

public class RingBuffer {
    private Object[] elements;

    private int capacity;
    private int writePos;
    private int readPos;

    public RingBuffer(int capacity) {
        this.capacity = capacity;
        elements = new Object[capacity];
        writePos = 0;
        readPos = 0;
    }

    public void reset() {
        writePos = 0;
        readPos = 0;
    }

    public int capacity() {
        return capacity;
    }
    public int readPos() {
        return readPos;
    }

    protected int writePos() {
        return writePos;
    }

    public int remainingCapacity() {
        if (writePos > readPos)
        return capacity - writePos + readPos;
        /*if (readPos > writePos)
        return readPos - writePos;*///writePos can't overrun readPos in this implementation
        return capacity;
    }

    public boolean put(Object element) {
        //TODO idiomatic way of error handling in java. return code or throw exception? (in case of buffer overflow) [DONE]
        //TODO p.s. I mean throw your own exception or return boolean of success. In case of bool we need unit test,
        //TODO expecting boolean, in case of throwable exception we need somehow to check wether it thew or not.
        if (remainingCapacity() == 1)//we need to have at least 1 empty cell in this implementation
            return false;
        elements[writePos] = element;
        writePos++;
        if (writePos == capacity)//writePos wrapped
            writePos = 0;
        return true;

/*        if (available < capacity) {
            if (writePos >= capacity) { // writePos can't be more than capacity
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
        if (readPos == capacity - 1) {
            readPos = 0;
            return nextObj;
        }
        readPos++;
        return nextObj;
    }
}
