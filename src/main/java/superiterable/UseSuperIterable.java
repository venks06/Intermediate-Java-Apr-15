package superiterable;

import students.Student;

import java.util.Arrays;
import java.util.List;

public class UseSuperIterable {
    public static void main(String[] args) {
        List<Student> schoolList = Arrays.asList(
                Student.of("Fred", 2.7, "Math", "Physics", "Chemistry"),
                Student.of("Jim", 3.7, "Art"),
                Student.of("Sheila", 3.9, "Math", "Physics", "Astronomy", "Quantum Mechanics")
        );
        SuperIterable<Student> school = new SuperIterable(schoolList);

        school
                .forEach(s -> System.out.println(">> " + s));

//        String s;
        school
                .filter(s -> s.getGpa() > 3)
                .map(s -> s.getName() + " has a gpa of " + s.getGpa())
                .forEach(s -> System.out.println("> " + s));

        System.out.println("-----------------------");
        school
//                .filter(s -> s.getGpa() > 3)
                .flatMap(s -> new SuperIterable<>(s.getCourses()))
//                .distinct()
                .forEach(s -> System.out.println("-- " + s));

        System.out.println("-----------------------");
        schoolList.stream()
//                .filter(s -> s.getGpa() > 3)
                .flatMap(s -> s.getCourses().stream())
//                .distinct()
                .forEach(s -> System.out.println("-- " + s));
    }
}
