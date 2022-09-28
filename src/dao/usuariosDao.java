package dao;

import conexionBD.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.usuariosModel;
import utilidades.encriptar;

public class usuariosDao {

    private Conexion conexion;
    private PreparedStatement stm;
    private Connection conn;

    public usuariosDao() {
        conexion = new Conexion();
        conn = conexion.retornaConexion();
    }

    public boolean guardar(usuariosModel usuarios) {
        boolean resp = false;

        try {

            stm = conn.prepareStatement("INSERT INTO usuarios "
                    + "(usuario,password)"
                    + " VALUES (?, MD5(?))");

            try {
                stm.setString(1, usuarios.getUsuario());
                stm.setString(2, usuarios.getPassword());
                stm.execute();
                resp = true;

            } catch (Exception e) {
                System.out.println("error en prepared statement " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resp;
    }
}
