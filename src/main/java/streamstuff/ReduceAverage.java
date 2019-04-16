package streamstuff;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ReduceAverage {
    public static void main(String[] args) {
        long start = System.nanoTime();
        long [] av = Stream.generate(() -> ThreadLocalRandom.current().nextInt(1, 11))
                .parallel()
                .limit(2_000_000_000)
                .reduce(new long[2],
                        (ab, i) -> new long[]{ ab[0] + i, ab[1] + 1},
                        (abf, ab) -> new long[]{ abf[0] + ab[0], abf[1] + ab[1]});
        long totalTime = System.nanoTime() - start;
        System.out.println("Average is " + ((double)av[0]/av[1])
                + " time is " + (totalTime / 1_000_000_000.0 + " seconds"));
//                .forEach(s -> System.out.println(s));
    }
}
