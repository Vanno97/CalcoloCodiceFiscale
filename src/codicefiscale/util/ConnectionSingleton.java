package codicefiscale.util;

import com.sun.org.slf4j.internal.LoggerFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;
import com.sun.org.slf4j.internal.Logger;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe che gestisce la connesione al DataBase
 * @author Micael Vanini
 */
public class ConnectionSingleton {
    private static Connection connection;
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionSingleton.class);
    
    private ConnectionSingleton() {
        
    }
    
    public static Connection getInstance() {
        if(connection == null){
            try {
                Properties properties = new Properties();
                InputStream inputStream = new FileInputStream("config.properties");
                properties.load(inputStream);
                String vendor = properties.getProperty("db.vendor");
                String driver = properties.getProperty("db.driver");
                String params = properties.getProperty("db.jdbc_params");
                String host = properties.getProperty("db.host");
                String port = properties.getProperty("db.port");
                String name = properties.getProperty("db.name");
                String username = properties.getProperty("db.username");
                String password = properties.getProperty("db.password");
                Class c = Class.forName(driver);
                LOGGER.debug("Ho caricato: " + c.getName());
                String url = "jdbc:" + vendor + "://" + host + ":" + port + "/" + name + "?" + params;
                connection = DriverManager.getConnection(url, username, password);
            } catch (FileNotFoundException e) {
                LOGGER.error("Error opening configuration file", e);
            } catch (IOException e) {
                LOGGER.error("Error loading configuration", e);
            } catch (ClassNotFoundException e) {
                LOGGER.error("Unable to find driver class", e);
            } catch (SQLException e) {
                LOGGER.error("Error connecting database", e);
            }
        }
        return connection;
    }
}
