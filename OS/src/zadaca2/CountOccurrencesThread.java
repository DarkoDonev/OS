package zadaca2;

public class CountOccurrencesThread extends Thread{
    SharedArray sharedArray;
    int []array;
    int start;
    int end;
    int target;
    public CountOccurrencesThread(SharedArray sharedArray,int target,int start,int end) {
        this.sharedArray = sharedArray;
        this.array = sharedArray.getArray();
        this.target = target;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        super.run();
        int localCounter = 0;
        for (int i = start; i < end; i++) {
            if(array[i]==target) localCounter++;
        }

        sharedArray.threadDone(localCounter);
    }
}
