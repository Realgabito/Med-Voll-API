package med.voll.api.DTO.consulta.validaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.DTO.consulta.DatosReservaConsulta;
import med.voll.api.infra.errores.ValidacionException;
import med.voll.api.repository.PacienteRepository;


@Component
public class ValidarPacienteActivo implements ValidacionDeConsultas {


    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DatosReservaConsulta datosReservaConsulta) {
        var pacienteActivo = pacienteRepository.findActivoById(datosReservaConsulta.idPaciente());

        if (!pacienteActivo) {
            throw new ValidacionException("EL paciente esta inactivo, no se pudo hacer la reserva");
        }
    }

}
