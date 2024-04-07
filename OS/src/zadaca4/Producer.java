package zadaca4;

import java.util.Random;

public class Producer extends Thread{
    Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        super.run();
        int item = getItem();
        buffer.addItem(item);
    }

    public int getItem(){
        return new Random().nextInt(10);
    }
}
