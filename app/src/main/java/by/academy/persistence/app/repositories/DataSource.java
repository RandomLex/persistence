package by.academy.persistence.app.repositories;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
public class DataSource {
    private static volatile DataSource instance;

    private final String url;
    private final String user;
    private final String password;

    @SneakyThrows
    private DataSource() {
        Properties dbProperties = new Properties();
        dbProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
        url = dbProperties.getProperty("url");
        user = dbProperties.getProperty("user");
        password = dbProperties.getProperty("password");
    }


    public static DataSource getInstance() {
        if (instance == null) {
            synchronized (DataSource.class) {
                if (instance == null) {
                    instance = new DataSource();
                }
            }
        }
        return instance;
    }

    @SneakyThrows
    public Connection getConnection() throws SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url, user, password);
    }

}
