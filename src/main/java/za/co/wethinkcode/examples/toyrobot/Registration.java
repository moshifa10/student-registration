package za.co.wethinkcode.examples.toyrobot;

public class Registration {

    private Student student;
    private Course course;


    public Registration(Student student, Course course) {
        this.student = student;
        this.course = course;

    }

    public Course getCourse() {
        return course;
    }

    public Student getStudent() {
        return student;
    }
}
