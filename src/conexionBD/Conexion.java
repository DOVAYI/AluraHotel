package conexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    private String server = "localhost:3306";
    private String user = "hotel";
    private String password = "Dovayi2021@Eli@Alf";
    private String bd = "hotel";//nombre de la base de datos
    private String url = "jdbc:mysql://" + server + "/" + bd;//"jdbc:mysql://localhost/"+bd;

    private Connection conn = null;

    public Conexion() {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException e) {
            System.err.println("error en la clase");
            Logger.getLogger(Conexion.class.getName()).log(Level.INFO, null, "no se encontro la clase");
        } catch (SQLException e) {
            System.out.println("error al conectar base de datos");

        }

    }

    public Connection retornaConexion() {
        return conn;
    }
}
