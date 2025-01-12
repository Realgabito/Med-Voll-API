package med.voll.api.DTO.consulta.validaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.DTO.consulta.DatosReservaConsulta;
import med.voll.api.infra.errores.ValidacionException;
import med.voll.api.repository.ConsultaRepository;


@Component
public class ValidarConsultas implements ValidacionDeConsultas {

    @Autowired
    private  ConsultaRepository consultaRepository;


    public void validar(DatosReservaConsulta datosReservaConsulta) {
        var primerHorario = datosReservaConsulta.fecha().withHour(7);
        var ultimoHorario = datosReservaConsulta.fecha().withHour(18);

        var pacienteTieneConsultas = consultaRepository.existsByPacienteIdAndFechaBetween(datosReservaConsulta.idPaciente(), primerHorario, ultimoHorario);

        if (pacienteTieneConsultas) {
            throw new ValidacionException("Paciente ya tiene consultas reservadas para ese dia");
        }
    }

}
