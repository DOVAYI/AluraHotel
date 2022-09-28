package models;

import utilidades.encriptar;

public class usuariosModel {

    private String usuario;
    private String password;

    public usuariosModel(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        //String password = encriptar.getHash(usuario);
        return password;
    }

}
