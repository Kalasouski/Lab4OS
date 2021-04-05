public class Consumer implements Runnable {
    final SyncQueue queue;

    public Consumer(SyncQueue queue) {
        this.queue = queue;
        new Thread(this, this.getClass().getName()).start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                queue.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}