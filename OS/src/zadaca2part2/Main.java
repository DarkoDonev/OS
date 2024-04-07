package zadaca2part2;




import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        int [] arr = new int[1000000000];
        int target = 16;
        int counterHere = 0;
        Random random = new Random();
        Lock lock = new ReentrantLock();
        int numOfThreads=10;
        for (int i = 0; i < 100000000; i++) {
            arr[i] = random.nextInt(10);
            if(arr[i] == target) counterHere++;
        }
        SharedArray sharedArray = new SharedArray(arr,lock);

        Thread []thread = new CountOccurrencesThread[10000000];

        for (int i = 0; i < numOfThreads; i++) {
            thread[i] = new CountOccurrencesThread(sharedArray,target,(i*10000000),(i+1)*10000000,i+1,lock);
            thread[i].start();
        }

        for (int i = 0; i < numOfThreads; i++) {
            try {
                thread[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(sharedArray.counter);
        System.out.println(counterHere);
        System.out.println(sharedArray.max);
    }
}
