package za.co.wethinkcode.examples.toyrobot;

public class Course {

    private int courseId = -1;
    private String courseName;

    public Course(String courseName){
        this.courseName = courseName;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return this.getCourseId() + " " + this.getCourseName();
    }
}
