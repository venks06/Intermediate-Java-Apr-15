package collectorstuff;

import students.Student;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UseCollectors {
    public static String getLetterGrade(Student s) {
        double gpa = s.getGpa();
        if (gpa > 3.7) return "A";
        if (gpa > 3.5) return "B";
        if (gpa > 3) return "C";
        if (gpa > 2.5) return "D";
        return "E";
    }
    public static void main(String[] args) {
        List<Student> school = Arrays.asList(
                Student.of("Fred", 2.7, "Math", "Physics", "Chemistry"),
                Student.of("Freddy", 2.9, "Math", "Chemistry"),
                Student.of("Ferdinand", 2.8, "Physics", "Chemistry"),
                Student.of("Filipe", 3.2, "Chemistry", "Algebra"),
                Student.of("Jim", 3.7, "Art"),
                Student.of("Sheila", 3.9, "Math", "Physics", "Astronomy", "Quantum Mechanics")
        );
        Map<String, List<Student>> results = school.stream()
                .collect(Collectors.groupingBy(UseCollectors::getLetterGrade));
//        results.entrySet().stream()
        results.forEach((k, v) -> System.out.println("Students with grade " + k + " are " + v));
    }
}
