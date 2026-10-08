package za.co.wethinkcode.examples.toyrobot;

import java.sql.*;
import java.util.List;

public class Database {


    private Connection connection;
    private final String DB_URL = "JDBC:sqlite:college.db";


    public Database(){
        this.Dbconnect();
    }

    public Connection getConnection() {
        return connection;
    }

    private void Dbconnect(){

        try {
            connection =  DriverManager.getConnection(DB_URL);
            System.out.println("Created a database Called " + DB_URL);
            boolean tablesCreated = this.createTables(connection);
            if (!tablesCreated){
                System.out.println("Couldn't create the tables");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean createTables(Connection connection){

        try {
            String table1 = "CREATE TABLE IF NOT EXISTS students (student_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, email TEXT NOT NULL UNIQUE)";
            String table2 = "CREATE TABLE IF NOT EXISTS courses (course_id INTEGER PRIMARY KEY AUTOINCREMENT, course_name TEXT NOT NULL)";
            String table3 = "CREATE TABLE IF NOT EXISTS registrations (student_id INTEGER , course_id INTEGER, registration_date TEXT NOT NULL,FOREIGN KEY (student_id) REFERENCES students(student_id), FOREIGN KEY (course_id) REFERENCES courses(course_id))";

            PreparedStatement statement1 = connection.prepareStatement(table1);
            PreparedStatement statement2 = connection.prepareStatement(table2);
            PreparedStatement statement3 = connection.prepareStatement(table3);

            boolean resultSet1 = statement1.execute();
            boolean resultSet2 = statement2.execute();
            boolean resultSet3 = statement3.execute();

            List<Boolean> resultSets = List.of(resultSet1,resultSet2,resultSet3);
            for (int i=0; i<resultSets.size(); i++){
                if (resultSets.get(i)){
                    return false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return  false;
        }
        return true;
    }
}
