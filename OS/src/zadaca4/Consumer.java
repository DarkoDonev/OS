package zadaca4;

public class Consumer extends Thread{
    Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        super.run();
        buffer.consumeItem();
    }
}
