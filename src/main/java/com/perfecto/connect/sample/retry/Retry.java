package com.perfecto.connect.sample.retry;

import java.time.Duration;

/**
 * Created by nashati on 18/12/2017.
 */
public class Retry {

    public static <R, E extends Throwable> R perform(Executable<R, E> executable, int retries, Duration duration) throws E {

        do {
            try {
                System.out.println("Executing task");
                return executable.execute();

            } catch (Throwable e) {
                if (--retries == 0) {
                    System.out.println("Retry finished");
                    throw e;
                }

                System.out.println("Trying one more time");
                try {
                    Thread.sleep(duration.toMillis());
                } catch (InterruptedException e1) {
                    // ignore
                }
            }
        } while (true);
    }

    public interface Executable<R, E extends Throwable> {

        R execute() throws E;
    }
}
