package students;

public @FunctionalInterface
interface Predicate<E> {
    boolean test(E s);
    static <E> Predicate<E> negate(Predicate<E> crit) {
        return s -> {
            System.out.println("performing negated test");
            return !crit.test(s);
        };
    }

    static <E> Predicate<E> and(Predicate<E> a, Predicate<E> b) {
        return s -> a.test(s) && b.test(s);
    }

    static <E> Predicate<E> or(Predicate<E> a, Predicate<E> b) {
        return s -> a.test(s) || b.test(s);
    }

    default Predicate<E> negate() {
        return s -> !this.test(s);
    }

    default Predicate<E> and(Predicate<E> b) {
        return s -> this.test(s) && b.test(s);
    }

    default Predicate<E> or(Predicate<E> b) {
        return s -> this.test(s) || b.test(s);
    }
}


