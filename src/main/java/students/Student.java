package students;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Student {
    private String name;
    private double gpa;
    private List<String> courses;

    private Student() {
    }

    public static Student of(String name, double gpa, String... courses) {
        validate(name, gpa, courses);
        Student self = new Student();
        self.name = name;
        self.gpa = gpa;
        self.courses = Arrays.asList(courses.clone());
        return self;
    }

    private static class Builder {
        private Student self = new Student();

        private Builder() {
            self.courses = new ArrayList<>();
        }

        public Builder name(String name) {
            self.name = name;
            return this;
        }

        public Builder gpa(double gpa) {
            self.gpa = gpa;
            return this;
        }

        // need to handle courses...

        public Student build() {
            validate(self.name, self.gpa, self.courses.toArray(new String[]{}));
            Student rv = self;
            self = null;
            return rv;
        }
    }

    public static void validate(String name, double gpa, String... courses) {
        if (name == null) throw new IllegalArgumentException("Must have non-null name");
    }

//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setGpa(double gpa) {
//        this.gpa = gpa;
//    }
//
//    public void setCourses(List<String> courses) {
//        this.courses = courses;
//    }

    public Student addCourse(String course) {
        List<String> newCourses = new ArrayList<>(this.courses);
        newCourses.add(course);
        String[] newCourseArray = newCourses.toArray(new String[]{});
        return of(this.name, this.gpa, newCourseArray);
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    public List<String> getCourses() {
//        return Collections.unmodifiableList(courses);
        return courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", gpa=" + gpa +
                ", courses=" + courses +
                '}';
    }

    public static Predicate<Student> getSmartCriterion(final double threshold) {
        double modified = threshold + 1;
        modified++;
        System.out.println("Creating student criterion for threshold " + threshold);
        return (Student s) -> {
            System.out.println("Evaluating student " + s);
            return s.gpa > threshold;
        };
//        return (s) -> { return s.getGpa() > 3; };
//        return s -> { return s.getGpa() > 3; };
//        return s -> s.getGpa() > threshold ;
    }

//    public static Predicate getSmartCriterion() {
//        return /*new Predicate() {*/
////            @Override
//            /*public boolean test*/(Student s) -> {
//                return s.getGpa() > 3;
//            }
//        /*}*/;
//    }

//    public static Predicate getSmartCriterion() {
//        return new Predicate() {
//            @Override
//            public boolean test(Student s) {
//                return s.getGpa() > 3;
//            }
//        };
//    }

//    public static Predicate getSmartCriterion() {
//        return new /*SmartCriterion();
//        static class SmartCriterion implements*/ Predicate() {
//            @Override
//            public boolean test(Student s) {
//                return s.getGpa() > 3;
//            }
//        };
//    }

//    static class SmartCriterion implements Predicate {
//        @Override
//        public boolean test(Student s) {
//            return s.getGpa() > 3;
//        }
//    }

    private static final Predicate<Student> enthusiasticCriterion = new EnthusiasticCrtierion();

    public static Predicate<Student> getEnthusiasticCriterion() {
        return enthusiasticCriterion;
    }

    static class EnthusiasticCrtierion implements Predicate<Student> {
        @Override
        public boolean test(Student s) {
            return s.getCourses().size() > 2;
        }
    }
}
