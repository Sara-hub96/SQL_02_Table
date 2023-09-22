package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newdb", "admin", "admin");
            Statement statement = conn.createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS students(" +
                    "student_id INT NOT NULL AUTO_INCREMENT," +
                    "last_name VARCHAR(30)," +
                    "first_name VARCHAR(30)," +
                    "PRIMARY KEY (student_id)" +
                    ")");

            String[] students = {
                    "INSERT INTO newdb.students (last_name, first_name) VALUES ('Rossi', 'Luca');",
                    "INSERT INTO newdb.students (last_name, first_name) VALUES ('Bianchi', 'Maria');",
                    "INSERT INTO newdb.students (last_name, first_name) VALUES ('Verdi', 'Luisa');",
                    "INSERT INTO newdb.students (last_name, first_name) VALUES ('Neri', 'Marco');"
            };

            for (String insertStudents : students) {
                statement.execute(insertStudents);
            }

            ResultSet rs = statement.executeQuery("SELECT * FROM students;");
            System.out.println("id" + "\t" + "last name" + "\t" + "first name;");

            while (rs.next()) {
                int id = rs.getInt("student_id");
                String lastName = rs.getString("last_name");
                String firstName = rs.getString("first_name");
                System.out.println(id + "\t" + lastName + "\t" + firstName);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
