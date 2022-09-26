package dao;

import conexionBD.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.reservasModel;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utilidades.convertirFechas;

public class reservaDao {

    private Conexion conexion;
    private PreparedStatement stm;
    private Connection conn;

    public reservaDao() {
        conexion = new Conexion();
        conn = conexion.retornaConexion();
    }

    

    public List<reservasModel> listar(int identificacion) {
        List<reservasModel> resultado = new ArrayList<>();
        String sql = "";
        try {
            if (identificacion == 0) {
                sql = "SELECT inicio,fin,valortotal,formapago FROM reservas";
            } else {
                sql = "SELECT inicio,fin,valortotal,formapago FROM reservas WHERE idhuesped=" + identificacion;
            }
            final PreparedStatement statement = conn
                    .prepareStatement(sql);

            try {
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                try {
                    while (resultSet.next()) {
                        resultado.add(new reservasModel(
                                convertirFechas.fechasConvertir(resultSet.getDate("inicio")),
                                convertirFechas.fechasConvertir(resultSet.getDate("fin")),
                                resultSet.getString("formapago"),
                                resultSet.getDouble("valortotal")));
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
