package opt;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UseOptional {
    public static void main(String[] args) {
        Map<String, String> names = new HashMap<>();
        names.put("Fred", "Jones");

        final String FIRST = "Freddy";

        try {
            String lastname = names.get(FIRST);
            String shout = lastname.toUpperCase();
            String address = "Mr. " + shout;
            System.out.println(address);
        } catch (NullPointerException npe) {
            System.out.println("Uh oh!!!");
        }
        System.out.println("-------------------------");
        Optional.of(names)
                .map(x -> x.get(FIRST))
                .map(x -> x.toUpperCase())
                .map(x -> "Mr. " + x)
                .ifPresent(s -> System.out.println(s));
        System.out.println("-------------------------");

        String result = Optional.of(names)
                .map(x -> x.get(FIRST))
                .map(x -> x.toUpperCase())
                .map(x -> "Mr. " + x)
                .orElse("There isn't anyone with that name");
        System.out.println(result);
    }
}
