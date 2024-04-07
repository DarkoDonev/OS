package zadaca4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Buffer {
    Semaphore producerSemaphore;
    Semaphore consumerSemaphore;
    List<Integer> buffer;

    public Buffer(Semaphore producerSemaphore, Semaphore consumerSemaphore, List<Integer> buffer) {
//        this.currentSize = currentSize;
        this.producerSemaphore = producerSemaphore;
        this.consumerSemaphore = consumerSemaphore;
        this.buffer = buffer;
    }


    public void addItem(int item){
        try {
            producerSemaphore.acquire();
            buffer.add(item);
            consumerSemaphore.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void consumeItem(){
        try {
            consumerSemaphore.acquire();
            buffer.remove(0);
            producerSemaphore.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
