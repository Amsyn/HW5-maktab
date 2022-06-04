package base.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    private final String username="root";
    private final String password="A@1234a";
    private final String url="jdbc:mysql://localhost:3306";
    private final Connection connection;


    public DataSource(Connection connection) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        this.connection = DriverManager.getConnection(url,username,password);

    }

    public Connection getConnection() {
        return connection;
    }
}
