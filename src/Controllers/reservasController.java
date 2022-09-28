package Controllers;

import dao.reservaDao;
import java.time.LocalDate;
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

    public boolean modificar(LocalDate fechainicial, LocalDate fechafinal,
            double valortotal, String formapago, int id) {
        return reservadao.modificar(fechainicial, fechafinal,
                valortotal, formapago, id);
    }

    public boolean eliminar(int id) {
        return reservadao.eliminar(id);
    }

    public void cerrarConexion(int statement, int conexion) {
        reservadao.cerrarConexion(statement, conexion);
    }

}
