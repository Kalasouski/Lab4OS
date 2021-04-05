public class Main {
    public static void main(String[] args){
        SyncQueue q = new SyncQueue();
        new Producer(q,1000);
        new Consumer(q);
    }
}
