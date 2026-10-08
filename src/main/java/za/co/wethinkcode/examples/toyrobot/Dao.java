package za.co.wethinkcode.examples.toyrobot;

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
            preparedStatement.setString(1, student.getEmail());

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
                preparedStatement.setString(1, student.getEmail());

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
        return true;
    }


    public boolean insertCourse(Course course){

        try {
            String sql = "INSERT INTO courses (name) VALUES (?)";
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
                String sql = "INSERT INTO courses (name) VALUES (?)";
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

    public boolean insertRegistrations(Registration registration){

    }



}
