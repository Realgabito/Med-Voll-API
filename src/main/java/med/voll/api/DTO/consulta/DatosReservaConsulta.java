package med.voll.api.DTO.consulta;

import java.time.LocalDateTime;



import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.DTO.medico.Especialidad;

public record DatosReservaConsulta(

        Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        
        LocalDateTime fecha,

        Especialidad especialidad
) {
} 

