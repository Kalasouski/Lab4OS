import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {
    static void startPattern(int queueCapacity, int nConsumers, int nProducers, int minValue, int maxValue,
                             int millis){
        Queue<Integer> queue = new ArrayBlockingQueue<>(queueCapacity,true);

        for(int i = 0;i<nProducers;i++)
            new Producer(queue, queueCapacity, minValue, maxValue, millis).start();
        for(int i = 0;i<nConsumers;i++)
            new Consumer(queue).start();

    }

    public static void main(String[] args) {
        startPattern(100,300,100, 20, 50, 1000);
    }
}