package montecarlo;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NormalDist {
    public static String makeStars(long length) {
        return Stream.generate(() -> "*").limit(length).collect(Collectors.joining());
    }

    public static IntStream diceThrows() {
        return IntStream.generate(
                () -> IntStream.generate(() -> ThreadLocalRandom.current().nextInt(1, 7))
                        .limit(10).sum()
        );
    }

    public static void main(String[] args) {
        Map<Integer, Long> data = diceThrows()
                .limit(1_000_000)
                .mapToObj(x -> x)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        long longest = data.entrySet().stream().mapToLong(Map.Entry::getValue).max().getAsLong();

        data.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> String.format("%2d [%5d]: %s",
                        e.getKey(), e.getValue(), makeStars(80 * e.getValue() / longest)))
                .forEachOrdered(System.out::println);
    }
}
