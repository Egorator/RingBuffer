package Home.Egor;

public class Main {
    public static void main(String[] args) {
        RingBuffer ringBuffer = new RingBuffer(8);
        System.out.println("Initialized new buffer, capacity: " + ringBuffer.capacity());
        System.out.println("Putting data into 0'th cell: a: " + ringBuffer.put('a') + "... Done");

        System.out.println("Reading data from 0'th cell: a: " + ringBuffer.take() + "... Done");

        System.out.println("Putting data into 1'th cell: b: " + ringBuffer.put('b') + "... Done");
        System.out.println("Putting data into 2'th cell: c: " + ringBuffer.put('c') + "... Done");
        System.out.println("Putting data into 3'th cell: d: " + ringBuffer.put('d') + "... Done");

        System.out.println("Reading data from 1'th cell: b: " + ringBuffer.take() + "... Done");
        System.out.println("Reading data from 2'th cell: c: " + ringBuffer.take() + "... Done");

        System.out.println("Putting data into 4'th cell: e: " + ringBuffer.put('e') + "... Done");
        System.out.println("Putting data into 5'th cell: f: " + ringBuffer.put('f') + "... Done");
        System.out.println("Putting data into 6'th cell: g: " + ringBuffer.put('g') + "... Done");
        System.out.println("Putting data into 7'th cell: h: " + ringBuffer.put('h') + "... Done");
        System.out.println("Putting data into 0'th cell: i: " + ringBuffer.put('i') + "... Done");

        System.out.println("Checking current data available for reading: " + ringBuffer.available());
        System.out.println("Checking remaining capacity: " + ringBuffer.remainingCapacity());

        System.out.println("Reading data from 3'th cell: d: " + ringBuffer.take() + "... Done");
        System.out.println("Reading data from 4'th cell: e: " + ringBuffer.take() + "... Done");
        System.out.println("Reading data from 5'th cell: f: " + ringBuffer.take() + "... Done");
        System.out.println("Reading data from 6'th cell: g: " + ringBuffer.take() + "... Done");
        System.out.println("Reading data from 7'th cell: h: " + ringBuffer.take() + "... Done");
        System.out.println("Reading data from 0'th cell: i: " + ringBuffer.take() + "... Done");

        System.out.println("Checking current data available for reading: " + ringBuffer.available());

        ringBuffer.reset();
    }
}

class RingBuffer {
    public Object[] elements = null;

    private int capacity  = 0;
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
    public int available(){
        return this.available;
    }

    public int remainingCapacity() {
        return this.capacity - this.available;
    }

    public boolean put(Object element){

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