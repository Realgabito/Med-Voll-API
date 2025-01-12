package med.voll.api.DTO.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import med.voll.api.DTO.DatosDireccion;

public record DatosActualizacionPaciente(
        Long id,
        String nombre,
        @Email
        String email,
        String telefono,
        String documentoIdentidad,
        @Valid
        DatosDireccion direccion
) {
}
