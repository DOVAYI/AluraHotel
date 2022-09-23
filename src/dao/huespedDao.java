package dao;

import conexionBD.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.huesped;
import utilidades.convertirFechas;

public class huespedDao {

    private Conexion conexion;
    private PreparedStatement stm;
    private Connection conn;

    public huespedDao() {
        conexion = new Conexion();
        conn = conexion.retornaConexion();
    }

    public List<huesped> listar() {
        List<huesped> resultado = new ArrayList<>();

        try {
            final PreparedStatement statement = conn
                    .prepareStatement("SELECT identificacion,nombres,nacimiento,telefonos FROM huesped");

            try {
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                try {
                    while (resultSet.next()) {
                        resultado.add(new huesped(
                                resultSet.getInt("identificacion"),
                                resultSet.getString("nombres"),
                                convertirFechas.fechasConvertir(resultSet.getDate("nacimiento")),
                                resultSet.getString("telefonos")));
                    }
                } catch (Exception ex) {
                    System.out.println("error en resulset " + ex.getMessage());
                }
            } catch (Exception e) {
                System.out.println("error " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }

    public int buscarMaximoIdReserva() {
        int resultado = 0;

        try {
            final PreparedStatement statement = conn
                    .prepareStatement("SELECT idr FROM reservas WHERE idr=(SELECT MAX(idr) FROM reservas)");

            try {
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                while (resultSet.next()) {
                    resultado = resultSet.getInt("idr");
                }

            } catch (Exception e) {
                System.out.println("error  2 trycath " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }

    public boolean guardar(huesped huesped) {
        boolean resp = false;
        java.sql.Date nacido = java.sql.Date.valueOf(huesped.getNacimiento());
        try {
            stm = conn.prepareStatement("INSERT INTO huesped "
                    + "(identificacion, nombres, nacimiento, telefonos)"
                    + " VALUES (?, ?, ?, ?)");

            try {
                stm.setInt(1, huesped.getIdentificacion());
                stm.setString(2, huesped.getNombres());
                stm.setDate(3, nacido);
                stm.setString(4, huesped.getTelefonos());

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
