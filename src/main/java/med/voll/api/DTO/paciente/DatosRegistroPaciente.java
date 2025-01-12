package med.voll.api.DTO.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.DTO.DatosDireccion;

public record DatosRegistroPaciente(
        @NotBlank
        String nombre,

        @NotBlank @Email
        String email,

        @NotBlank
        String telefono,

        @NotBlank
        String documentoIdentidad,

        @NotNull
        @Valid
        DatosDireccion direccion
) {
}
