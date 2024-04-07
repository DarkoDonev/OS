package zadaca2part2;


import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class CountOccurrencesThread extends Thread{
    SharedArray sharedArray;
    int [] array;
    int target;
    int start;
    int end;
    int here;
    int max;
    Lock lock;
    Semaphore semaphore;

    public CountOccurrencesThread(SharedArray sharedArray, int target, int start, int end,int here,Lock lock) {
        this.sharedArray = sharedArray;
        this.target = target;
        this.start = start;
        this.end = end;
        array = sharedArray.getArray();
        this.here = here;
        semaphore = new Semaphore(0);
        max = -1;
        this.lock = lock;
    }

    @Override
    public void run() {
        super.run();
        int localCounter = 0;
        for (int i = start; i < end; i++) {
            if (array[i]==target){
                localCounter++;
            }
        }
        sharedArray.threadDone(localCounter);
        lock.lock();
        if(localCounter==sharedArray.max){
            System.out.println("Max occurrences are found in thread: " + here + " and the max occurrences number is: " + localCounter);
        }
        lock.unlock();
    }
}
