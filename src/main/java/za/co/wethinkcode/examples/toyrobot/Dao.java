package za.co.wethinkcode.examples.toyrobot;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.List;

public class Dao {

    private Connection connection;

    public Dao(Connection connection){
        this.connection = connection;
    }


    public boolean insertStudent(Student student){

        try {
            String sql = "INSERT INTO students (name, email) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if(resultSet.next()){
                int newId = resultSet.getInt(1);
                student.setId(newId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public  boolean insertStudents(List<Student> students){

        for (Student student : students) {
            try {
                String sql = "INSERT INTO students (name, email) VALUES (?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                preparedStatement.setString(1, student.getName());
                preparedStatement.setString(2, student.getEmail());

                preparedStatement.executeUpdate();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();

                if (resultSet.next()) {
                    int newId = resultSet.getInt(1);
                    student.setId(newId);
                }


            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        System.out.println("added new Students");
        return true;
    }


    public boolean insertCourse(Course course){

        try {
            String sql = "INSERT INTO courses (course_name) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, course.getCourseName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if(resultSet.next()){
                int newId = resultSet.getInt(1);
                course.setCourseId(newId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertCourses(List<Course> courses){
        for (Course course : courses)
            try {
                String sql = "INSERT INTO courses (course_name) VALUES (?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                preparedStatement.setString(1, course.getCourseName());
                preparedStatement.executeUpdate();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();

                if(resultSet.next()){
                    int newId = resultSet.getInt(1);
                    course.setCourseId(newId);
                }

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

        System.out.println("Added new Courses");
        return true;
    }


    public boolean insertRegistration(Registration registration){

        try {
            String sql = "INSERT INTO registrations (student_id, course_id, registration_date) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, registration.getStudent().getId());
            preparedStatement.setInt(2, registration.getCourse().getCourseId());
            preparedStatement.setString(3, registration.getDate().toString());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertRegistrations(List<Registration> registrations){
        for (Registration registration : registrations){
            try {
                String sql = "INSERT INTO registrations (student_id, course_id, registration_date) VALUES (?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                preparedStatement.setInt(1, registration.getStudent().getId());
                preparedStatement.setInt(2, registration.getCourse().getCourseId());
                preparedStatement.setString(3, registration.getDate().toString());
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public ResultSet getCourses(){
        try {
            Path path = Path.of("./sql/courses.sql");

            String sql = Files.readString(path);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            return preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public ResultSet getStudents(){
        try {
            Path path = Path.of("./sql/students.sql");

            String sql = Files.readString(path);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            return preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public ResultSet getRegistration(){
        try {
            Path path = Path.of("./sql/registrations.sql");

            String sql = Files.readString(path);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            return preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public ResultSet registeredStudentsPlusCourseName(){
        try {
            Path path = Path.of("./sql/registered_students_and_courses.sql");

            String sql = Files.readString(path);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            return preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
