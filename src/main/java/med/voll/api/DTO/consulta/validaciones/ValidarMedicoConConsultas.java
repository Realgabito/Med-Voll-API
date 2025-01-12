package med.voll.api.DTO.consulta.validaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.DTO.consulta.DatosReservaConsulta;
import med.voll.api.infra.errores.ValidacionException;
import med.voll.api.repository.ConsultaRepository;


@Component
public class ValidarMedicoConConsultas implements ValidacionDeConsultas {


    @Autowired
    private ConsultaRepository consultaRepository;


    public void validar(DatosReservaConsulta datosReservaConsulta) {
        var medicoTieneConsultas = consultaRepository.existsByMedicoIdAndFecha(datosReservaConsulta.idMedico(), datosReservaConsulta.fecha());

        if (medicoTieneConsultas) {
            throw new ValidacionException("Medico ya tiene otra consulta para esta fecha");
        }
    }
}
