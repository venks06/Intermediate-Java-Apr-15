package students;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@FunctionalInterface
interface Strange {
//    void doStuff();
    boolean doStuff(Student s);
}

public class School {

//    public static Predicate negate(Predicate crit) {
//        return s -> {
//            System.out.println("performing negated test");
//            return !crit.test(s);
//        };
//    }
//
//    public static Predicate and(Predicate a, Predicate b) {
//        return s -> a.test(s) && b.test(s);
//    }
//
//    public static Predicate or(Predicate a, Predicate b) {
//        return s -> a.test(s) || b.test(s);
//    }

    public static <E> void showAll(List<E> ls) {
        for (E s : ls) {
            System.out.println("> " + s);
        }
        System.out.println("--------------------------------");
    }

    public static <E> List<E> getByCriterion(List<E> ls, Predicate<E> crit) {
        System.out.println("in getByCriterion");
        List<E> out = new ArrayList<>();
        for (E s : ls) {
            if (crit.test(s)) {
                out.add(s);
            }
        }
        return out;
    }

//    public static List<Student> getByCriterion(List<Student> ls, double threshold) {
//        List<Student> out = new ArrayList<>();
//        for (Student s : ls) {
//            if (s.getGpa() > threshold) {
//                out.add(s);
//            }
//        }
//        return out;
//    }
//
//    public static List<Student> getEnthusiasticStudents(List<Student> ls, int threshold) {
//        List<Student> out = new ArrayList<>();
//        for (Student s : ls) {
//            if (s.getCourses().size() > threshold) {
//                out.add(s);
//            }
//        }
//        return out;
//    }
//
    public static void main(String[] args) {
        List<Student> school = Arrays.asList(
                Student.of("Fred", 2.7, "Math", "Physics", "Chemistry"),
                Student.of("Jim", 3.7, "Art"),
                Student.of("Sheila", 3.9, "Math", "Physics", "Astronomy", "Quantum Mechanics")
        );

        System.out.println("All");
        showAll(school);
//        System.out.println("Smart");
//        showAll(getByCriterion(school, 3));
//        System.out.println("Enthusiastic");
//        showAll(getEnthusiasticStudents(school, 2));

        System.out.println("Smart");
        showAll(getByCriterion(school, Student.getSmartCriterion(2.5)));
        System.out.println("Enthusiastic");
        showAll(getByCriterion(school, Student.getEnthusiasticCriterion()));

        System.out.println("Un-enthusiastic");
        showAll(getByCriterion(school, s -> s.getCourses().size() < 3));

        Predicate<Student> obj;
        obj = s -> s.getGpa() > 3;
        boolean b1 = ((Strange)(s -> s.getGpa() > 3)).doStuff(Student.of("Albert", 3.5));
        boolean b2 = ((Predicate<Student>)(s -> s.getGpa() > 3)).test(Student.of("Albert", 3.5));

        System.out.println("b1 is " + b1);
        System.out.println("b2 is " + b2);

        System.out.println("Smart");
        showAll(getByCriterion(school, Student.getSmartCriterion(2.5)));
        System.out.println("Enthusiastic");
        showAll(getByCriterion(school, Student.getEnthusiasticCriterion()));

//        System.out.println("not Smart");
//        showAll(getByCriterion(school,
//                Predicate.negate(Student.getSmartCriterion(2.5))));
//        System.out.println("not Enthusiastic");
//        showAll(getByCriterion(school,
//                Predicate.negate(Student.getEnthusiasticCriterion())));
//        System.out.println("Smart and not enthusiastic");
//        showAll(getByCriterion(school,
//                Predicate.and(Student.getSmartCriterion(3),
//                        Predicate.negate(Student.getEnthusiasticCriterion()))));


        System.out.println("not Smart");
        showAll(getByCriterion(school,
                Student.getSmartCriterion(2.5).negate()));
        System.out.println("not Enthusiastic");
        showAll(getByCriterion(school,
               Student.getEnthusiasticCriterion().negate()));
        System.out.println("Smart and not enthusiastic");
        showAll(getByCriterion(school,
                Student.getSmartCriterion(3)
                        .and(Student.getEnthusiasticCriterion().negate())));

        List<String> names = Arrays.asList("Albert", "Fred", "Sheila", "Jim", "William");
        showAll(getByCriterion(names, st -> st.length() > 4));
    }
}