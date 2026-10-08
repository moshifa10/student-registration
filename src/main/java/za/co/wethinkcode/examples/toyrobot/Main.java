package za.co.wethinkcode.examples.toyrobot;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Student> students = List.of(
                new Student("Njabs", "moshifa@"),
                new Student("Busa", "busa@"),
                new Student("Stha", "stha@")
        );

        List<Course> courses = List.of(
                new Course("Accounting"),
                new Course("Software Engineering")
        );

        SaveStudentsCourses saveStudentsCourses = new SaveStudentsCourses(students, courses);

        Database database = new Database();

        Connection connection = database.getConnection();

        Dao dao = new Dao(connection);
        dao.insertStudents(saveStudentsCourses.getStudents());
        dao.insertCourses(saveStudentsCourses.getCourses());

        dao.insertRegistration(new Registration(students.get(0), courses.get(1)));

        ResultSet resultSet = dao.getStudents();

        System.out.println("Students");
        try {
            while (resultSet.next()){
                int id = resultSet.getInt("student_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");

                System.out.println(id);
                System.out.println(name);
                System.out.println(email);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet resultSet3 = dao.getCourses();
        System.out.println("\n\n\nCourses");
        try {
            while (resultSet3.next()){
                int id = resultSet3.getInt("course_id");
                String name = resultSet3.getString("course_name");

                System.out.println(id);
                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet resultSet2 = dao.getRegistration();
        System.out.println("\n\n\nQueryRegistrations");
        try {
            while (resultSet2.next()){
                int id = resultSet2.getInt("student_id");
                String name = resultSet2.getString("course_id");
                String email = resultSet2.getString("registration_date");

                System.out.println(id);
                System.out.println(name);
                System.out.println(email);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}