package dao;

import conexionBD.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.usuariosModel;
import utilidades.encriptar;
import utilidades.mostrarMensaje;

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
        if (buscarUsuario(usuarios.getUsuario())) {
            mostrarMensaje.mensaje();
            return resp;
        } else {
            try {

                stm = conn.prepareStatement("INSERT INTO usuarios "
                        + "(usuario,password)"
                        + " VALUES (?, ?)");

                try {
                    stm.setString(1, usuarios.getUsuario());
                    stm.setString(2, encriptar.getHash(usuarios.getPassword()));
                    stm.execute();
                    resp = true;

                } catch (Exception e) {
                    System.out.println("error en prepared statement " + e.getMessage());
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return resp;
    }

    public boolean validarAcceso(usuariosModel usuarios) {
        boolean rsp = false;
        String password = encriptar.getHash(usuarios.getPassword());
        try {
            final PreparedStatement statement = conn
                    .prepareStatement("SELECT usuario,password FROM usuarios "
                            + "WHERE usuario='" + usuarios.getUsuario() + "' "
                            + "AND password='" + password + "'");

            try {
                statement.execute();
                final ResultSet resultSet = statement.getResultSet();

                if (resultSet != null) {

                    while (resultSet.next()) {
                        rsp = true;
                    }
                }

            } catch (SQLException ex) {
                System.out.println("error en resulset " + ex.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("error en validar Acceso " + e.getMessage());
        }

        return rsp;
    }

    private boolean buscarUsuario(String usuario) {
        boolean rsp = false;
        try {
            final PreparedStatement statement = conn
                    .prepareStatement("SELECT usuario FROM usuarios "
                            + "WHERE usuario='" + usuario + "'");

            try {
                statement.execute();
                final ResultSet resultSet = statement.getResultSet();

                if (resultSet != null) {

                    while (resultSet.next()) {
                        rsp = true;
                    }
                }

            } catch (SQLException ex) {
                System.out.println("error en resulset " + ex.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("error en validar Acceso " + e.getMessage());
        }

        return rsp;
    }

    public boolean modificar(String usuario, String newPassword) {
        boolean rsp = false;
        try {
            final PreparedStatement statement = conn.prepareStatement(
                    "UPDATE usuarios SET "
                    + " password = ? "
                    + " WHERE usuario= ?");

            statement.setString(1, encriptar.getHash(newPassword));
            statement.setString(2, usuario);
            statement.execute();
            rsp = true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rsp;
    }

    public void cerrarConexion(int statement, int conexion) {
        try {
            if (statement == 1 && conexion == 1) {
                this.stm.close();
                this.conn.close();
            } else if (statement == 0 && conexion == 1) {
                this.conn.close();
            }

        } catch (SQLException e) {
            System.out.println("Error al cerrar la Conexion a la Base de Datos");
        }
    }
}
