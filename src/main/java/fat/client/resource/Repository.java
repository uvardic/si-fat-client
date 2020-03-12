package fat.client.resource;

import fat.client.resource.visitor.ResourceVisitor;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Repository extends Resource {

    private static final String SERVER = "64.225.110.65";

    private static final String NAME = "tim_401_5_si2019";

    private static final String USERNAME = "tim_401_5_si2019";

    private static final String PASSWORD = "G89dksXH";

    private static Connection connection;

    public Repository() {
        super(NAME, null);
    }

    public static Connection getConnection() {
        if (connection != null)
            return connection;

        return connect();
    }

    private static Connection connect() {
        try {
            connection = DriverManager.getConnection(
                    String.format("jdbc:mysql://%s/%s?user=%s&password=%s", SERVER, NAME, USERNAME, PASSWORD)
            );
            System.out.println("Connected to database");
            return connection;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        throw new RuntimeException("Exception while connecting to database!");
    }

    public static DatabaseMetaData getMetaData() {
        try {
            return getConnection().getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Exception while getting database meta data!");
    }

    @Override
    public void acceptVisitor(ResourceVisitor visitor) {
        visitor.visit(this);
    }

}
