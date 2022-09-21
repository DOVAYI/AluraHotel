package dao;

import conexionBD.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.reservasModel;
import java.sql.Date;


public class reservaDao {

    private Conexion conexion;
    private PreparedStatement stm;
    private Connection conn;

    public reservaDao() {
        conexion = new Conexion();
        conn = conexion.retornaConexion();
    }
    
    public boolean guardar(reservasModel reserva) {
        boolean resp = false;
        Date fechaInicio = Date.valueOf(reserva.getFechaInicio());
        Date fechaFin = Date.valueOf(reserva.getFechaFin());
        try {
            stm = conn.prepareStatement("INSERT INTO reservas "
                    + "(inicio, fin, valortotal, formapago,idhuesped)"
                    + " VALUES (?, ?, ?, ?, ?)");

            try {
                stm.setDate(1, fechaInicio);
                stm.setDate(2, fechaFin);
                stm.setDouble(3, reserva.valorTotalReservas());
                stm.setString(4, reserva.getFormaPago());
                stm.setInt(5, reserva.getIdHuesped());

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
