package superiterable;

import students.Student;

import java.util.Arrays;
import java.util.List;

public class UseSuperIterable {
    public static void main(String[] args) {
        SuperIterable<Student> school = new SuperIterable(Arrays.asList(
                Student.of("Fred", 2.7, "Math", "Physics", "Chemistry"),
                Student.of("Jim", 3.7, "Art"),
                Student.of("Sheila", 3.9, "Math", "Physics", "Astronomy", "Quantum Mechanics")
        ));

        school
                .forEach(s -> System.out.println(">> " + s));

//        String s;
        school
                .filter(s -> s.getGpa() > 3)
                .map(s -> s.getName() + " has a gpa of " + s.getGpa())
                .forEach(s -> System.out.println("> " + s));

        school
//                .filter(s -> s.getGpa() > 3)
                .flatMap(s -> new SuperIterable<>(s.getCourses()))
                .unique()
                .forEach(s -> System.out.println("-- " + s));
    }
}
