package med.voll.api.DTO.consulta.validaciones;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import med.voll.api.DTO.consulta.DatosReservaConsulta;
import med.voll.api.infra.errores.ValidacionException;

@Component
public class ValidacionFueraHorarioConsultas implements ValidacionDeConsultas{

    
    public void validar(DatosReservaConsulta datosReservaConsulta) {
        var fechaConsulta = datosReservaConsulta.fecha();

        var domingo = fechaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horarioAntesDeApertura = fechaConsulta.getHour() < 7;
        var horarioDespuesDeClausura = fechaConsulta.getHour() > 18; 

        if (domingo || horarioAntesDeApertura || horarioDespuesDeClausura ) {
            throw new ValidacionException("Horario seleccionado fuera del horario de atencion");
        }
    }
}
