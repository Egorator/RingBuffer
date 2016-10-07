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
        reset();
    }

    public void reset() {
        writePos = 0;
        readPos = 0;
    }

    public int capacity() {
        return bufSize - 1;
    }

    public int remainingCapacity() {
        return capacity() - numElementsInBuffer();
    }

    public int numElementsInBuffer() {
        if (writePos >= readPos)
            return writePos - readPos;
        return bufSize + writePos - readPos;
    }

    public boolean put(Object element) {
        if (remainingCapacity() == 0)
            return false;
        elements[writePos] = element;
        writePos = nextPos(writePos);
        return true;
    }

    public Object take() {
        if (isEmpty())
            return null;
        Object nextObj = elements[readPos];
        readPos = nextPos(readPos);
        return nextObj;
    }

    private int nextPos(int curPos) {
        curPos = (curPos + 1) % bufSize;
        return curPos;
    }

    public boolean isFull() {
        return remainingCapacity() == 0;
    }

    public boolean isEmpty() {
        return numElementsInBuffer() == 0;
    }
}
