package Home.Egor;

public class RingBuffer {
    // TODO low priority: switch to writePos and readPos, remove member "available". Only do with good unit tests coverage.
    private Object[] elements = null; // TODO should be private or protected.[DONE]

    private int capacity  = 0;//TODO should I initialize them somewhere else, but not there? // TODO do not initialize it here. [???]
    private int writePos  = 0; // TODO what is the difference if we initialize it here or in constructor? Move initialization to constructor? [DONE]
    private int available = 0; // TODO same as above. [DONE]

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

    protected int writePos() {
        return this.writePos;
    } // TODO delete (not public method at least). [DONE]

    public int remainingCapacity() {
        return this.capacity - this.available;
    }

    public void put(Object element) { //TODO split in two files: interface and implementation (research, see java collections code). [DONE]

/*        if(remainingCapacity() == 0) return false;
        else {
            if(writePos == capacity){ // writePos can't be more than capacity
                writePos = 0;
            }
            elements[writePos] = element;
            writePos++;
            available++;
            return true;
        }*/
        //TODO why the hell we need to return false at all? We overwrite first element if buffer is full! [ASK]
        if(writePos == capacity){ // writePos can't be more than capacity
            writePos = 0;
        }
        elements[writePos] = element;
        writePos++;
        available++;


/*        if(available < capacity){ // TODO use "early return" here? if(remainingCapacity() == 0) return false; // else continue our code. [DONE]
            if(writePos >= capacity){ // writePos can't be more than capacity
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
