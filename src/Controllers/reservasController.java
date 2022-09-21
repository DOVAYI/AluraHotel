package Controllers;

import dao.reservaDao;
import models.reservasModel;

public class reservasController {

    private reservaDao reservadao;

    public reservasController() {
        reservadao = new reservaDao();
    }

    public boolean guardarReserva(reservasModel reservas) {
        return reservadao.guardar(reservas);
    }

    public void cerrarConexion() {
        reservadao.cerrarConexion();
    }
}
