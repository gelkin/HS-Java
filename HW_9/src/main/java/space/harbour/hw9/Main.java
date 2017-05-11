package space.harbour.hw9;


import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    /**
     * Run on a machine:
     * $ psql -h localhost -d jdbc_playground -U junk_user
     */

    public static void main(String[] args) {
        ConnectionHelper.example();

        System.out.println("");
        try (Connection connection = ConnectionHelper.getConnection()) {
            MyDataSetExample x = new MyDataSetExample(connection);
            x.runExample();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
