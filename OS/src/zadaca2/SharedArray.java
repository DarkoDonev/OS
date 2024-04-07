package zadaca2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedArray {
    private int array[];
    private int counter;
    private int maxPerThread;
    private int threadsWorking;
    Lock lock;

    public SharedArray(int[] array, int counter, int threadsWorking) {
        this.array = array;
        this.counter = counter;
        this.lock = new ReentrantLock();
        this.threadsWorking = threadsWorking;
    }

    public void threadDone(int counter){
        lock.lock();
        this.counter+=counter;
        if(counter>this.maxPerThread){
            maxPerThread = counter;
        }
        lock.unlock();
    }
    public int getMax(){
        return maxPerThread;
    }
    public int[] getArray() {
        return array;
    }

    public int getCount() {
        return this.counter;
    }
}
