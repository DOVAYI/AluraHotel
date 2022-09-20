package Controllers;

import dao.huespedDao;
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
    
    public int buscarMaximoIdReserva(){
        return huespedao.buscarMaximoIdReserva();
    }
    
    public void cerrarConexion(){
        huespedao.cerrarConexion();
    }

}
