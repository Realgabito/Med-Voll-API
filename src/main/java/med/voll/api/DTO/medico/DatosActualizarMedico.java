package med.voll.api.DTO.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.DTO.DatosDireccion;

public record DatosActualizarMedico(

    @NotNull
    Long id,
    String nombre,
    String documento,
    DatosDireccion direccion
    
) {

}
