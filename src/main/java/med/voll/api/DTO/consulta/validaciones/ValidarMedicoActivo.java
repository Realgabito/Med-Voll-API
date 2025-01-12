package med.voll.api.DTO.consulta.validaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.DTO.consulta.DatosReservaConsulta;
import med.voll.api.infra.errores.ValidacionException;
import med.voll.api.repository.MedicoRepository;



@Component
public class ValidarMedicoActivo implements ValidacionDeConsultas {


    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DatosReservaConsulta datosReservaConsulta) {
        //Seleccion del medico opcional 

        if (datosReservaConsulta.idMedico() == null) {
            return;
        }

        var medicoActivo = medicoRepository.findActivoById(datosReservaConsulta.idMedico());
        if (!medicoActivo) {
            throw new ValidacionException("El medico no esta activo, no se pudo hacer la consulta");
        }
    }

}
