package space.harbour.hw9;

import space.harbour.hw9.dataSets.CourseDataSet;
import space.harbour.hw9.dataSets.StudentDataSet;
import space.harbour.hw9.executor.TExecutor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyDAO {
    private Connection connection;

    public MyDAO(Connection connection) {
        this.connection = connection;
    }

    public StudentDataSet getStudent(long id) throws SQLException {
        TExecutor exec = new TExecutor(connection);
        return exec.execQuery("select * from students where id=" + id, result -> {
            result.next();
            return new StudentDataSet(result.getLong(1), result.getString(2));
        });
    }

    public List<StudentDataSet> getAllStudents() throws SQLException {
        TExecutor exec = new TExecutor(connection);
        return exec.execQuery("select * from students", result -> {
            List<StudentDataSet> students = new ArrayList<>();
            while (result.next()) {
                students.add(new StudentDataSet(result.getLong(1), result.getString(2)));
            }
            return students;
        });
    }

    public CourseDataSet getCourse(long id) throws SQLException {
        TExecutor exec = new TExecutor(connection);
        return exec.execQuery("select * from courses where id=" + id, result -> {
            result.next();
            return new CourseDataSet(result.getLong(1), result.getString(2), result.getInt(3));
        });
    }

    public List<CourseDataSet> getAllCourses() throws SQLException {
        TExecutor exec = new TExecutor(connection);
        return exec.execQuery("select * from courses", result -> {
            List<CourseDataSet> students = new ArrayList<>();
            while (result.next()) {
                students.add(new CourseDataSet(result.getLong(1), result.getString(2), result.getInt(3)));
            }
            return students;
        });
    }


}
