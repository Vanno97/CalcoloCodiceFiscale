package codicefiscale.dao;

import codicefiscale.util.ConnectionSingleton;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Questa classe si collegga al Database per recuperare i codici dei comuni
 * @author Micael Vanini
 */
public class ComuneDAO {
    private final Logger LOGGER;
    private final String READ_FROM_COMUNE = "SELECT * FROM comune WHERE nome = ?";
    
    public ComuneDAO() {
        LOGGER = LoggerFactory.getLogger(this.getClass());
    }
    
    public String readFromSigla(String nome){
        Connection connection = ConnectionSingleton.getInstance();
        String codiceComune = null;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(READ_FROM_COMUNE);
            preparedStatement.setString(1, nome);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            codiceComune = resultSet.getString("codice");
        } catch (SQLException e) {
            this.LOGGER.error("Error retrieving 'codice comune'", e);
        }
        return codiceComune;
    }
}
