import java.math.BigInteger;

public class SyncQueue {
    int num;
    boolean valueSet = false;

    synchronized BigInteger get() throws InterruptedException {
        while (!valueSet)
            wait();
        BigInteger fact = factorial(num);
        System.out.println("Got: " + fact);
        valueSet = false;
        notify();
        return fact;
    }

    synchronized void put(int num) throws InterruptedException {
        while (valueSet)
            wait();
        this.num = num;
        valueSet = true;
        System.out.println("Posted: " + num);
        notify();
    }

    static BigInteger factorial(int number) {
        BigInteger factorial = BigInteger.ONE;
        for (int i = number; i > 0; i--) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }
}