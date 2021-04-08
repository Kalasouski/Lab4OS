import java.math.BigInteger;
import java.util.Queue;

public class Consumer extends Thread {
    private final Queue<Integer> queue;

    public Consumer(Queue<Integer> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            synchronized (queue) {
                while (queue.isEmpty()) {

                    System.out.println("Queue is empty,"
                            + "Consumer thread is waiting"
                            + " for producer thread to put something in queue");
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int val = queue.remove();
                System.out.println("Consuming value : fact(" + val+") = "+factorial(val));
                queue.notifyAll();
            }
        }
    }

    public static BigInteger factorial(int number) {
        BigInteger factorial = BigInteger.ONE;
        for (int i = number; i > 0; i--)
            factorial = factorial.multiply(BigInteger.valueOf(i));
        return factorial;
    }
}