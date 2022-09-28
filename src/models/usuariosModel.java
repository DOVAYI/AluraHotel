package models;

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

        return password;
    }

}
