package space.harbour.hw9.executor;

import space.harbour.hw9.dataSets.CourseDataSet;
import space.harbour.hw9.dataSets.StudentDataSet;
import space.harbour.hw9.executor.SimpleExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PreparedExecutor extends SimpleExecutor {

    public PreparedExecutor(Connection connection) {
        super(connection);
    }

    public void execUpdateStudents(List<StudentDataSet> students) {
        try {
            getConnection().setAutoCommit(false);
            String update = "insert into students(id, user_name) values(?,?)";
            PreparedStatement stmt = getConnection().prepareStatement(update);

            for (StudentDataSet s: students) {
                stmt.setLong(1, s.getId());
                stmt.setString(2, s.getName());
                stmt.executeUpdate();
            }

            getConnection().commit();
            getConnection().setAutoCommit(true);
            stmt.close();
        } catch (SQLException e) {
            try {
                getConnection().rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void execUpdateCourses(List<CourseDataSet> courses) {
        try {
            getConnection().setAutoCommit(false);
            String update = "insert into courses(id, course_name, year) values(?,?,?)";
            PreparedStatement stmt = getConnection().prepareStatement(update);

            for (CourseDataSet c : courses) {
                stmt.setLong(1, c.getId());
                stmt.setString(2, c.getName());
                stmt.setInt(3, c.getYear());
                stmt.executeUpdate();
            }

            getConnection().commit();
            getConnection().setAutoCommit(true);
            stmt.close();
        } catch (SQLException e) {
            try {
                getConnection().rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
