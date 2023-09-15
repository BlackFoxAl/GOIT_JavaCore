package Module12;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ThreadTestTaskTwo {
    static final int LENGHTSEQUENCE = 15;
    private int currentNumber;
    private final BlockingQueue<String> queueNumbers = new LinkedBlockingQueue<>();
    public ThreadTestTaskTwo() {
        this.currentNumber = 1;
    }
    public void fizz() throws InterruptedException {
        while (true) {
            if (currentNumber > LENGHTSEQUENCE) {
                break;
            }
            if (currentNumber % 3 == 0 && currentNumber % 5 != 0) {
                queueNumbers.put("fizz");
                currentNumber++;
            }
        }
    }
    public void buzz() throws InterruptedException {
        while (true) {
            if (currentNumber > LENGHTSEQUENCE) {
                break;
            }
            if (currentNumber % 5 == 0 && currentNumber % 3 != 0) {
                queueNumbers.put("buzz");
                currentNumber++;
            }
        }
    }
    public void fizzbuzz() throws InterruptedException {
        while (true) {
            if (currentNumber > LENGHTSEQUENCE) {
                break;
            }
            if (currentNumber % 3 == 0 && currentNumber % 5 == 0) {
                queueNumbers.put("fizzbuzz");
                currentNumber++;
            }
        }
    }
    public void number() throws InterruptedException {
        while (true) {
            if (currentNumber > LENGHTSEQUENCE) {
                break;
            }
            if (currentNumber % 3 != 0 && currentNumber % 5 != 0) {
                queueNumbers.put(String.valueOf(currentNumber));
                currentNumber++;
            }
        }
    }

    public static void main(String[] args) {
        ThreadTestTaskTwo fizzBuzz = new ThreadTestTaskTwo();
        ExecutorService service = Executors.newFixedThreadPool(4);
        Runnable runnableTaskA = () -> {
            try {
                fizzBuzz.fizz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable runnableTaskB = () -> {
            try {
                fizzBuzz.buzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable runnableTaskC = () -> {
            try {
                fizzBuzz.fizzbuzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable runnableTaskD = () -> {
            try {
                fizzBuzz.number();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        service.submit(runnableTaskA);
        service.submit(runnableTaskB);
        service.submit(runnableTaskC);
        service.submit(runnableTaskD);
        service.shutdown();
        try {
            service.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!fizzBuzz.queueNumbers.isEmpty()) {
            try {
                System.out.println(fizzBuzz.queueNumbers.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
