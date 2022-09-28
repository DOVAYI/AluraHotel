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
    
    public boolean modificar(String usuario, String newPassword) {
       
        return usuariosdao.modificar(usuario, newPassword);
    }
    
    public void cerrarConexion(int statement, int conexion) {
        usuariosdao.cerrarConexion(statement, conexion);
    }

}
