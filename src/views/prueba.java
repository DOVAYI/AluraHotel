/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;

/**
 *
 * @author Luis Alfredo Romero
 */
public class prueba {
    public static void main(String[] args) {
        
         LocalDate inicio = LocalDate.of(2019, 7, 14); // 1 de enero 2019
        LocalDate fin = LocalDate.of(2020, 1, 1);// 1 de enero 2020
 
        // Usando ChronoUnit, calculo el numero de dias
        long dias = DAYS.between(inicio, fin);
        
        System.out.println("año: "+inicio.getYear());
        System.out.println("mes: "+inicio.getMonthValue());
        System.out.println("dia: "+inicio.getDayOfMonth());
        System.out.println(" cuantos dias: "+dias);
    }
    
}
