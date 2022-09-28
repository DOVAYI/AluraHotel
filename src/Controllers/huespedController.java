package Controllers;

import dao.huespedDao;
import java.time.LocalDate;
import java.util.List;
import models.huesped;

public class huespedController {

    private huespedDao huespedao;

    public huespedController() {

        huespedao = new huespedDao();

    }

    public boolean guardar(huesped huesped) {
        boolean rsp = huespedao.guardar(huesped);
        return rsp;
    }

    public int buscarMaximoIdReserva() {
        return huespedao.buscarMaximoIdReserva();
    }

    public List<huesped> listar(int identificacion) {
        return huespedao.listar(identificacion);
    }

    public boolean modificar(String nombres, LocalDate nacimiento,
            String telefonos, int id) {

        return huespedao.modificar(nombres, nacimiento, telefonos, id);

    }

    public boolean eliminar(int identificacion) {
        return huespedao.eliminar(identificacion);
    }

    public void cerrarConexion(int statement, int conexion) {
        huespedao.cerrarConexion(statement, conexion);
    }

}
