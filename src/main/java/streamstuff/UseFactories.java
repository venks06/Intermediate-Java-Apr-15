package streamstuff;

import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class UseFactories {
    public static void main(String[] args) {
        System.out.println("Everything less than 0.0? "
                + Stream.generate(() -> Math.random())
                .peek(x -> System.out.println(x))
                .allMatch(x -> x < 0.9));
//                .forEach(s -> System.out.println(s));

        System.out.println("Everything less than 10? " +
        DoubleStream.iterate(0.0, x -> x + 0.5)
                .limit(8)
                .peek(x -> System.out.println(x))
                .allMatch(x -> x < 10));
//                .forEach(s -> System.out.println(s));
    }
}
