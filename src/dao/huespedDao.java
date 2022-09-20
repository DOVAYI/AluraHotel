/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexionBD.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.huesped;

public class huespedDao {

    private Conexion conexion;
    private PreparedStatement stm;
    private Connection conn;

    public huespedDao() {
        conexion = new Conexion();
        conn = conexion.retornaConexion();
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

    public void cerrarConexion() {
        try {
            this.stm.close();

            this.conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al cerrar la Conexion a la Base de Datos");
        }
    }

}
//"SELECT existencia FROM inventario WHERE id_inv=(SELECT MAX(id_inv) FROM inventario WHERE codigo_prod='"
