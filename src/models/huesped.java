
package models;

import java.time.LocalDate;

/**
 *
 * @author Luis Alfredo Romero
 */
public class huesped {
    private int identificacion;
    private String nombres;
    private LocalDate nacimiento;
    private String telefonos;

    public huesped(int identificacion, String nombres, LocalDate nacimiento, String telefonos) {
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.nacimiento = nacimiento;
        this.telefonos = telefonos;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public LocalDate getNacimiento() {
        return nacimiento;
    }

    public String getTelefonos() {
        return telefonos;
    }
    
    
}
