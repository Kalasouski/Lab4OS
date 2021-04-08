import java.util.Queue;

public class Producer extends Thread {
    private final Queue<Integer> queue;
    private final int maxSize;
    private final int minValue, maxValue;
    private final int millis;
    public Producer(Queue<Integer> queue, int maxSize, int minValue, int maxValue, int millis) {
        this.queue = queue;
        this.maxSize = maxSize;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.millis = millis;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            synchronized (queue){
                while (queue.size() == maxSize) {
                    try {
                        System.out .println("Queue is full, "
                                + "Producer thread waiting for "
                                + "consumer to take something from queue");
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int generatedVal = getRandomNumber(minValue,maxValue);
                System.out.println("Producing value : " + generatedVal);
                queue.add(generatedVal);
                queue.notifyAll();
            }
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}