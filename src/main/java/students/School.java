package students;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface StudentCriterion {
    boolean test(Student s);
}

class SmartCriterion implements StudentCriterion {
    @Override
    public boolean test(Student s) {
        return s.getGpa() > 3;
    }
}

class EnthusiasticCrtierion implements StudentCriterion {

    @Override
    public boolean test(Student s) {
        return s.getCourses().size() > 2;
    }
}

public class School {
    public static void showAll(List<Student> ls) {
        for (Student s : ls) {
            System.out.println("> " + s);
        }
        System.out.println("--------------------------------");
    }

    public static List<Student> getStudentsByCriterion(List<Student> ls, StudentCriterion crit) {
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
        showAll(getStudentsByCriterion(school, new SmartCriterion()));
        System.out.println("Enthusiastic");
        showAll(getStudentsByCriterion(school, new EnthusiasticCrtierion()));
    }
}
