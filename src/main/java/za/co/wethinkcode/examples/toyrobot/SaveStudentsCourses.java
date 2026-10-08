package za.co.wethinkcode.examples.toyrobot;

import java.util.List;

public class SaveStudentsCourses {

    private List<Student> students;
    private  List<Course> courses;

    public SaveStudentsCourses(List<Student> students, List<Course> courses){

        this.students = students;
        this.courses = courses;
    }

    public void addStudent(Student newStudent){
        students.add(newStudent);
    }

    public void addCourse(Course course){
        courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Student> getStudents() {
        return students;
    }


}
