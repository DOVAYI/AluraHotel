package Controllers;

import dao.reservaDao;
import java.util.List;
import models.reservasModel;

public class reservasController {

    private reservaDao reservadao;

    public reservasController() {
        reservadao = new reservaDao();
    }

    public boolean guardarReserva(reservasModel reservas) {
        return reservadao.guardar(reservas);
    }

    public List<reservasModel> listar(int identificacion) {
        return reservadao.listar(identificacion);
    }

    public void cerrarConexion(int statement, int conexion) {
        reservadao.cerrarConexion(statement, conexion);
    }

    public boolean eliminar(int id) {
        return reservadao.eliminar(id);
    }

}
