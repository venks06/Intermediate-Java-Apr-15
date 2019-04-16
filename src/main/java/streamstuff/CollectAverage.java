package streamstuff;

import java.util.OptionalDouble;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;

class Average {
    private double sum = 0;
    private long count = 0;

    public Average() {}

    public void include(double d) {
        sum += d;
        count++;
    }

    public void merge(Average other) {
        this.sum += other.sum;
        this.count += other.count;
    }

    public OptionalDouble get() {
        if (count != 0) return OptionalDouble.of(sum/count);
        else return OptionalDouble.empty();
    }
}

public class CollectAverage {
    public static void main(String[] args) {
        long start = System.nanoTime();
        DoubleStream.generate(() -> ThreadLocalRandom.current().nextDouble(-Math.PI, Math.PI))
                .limit(1_500_000_000L)
                .map(x -> Math.sin(x))
                .parallel()
                .collect(() -> new Average(), (a, d) -> a.include(d), (af, a) -> af.merge(a))
                .get()
                .ifPresent(x -> System.out.println("Average is " + x));
        long time = System.nanoTime() - start;
        System.out.println("Total time was " + (time / 1_000_000_000.0) + " seconds");
    }
}
