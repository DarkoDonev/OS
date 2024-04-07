package zadaca2;

import java.util.Random;

public class Main  {
    public static void main(String[] args) {
        int[] arr = new int[100000];
        Thread[] threads = new Thread[10];
        Random random = new Random();
        int target = 10;
        SharedArray sharedArray = new SharedArray(arr,0,10);
        for (int i = 0; i < 100000; i++) {
            arr[i] = random.nextInt(100);
        }
        for (int i = 0; i < 10; i++) {
            threads[i] = new CountOccurrencesThread(sharedArray,target,i*10000,(i+1)*10000);
            threads[i].start();
        }
//        for (int i = 0; i < 10; i++) {
//            try {
//                threads[i].join();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
        System.out.println("Number of occurrences: " + sharedArray.getCount());
        System.out.println("Max per thread: " + sharedArray.getMax());
    }
}
