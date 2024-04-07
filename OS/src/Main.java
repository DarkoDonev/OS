// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
class sharedCounter{
    public int number;

    public sharedCounter() {
        this.number = 0;
    }

    public int getNumber() {
        return number;
    }
}

public class Main {

    public static void main(String[] args) {
        Thread ta = new ThreadA();
        Thread tb = new ThreadA();
        Thread tc = new ThreadA();
        ta.start();
        tb.start();
        tc.start();
        System.out.println("DONE");
    }

}
class Test {
    private static String staticField; // can be shared
    private String classField; // can be shared
    public String example() {
        String localVariable="something"; // never shared
        return localVariable;
    }
    public String example(String mayBeShared) {
        String shouldBeProtected = mayBeShared;
        return shouldBeProtected;
    }
}
class ThreadA extends Thread{
    private int hehe;
    public ThreadA() {
        hehe=0;
    }

    public void run(){
        for (int i = 0; i < 2000; i++) {
            hehe++;
        }
        System.out.println("A done");
    }
}
