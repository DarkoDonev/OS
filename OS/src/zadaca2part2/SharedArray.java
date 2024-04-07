package zadaca2part2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class SharedArray {
    int []arr;
    int counter;
    Lock lock;
    Semaphore semaphore;
    int max;
    int numOfThreads;
    int threadsDone;
    public SharedArray(int[] arr, Lock lock) {
        this.arr = arr;
        this.counter = 0;
        this.lock = lock;
        this.semaphore = new Semaphore(0);
        numOfThreads = 10;
        this.threadsDone = 0;
        max = -1;
    }

    public int[] getArray() {
        return arr;
    }

    public void threadDone(int counter){
        lock.lock();
        this.counter+=counter;
        this.threadsDone++;
        if(max<counter) max = counter;
        lock.unlock();
        if(threadsDone==numOfThreads){
            semaphore.release(10);
        }else{
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
