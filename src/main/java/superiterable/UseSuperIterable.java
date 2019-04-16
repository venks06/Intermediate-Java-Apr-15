package superiterable;

import students.Student;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

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

        System.out.println("-----------------------");
        schoolList.stream()
                .map(s -> s.toString())
                .forEach(s -> System.out.println(s));

        System.out.println("-----------------------");
        schoolList.stream()
                .map(s -> s.getName())
                .forEach(s -> System.out.println(s));

        System.out.println("-----------------------");
        schoolList.stream()
                .map(s -> s.getName() + " scored " + s.getGpa())
                .forEach(s -> System.out.println(s));

        System.out.println("-----------------------");
        schoolList.stream()
//                .filter(s -> s.getGpa() > 3)
                .filter(Student.getSmartCriterion(3))
                .map(s -> s.getName() + " scored " + s.getGpa())
                .forEach(s -> System.out.println(s));

        System.out.println("-----------------------");
        schoolList.stream()
                .map(s -> s.getGpa())
                .forEach(s -> System.out.println(s));

        System.out.println("-----------------------");
        schoolList.stream()
                .filter(Student.getSmartCriterion(3))
                .map(s -> s.getGpa())
                .sorted()
                .forEach(s -> System.out.println(s));

        System.out.println("-----------------------");
        schoolList.stream()
                .flatMap(s -> s.getCourses().stream().map(c -> s.getName() + " takes " + c))
                .forEach(s -> System.out.println(s));

        System.out.println("-----------------------");
        schoolList.stream()
                .peek(s -> System.out.println("peeking " + s))
//                .forEach(s -> System.out.println(s))
                ;

        System.out.println("-----------------------");
        school
                .peek(s -> System.out.println("peeking " + s))
//                .forEach(s -> System.out.println(s))
                ;


        Predicate<Student> isSmart = Student.getSmartCriterion(3);
        System.out.println("-----------------------");
        boolean allAreSmart = schoolList.stream()
//                .filter(isSmart)
                .allMatch(isSmart);
        System.out.println("Everyone is smart? " + allAreSmart);


        System.out.println("-----------------------");
        allAreSmart = schoolList.stream()
                .sorted((s1, s2) -> Double.compare(s2.getGpa(), s1.getGpa()))
//                .filter(isSmart)
                .allMatch(isSmart);
        System.out.println("Everyone is smart? " + allAreSmart);

    }
}
