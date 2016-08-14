package Home.Egor;

public class RingBuffer {
    // TODO low priority: switch to writePos and readPos, remove member "available". Only do with good unit tests coverage.
    public Object[] elements = null; // TODO should be private or protected.

    private int capacity  = 0;//TODO should I initialize them somwhere else, but not there? // TODO do not initialize it here.
    private int writePos  = 0; // TODO what is the difference if we initialize it here or in constructor? Move initialization to constructor?
    private int available = 0; // TODO same as above.

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
    } // TODO delete (not public method at least).

    public int remainingCapacity() {//NOTE returns boolean!
        return this.capacity - this.available;
    }

    public boolean put(Object element) { // TODO split in two files: interface and implementation (research, see java collections code).

        if(available < capacity){ // TODO use "early return" here? if(remainingCapacity() == 0) return false; // else continue our code.
            if(writePos >= capacity){ // writePos can't be more than capacity
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
