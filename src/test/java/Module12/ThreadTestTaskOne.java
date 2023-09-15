package Module12;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadTestTaskOne {

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        AtomicInteger timeLeft = new AtomicInteger();
        Runnable runnableTaskFirst = () -> {
            System.out.println(timeLeft.getAndIncrement() + " sec");
        };
        Runnable runnableTaskSecond = () -> {
            System.out.println("the next 5 seconds have passed");
        };
        Runnable runnableShutdown = () -> {
            service.shutdown();
        };
        service.scheduleWithFixedDelay(runnableTaskFirst, 0, 1, TimeUnit.SECONDS);
        service.scheduleWithFixedDelay(runnableTaskSecond, 6, 5, TimeUnit.SECONDS);
        service.schedule(runnableShutdown,17,TimeUnit.SECONDS);//shutdown in 17 second
    }
}
