package zadaca1;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FindAllOccurrences {
    public static void main(String[] args) {
        int[] array = new int[10000];
        int target = 10;
        int counter2=0;
        Lock lock = new ReentrantLock();
        Thread thread[] = new Thread[10];
        sharedCounter counter = new sharedCounter(0);
        for (int i = 0; i < 10000; i++) {
            array[i] = new Random().nextInt(100);
            if(array[i]==target){
                counter2++;
            }
        }
        for (int i = 0; i < 10; i++) {
            thread[i] = new CountOccurrencesThread(array,target,i*1000,(i+1)*1000,counter,lock);
            thread[i].start();
        }
        for (int i = 0; i < 10; i++) {
            try {
                thread[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(counter);
        System.out.println(counter2);
    }
}
class CountOccurrencesThread extends Thread{
    private int array[];
    private int target;
    private int startIndex;
    private int endIndex;
    sharedCounter counter;
    Lock lock;
    public CountOccurrencesThread(int[] array, int target, int startIndex, int endIndex, sharedCounter counter,Lock lock) {
        this.array = array;
        this.target = target;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.counter = counter;
        this.lock = lock;
    }

    @Override
    public void run() {
        super.run();
        for (int i = startIndex; i < endIndex; i++) {
            if(array[i]==target){
                lock.lock();
                try{
                    counter.increment();
                }finally {
                    lock.unlock();
                }
            }
        }
    }
}
class sharedCounter{
    private int counter;

    public sharedCounter(int counter) {
        this.counter = counter;
    }
    public void increment(){
        this.counter++;
    }

    @Override
    public String toString() {
        return String.format("%d",counter);
    }
}