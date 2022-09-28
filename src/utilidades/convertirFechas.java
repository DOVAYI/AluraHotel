package utilidades;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class convertirFechas {

    public static LocalDate fechasConvertir(Date fechas) {
        LocalDate fechaconvertida = null;
        try {
            fechaconvertida = Instant.ofEpochMilli(fechas.getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();;

        } catch (Exception e) {
            System.out.println("Error al convertir Fechas " + e.getMessage());
        }
        return fechaconvertida;
    }
}
