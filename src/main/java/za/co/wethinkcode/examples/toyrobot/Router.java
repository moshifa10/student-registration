package za.co.wethinkcode.examples.toyrobot;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Router {



    public static void main(String[] args) {

        Javalin app = Javalin.create().start(1999);

        app.get("/", context -> {
            context.result("Welcome to Students course and registrations");
        });

        app.get("/students", context -> {
            getStudents(context);
        });
        app.get("/courses", Router::getCourses);
        app.get("/registrations", Router::getRegistrations);
        app.post("/students", Router::createStudent);

    }


    private static void getStudents(Context context){
        context.contentType("application/json");

        Dao dao = getConnection();
        ResultSet resultSet = dao.getStudents();
        List<Student> students = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("student_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");

                Student student = new Student(name, email);
                student.setId(id);
                students.add(student);
            }
        } catch (SQLException e) {
            context.status(500).result("Database error");
            e.printStackTrace();
        }
        context.json(students);
    }
    private static void getCourses(Context context){
        context.contentType("application/json");

        Dao dao = getConnection();
        ResultSet resultSet = dao.getCourses();
        List<Course> courses = new ArrayList<>();

        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("course_id");
                String name = resultSet.getString("course_name");

                Course course = new Course(name);
                course.setCourseId(id);
                courses.add(course);

            }
        } catch (SQLException e) {
            context.status(500).result("Database error");
            e.printStackTrace();
        }
        context.json(courses);
    }


    private static void getRegistrations(Context context) {
        List<Map<String, Object>> registrations = new ArrayList<>();

        try (Connection connection = new Database().getConnection()) {
            if (connection == null) {
                context.status(500).json(Map.of("error", "Database connection failed"));
                return;
            }

            Dao dao = new Dao(connection);

            try (ResultSet resultSet = dao.registeredStudentsPlusCourseName()) {
                if (resultSet == null) {
                    context.status(500).json(Map.of("error", "Database query failed"));
                    return;
                }

                while (resultSet.next()) {
                    Map<String, Object> registration = new LinkedHashMap<>();

                    registration.put(
                            "studentName",
                            resultSet.getString("name")
                    );

                    registration.put(
                            "courseName",
                            resultSet.getString("course_name")
                    );

                    registration.put(
                            "registrationDate",
                            resultSet.getString("registration_date")
                    );

                    registrations.add(registration);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            context.status(500).json(Map.of("error", "Database error"));
            return;
        }

        context.json(registrations);
    }

    private static void createStudent(Context context) {
        String name = context.formParam("name");
        String email = context.formParam("email");

        if (name == null || name.isBlank()
                || email == null || email.isBlank()) {
            context.status(400).json(
                    Map.of("error", "Name and email are required")
            );
            return;
        }

        Student student = new Student(name.trim(), email.trim());

        try (Connection connection = new Database().getConnection()) {
            if (connection == null) {
                context.status(500).json(
                        Map.of("error", "Database connection failed")
                );
                return;
            }

            Dao dao = new Dao(connection);
            boolean saved = dao.insertStudent(student);

            if (!saved) {
                context.status(500).json(
                        Map.of("error", "Could not save student")
                );
                return;
            }

            context.status(201).json(student);

        } catch (SQLException e) {
            e.printStackTrace();
            context.status(500).json(
                    Map.of("error", "Database error")
            );
        }
    }

    private static Dao getConnection(){
        return new Dao(new Database().getConnection());
    }

}
