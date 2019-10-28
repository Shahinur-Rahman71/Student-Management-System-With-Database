
package fx24.loginwithdbsuper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Dbconnect {
    
    private Dbconnect(){
        
    }
    
    public static Dbconnect getInstance(){
        
        return new Dbconnect();
    }
    public static Connection connection;
    public Connection getConnection(){
        
        try {
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/shanto","root","");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
        
    }
    
}
