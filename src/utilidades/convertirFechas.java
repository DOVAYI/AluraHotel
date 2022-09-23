package utilidades;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class convertirFechas {

    public static LocalDate fechasConvertir(Date fechas) {
        LocalDate fechaconvertida = Instant.ofEpochMilli(fechas.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();;
        return fechaconvertida;
    }
}
