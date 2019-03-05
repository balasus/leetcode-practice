package concurrency;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.IntPredicate;

/**
 * Program to print even and odd in sequence by threads
 * From : https://stackoverflow.com/questions/39037629/print-even-odd-number-using-two-threads
 */
public class PrintEvenOdd {
    public static void main(String[] args) {
        // thread pooling
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.submit(new Printer(i->i%2!=0, 10));
        pool.submit(new Printer(i->i%2==0, 10));
        pool.shutdown();
    }

    /**
     * Printer which prints even and odd numbers
     */
    private static final class Printer implements Runnable {

        // shared lock for accessing and updating the value by threads
        private static final Object LOCK = new Object();
        // a counter whose updated value needs to be visible to other threads
        private static volatile int counter=1;
        // Instance variable to let task (runnable) know what to print , even/odd
        private final IntPredicate predicate;
        // Range of numbers to print
        private int maxRange;

        public Printer(IntPredicate predicate, int range) {
            this.predicate = Objects.requireNonNull(predicate);
            this.maxRange=range;
        }

        @Override
        public void run() {
            while (counter < maxRange){
                synchronized (LOCK){
                    if(predicate.test(counter)){
                        System.out.println(Thread.currentThread().getName() + ":" + counter);
                        counter++;
                    }
                }
            }
        }
    }
}
