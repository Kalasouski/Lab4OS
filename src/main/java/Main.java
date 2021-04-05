public class Main {
    static void startPattern(int n) {
        for (int i = 0; i < n; i++) {
            SyncQueue q = new SyncQueue();
            new Producer(q, 1000);
            new Consumer(q);
        }
    }

    public static void main(String[] args) {
        startPattern(1);
    }
}