package Controllers;

import dao.usuariosDao;
import models.usuariosModel;

public class usuariosController {

    private usuariosDao usuariosdao;

    public usuariosController() {
        usuariosdao = new usuariosDao();
    }

    public boolean guardar(usuariosModel usuarios) {
        return usuariosdao.guardar(usuarios);
    }

    public boolean validarAcceso(usuariosModel usuarios) {

        return usuariosdao.validarAcceso(usuarios);
    }
    
    public void cerrarConexion(int statement, int conexion) {
        usuariosdao.cerrarConexion(statement, conexion);
    }

}
