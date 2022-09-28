package dao;

import conexionBD.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.reservasModel;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
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
                sql = "SELECT inicio,fin,valortotal,formapago,idhuesped FROM reservas";
            } else {
                sql = "SELECT inicio,fin,valortotal,formapago,idhuesped FROM reservas WHERE idhuesped=" + identificacion;
            }
            final PreparedStatement statement = conn
                    .prepareStatement(sql);

            try {
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                try {
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            resultado.add(new reservasModel(
                                    convertirFechas.fechasConvertir(resultSet.getDate("inicio")),
                                    convertirFechas.fechasConvertir(resultSet.getDate("fin")),
                                    resultSet.getString("formapago"),
                                    resultSet.getDouble("valortotal"),
                                    resultSet.getInt("idhuesped")
                    
                            ));
                        }
                    }

                } catch (SQLException ex) {
                    System.out.println("error en resulset " + ex.getMessage());
                }
            } catch (Exception e) {
                System.out.println("error en reserva dao " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(" en dao resultado "+ resultado);
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
    
    public boolean modificar(LocalDate fechainicial,LocalDate fechafinal, double valortotal, String formapago,int id) {
        boolean rsp=false;
        try {
            final PreparedStatement statement = conn.prepareStatement(
                    "UPDATE reservas SET "
                    + " inicio = ?, "
                    + " fin = ?,"
                    + " valortotal = ?,"
                    + " formapago = ?"        
                    + " WHERE idhuesped = ?");

           
                statement.setDate(1, Date.valueOf(fechainicial));
                statement.setDate(2, Date.valueOf(fechafinal));
                statement.setDouble(3, valortotal);
                statement.setString(4, formapago);
                statement.setInt(5, id);
                statement.execute();
                rsp=true;

                

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return rsp;
    }

    public boolean eliminar(int id) {
        boolean rsp = false;
        try {
            final PreparedStatement statement = conn.prepareStatement("DELETE FROM reservas WHERE idhuesped = ?");

            statement.setInt(1, id);
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
