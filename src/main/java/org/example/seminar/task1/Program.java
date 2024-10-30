package org.example.seminar.task1;

import org.example.seminar.Models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Program {

    private static final Random RANDOM = new Random();

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://students.db:3306/";
        String user = "root";
        String password = "123";

        // Подключение к базе данных
        try (Connection connection = DriverManager.getConnection(url, user, password);){

            // Создание БД
            createDatabase(connection);
            System.out.println("Database created successfully");

            // Использование БД
            useDatabase(connection);
            System.out.println("Using database studentDB ");

            // Создание таблицы
            createTable(connection);
            System.out.println("Table 'students' created successfully");

            // Добавление данных в таблицу
            int count = RANDOM.nextInt(5,11);
            for (int i = 0; i < count; i++) {
                insertData(connection, Student.create());
            }
            System.out.println("Insert data successfully");


            //Чтение данных
            Collection<Student> students = readData(connection);
            for (Student student : students) {
                System.out.println(student);
            }
            System.out.println("Read data successfully");

            // Обновление данных
            for (Student student : students) {
                student.updateName();
                student.updateAge();
                updateData(connection, student);
            }
            System.out.println("Student data updated successfully");

            //Чтение данных
            students = readData(connection);
            for (Student student : students) {
                System.out.println(student);
            }
            System.out.println("Read data successfully");

            // Удаление данных
            for (Student student : students) {
                deleteData(connection, student.getId());
            }
            System.out.println("Student data deleted successfully");

            /*// Закрытие соединения
            connection.close();
            System.out.println("Connection closed successfully");*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //region Вспомогательные методы

    private static void createDatabase(Connection connection) throws SQLException {
        String createDatabase = "CREATE DATABASE IF NOT EXISTS studentDB;";
        try (PreparedStatement statement = connection.prepareStatement(createDatabase)) {
            statement.execute();
        }
    }

    private static void useDatabase(Connection connection) throws SQLException {
        String useDatabase = "USE studentsDB;";
        try (PreparedStatement statement = connection.prepareStatement(useDatabase)) {
            statement.execute();
        }
    }

    private static void createTable(Connection connection) throws SQLException {
        String createTable =
                "CREATE TABLE IF NOT EXISTS students (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), age INT)";
        try (PreparedStatement statement = connection.prepareStatement(createTable)) {
            statement.execute();
        }
    }

    /**
     * Добавление данных в таблицу students
     * @param connection Соединение с БД
     * @param student Студент
     * @throws SQLException Исключение при выполнении запроса
     */
    private static void insertData(Connection connection, Student student) throws SQLException {
        String insertDataSQL = "INSERT INTO students (name, age) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertDataSQL)) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.executeUpdate();
        }
    }

    private static Collection<Student> readData(Connection connection) throws SQLException {
        ArrayList<Student> studentsList = new ArrayList<>();
        String readDataSQL = "SELECT * FROM students";
        try (PreparedStatement statement = connection.prepareStatement(readDataSQL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                studentsList.add(new Student(id, name, age));
            }
            return studentsList;
        }
    }


    private static void updateData(Connection connection, Student student) throws SQLException {
        String updateDataSQL = "UPDATE students SET name = ?, age = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(updateDataSQL)) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setInt(3, student.getId());
            statement.executeUpdate();
        }
    }

    private static void deleteData(Connection connection, int id) throws SQLException {
        String deleteDataSQL = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteDataSQL)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    //endregion
}
