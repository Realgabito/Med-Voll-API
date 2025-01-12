package med.voll.api.DTO.consulta.validaciones;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.DTO.consulta.DatosReservaConsulta;
import med.voll.api.infra.errores.ValidacionException;


@Component
public class ValidacionConsultaConAnticipacion implements ValidacionDeConsultas {

    
    public void validar(DatosReservaConsulta datosReservaConsulta) {


        var fechaConsulta = datosReservaConsulta.fecha();
        var ahora = LocalDateTime.now();
        var diferenciaEnMinutos = Duration.between(ahora, fechaConsulta).toMinutes();

        if (diferenciaEnMinutos < 30) {
            throw new ValidacionException("No se puede hacer la consulta con menos de 30 minutos de anticipacion");
        }
    }
}
