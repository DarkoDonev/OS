package zadaca4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {


    public static void main(String[] args) {
        Semaphore producerSemaphore = new Semaphore(5);
        Semaphore consumerSemaphore = new Semaphore(0);

        List<Integer> buffer = new ArrayList<>();
        Buffer sharedBuffer = new Buffer(producerSemaphore,consumerSemaphore,buffer);
        Producer producer = new Producer(sharedBuffer);
        producer.start();

    }
}
