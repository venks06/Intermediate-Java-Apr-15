package concordance;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Concordance {
    public static final Pattern LINE_END = Pattern.compile("\\W+");
    public static final Comparator<Map.Entry<String, Long>> normalOrder = Map.Entry.comparingByValue();
    public static final Comparator<Map.Entry<String, Long>> reverseOrder = normalOrder.reversed();

    public static void main(String[] args) throws Throwable {
        try (Stream<String> in = Files.lines(Paths.get("PrideAndPrejudice.txt"));) {
            in
                    .flatMap(LINE_END::splitAsStream)
                    .filter(s -> !s.isEmpty())
                    .map(String::toLowerCase)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet().stream()
//                    .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
//                    .sorted(Comparator.comparing(Map.Entry::getValue, (v1, v2) -> v2.compareTo(v1)))
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .limit(200)
                    .map(e -> String.format("%20s : %5d", e.getKey(), e.getValue()))
                    .forEach(System.out::println);


        }
    }
}
