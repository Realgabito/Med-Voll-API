package med.voll.api.DTO.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.DTO.consulta.validaciones.ValidacionDeConsultas;
import med.voll.api.DTO.medico.Medico;
import med.voll.api.infra.errores.ValidacionException;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;

@Service
public class ReservaDeConsulta {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository PacienteRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private List<ValidacionDeConsultas> validaciones;



    public DatosDetalleConsulta reservar(DatosReservaConsulta datosReservaConsulta) {

        if (!PacienteRepository.existsById(datosReservaConsulta.idPaciente())) {
            
            throw new ValidacionException("No existe un paciente con el ID informado");
        }

    

        if (datosReservaConsulta.idMedico() != null && !medicoRepository.existsById(datosReservaConsulta.idMedico())) {
            
            throw new ValidacionException("No existe un medico con el ID informado");
        }


        //Validaciones
        validaciones.forEach(v -> v.validar(datosReservaConsulta));

        

        var medico = elegirMedico(datosReservaConsulta);

        if (medico == null) {
            
            throw new ValidacionException("No existe un medico disponible en ese horario");
        }


        var paciente = PacienteRepository.findById(datosReservaConsulta.idPaciente()).get();
        
        var consulta = new Consulta(null, medico, paciente, datosReservaConsulta.fecha(), null);
        
        
        consultaRepository.save(consulta);
        return new DatosDetalleConsulta(consulta);
        
    }


        
    public Medico elegirMedico(DatosReservaConsulta datosReservaConsulta) {
        if (datosReservaConsulta.idMedico() != null ) {
                return medicoRepository.getReferenceById(datosReservaConsulta.idMedico());
            }

        if (datosReservaConsulta.especialidad() == null) {
                    throw new ValidacionException("Es necesario elegir una especialidad cuando no se elije un medico");
            }

        return medicoRepository.elegirMedicoAleatorio(datosReservaConsulta.especialidad(), datosReservaConsulta.fecha());
    }

    public void cancelar(DatosCancelarConsulta datosCancelarConsulta) {
        if (!consultaRepository.existsById(datosCancelarConsulta.idConsulta())) {
            throw new ValidacionException("ID de la consulta no existe!");
        }

        var consulta = consultaRepository.getReferenceById(datosCancelarConsulta.idConsulta());
        consulta.cancelar(datosCancelarConsulta.motivo());
    }



}
