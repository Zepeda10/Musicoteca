
package modelo;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;


public class Conexion {
    Connection conn;
    public Connection getConnection(){
        String url = "jdbc:mysql://localhost:3306/musicoteca";
        String user = "root";
        String pass = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url,user,pass);
        }catch(Exception e){
            
        }
        return conn;
    }
}
