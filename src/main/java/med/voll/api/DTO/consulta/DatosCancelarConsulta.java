package med.voll.api.DTO.consulta;

import jakarta.validation.constraints.NotNull;

public record DatosCancelarConsulta(
    
@NotNull
Long idConsulta,

@NotNull
MotivoCancelacion motivo
) {

}
