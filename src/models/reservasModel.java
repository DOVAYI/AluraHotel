package models;

import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;

public class reservasModel {

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private final double valor = 35000;
    private String formaPago;
    private int idHuesped;
    private double valorTotalReserva;

    public reservasModel(LocalDate fechaInicio, LocalDate fechaFin, String formaPago, double valorTotalReserva,int idHuesped) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.formaPago = formaPago;
        this.valorTotalReserva = valorTotalReserva;
        this.idHuesped=idHuesped;
    }
    
    

    public reservasModel(LocalDate fechaInicio, LocalDate fechaFin, String formaPago, int idHuesped) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.formaPago = formaPago;
        this.idHuesped = idHuesped;
    }

    public reservasModel(LocalDate fechaInicio, LocalDate fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;

    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public int getIdHuesped() {
        return idHuesped;
    }

    public double getValorTotalReserva() {
        return valorTotalReserva;
    }
    
    

    public double valorTotalReservas() {
        double valorTotal = valor;
        LocalDate inicio = LocalDate.of(fechaInicio.getYear(),
                fechaInicio.getMonthValue(), fechaInicio.getDayOfMonth()); 
        LocalDate fin = LocalDate.of(fechaFin.getYear(),
                fechaFin.getMonthValue(), fechaFin.getDayOfMonth());

        // Usando ChronoUnit, calculo el numero de dias
        double dias = DAYS.between(inicio, fin);

        if (dias > 0 || dias==0) {
            valorTotal = valor * (dias+1);
        } else if (dias < 0) {
            valorTotal = 0;
        }

        return valorTotal;
    }

}
