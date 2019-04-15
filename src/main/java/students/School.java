package students;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface StudentCriterion {
    boolean test(Student s);
}

interface Strange {
    boolean doStuff(Student s);
}

public class School {

    public static StudentCriterion negate(StudentCriterion crit) {
        return s -> {
            System.out.println("performing negated test");
            return !crit.test(s);
        };
    }

    public static void showAll(List<Student> ls) {
        for (Student s : ls) {
            System.out.println("> " + s);
        }
        System.out.println("--------------------------------");
    }

    public static List<Student> getStudentsByCriterion(List<Student> ls, StudentCriterion crit) {
        System.out.println("in getByCriterion");
        List<Student> out = new ArrayList<>();
        for (Student s : ls) {
            if (crit.test(s)) {
                out.add(s);
            }
        }
        return out;
    }

//    public static List<Student> getStudentsByCriterion(List<Student> ls, double threshold) {
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
//        showAll(getStudentsByCriterion(school, 3));
//        System.out.println("Enthusiastic");
//        showAll(getEnthusiasticStudents(school, 2));

        System.out.println("Smart");
        showAll(getStudentsByCriterion(school, Student.getSmartCriterion(2.5)));
        System.out.println("Enthusiastic");
        showAll(getStudentsByCriterion(school, Student.getEnthusiasticCriterion()));

        System.out.println("Un-enthusiastic");
        showAll(getStudentsByCriterion(school, s -> s.getCourses().size() < 3));

        StudentCriterion obj;
        obj = s -> s.getGpa() > 3;
        boolean b1 = ((Strange)(s -> s.getGpa() > 3)).doStuff(Student.of("Albert", 3.5));
        boolean b2 = ((StudentCriterion)(s -> s.getGpa() > 3)).test(Student.of("Albert", 3.5));

        System.out.println("b1 is " + b1);
        System.out.println("b2 is " + b2);

        System.out.println("Smart");
        showAll(getStudentsByCriterion(school, Student.getSmartCriterion(2.5)));
        System.out.println("Enthusiastic");
        showAll(getStudentsByCriterion(school, Student.getEnthusiasticCriterion()));

        System.out.println("not Smart");
        showAll(getStudentsByCriterion(school, negate(Student.getSmartCriterion(2.5))));
        System.out.println("not Enthusiastic");
        showAll(getStudentsByCriterion(school, negate(Student.getEnthusiasticCriterion())));

    }
}
