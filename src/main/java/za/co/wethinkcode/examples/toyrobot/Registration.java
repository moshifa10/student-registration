package za.co.wethinkcode.examples.toyrobot;

import java.time.LocalDate;

public class Registration {

    private Student student;
    private Course course;
    private LocalDate date;


    public Registration(Student student, Course course, LocalDate date) {
        this.student = student;
        this.course = course;
        this.date = date;
    }
    public Registration(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.date = LocalDate.now();
    }



    public Course getCourse() {
        return course;
    }

    public Student getStudent() {
        return student;
    }

    public LocalDate getDate() {
        return date;
    }
}
