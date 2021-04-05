public class Producer implements Runnable {
    final SyncQueue queue;
    final int millis;

    public Producer(SyncQueue queue, int millis) {
        this.queue = queue;
        this.millis = millis;
        new Thread(this, this.getClass().getName()).start();
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            try {
                int res = getRandomNumber(10, 50);
                queue.put(res);
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