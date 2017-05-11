package space.harbour.hw9;

import space.harbour.hw9.dataSets.CourseDataSet;
import space.harbour.hw9.dataSets.StudentDataSet;
import space.harbour.hw9.executor.PreparedExecutor;
import space.harbour.hw9.executor.SimpleExecutor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyDataSetExample {
    private Connection connection;

    public MyDataSetExample(Connection connection) {
        this.connection = connection;
        prepareData();
    }

    private void prepareData() {
        SimpleExecutor exec = new SimpleExecutor(connection);
        try {
            exec.execUpdate("drop table if exists students");
            exec.execUpdate("drop table if exists courses");

            exec.execUpdate("create table students (id serial, user_name varchar(256), primary key (id))");
            System.out.println("Table 'students' created");

            exec.execUpdate("create table courses (id serial, course_name varchar(256), year int, primary key (id))");
            System.out.println("Table 'courses' created");

            insertData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertData() {
        PreparedExecutor exec = new PreparedExecutor(connection);
        List<StudentDataSet> students = new ArrayList<>();
        students.add(new StudentDataSet(1, "Vova"));
        students.add(new StudentDataSet(2, "Artur"));
        students.add(new StudentDataSet(3, "Max"));
        students.add(new StudentDataSet(4, "Nami"));
        exec.execUpdateStudents(students);
        System.out.println("Users added");

        List<CourseDataSet> courses = new ArrayList<>();
        courses.add(new CourseDataSet(1, "Java", 2017));
        courses.add(new CourseDataSet(2, "C++", 2016));
        exec.execUpdateCourses(courses);
        System.out.println("Courses added");
    }

    public void runExample() {
        try {
            MyDAO myDAO = new MyDAO(connection);
            int id = 1;
            System.out.println(String.format("\nStudent for id = %d is", id));
            System.out.println(myDAO.getStudent(id));

            System.out.println("\nList of all students:");
            List<StudentDataSet> students = myDAO.getAllStudents();
            for (StudentDataSet student: students) {
                System.out.println(student);
            }

            System.out.println("\nList of all courses:");
            List<CourseDataSet> courses = myDAO.getAllCourses();
            for (CourseDataSet course: courses) {
                System.out.println(course);
            }

            System.out.println("\nDone!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
