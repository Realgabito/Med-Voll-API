package med.voll.api.DTO.medico;

import med.voll.api.DTO.DatosDireccion;

public record DatosRespuestaMedico(
    Long id,
    String nombre,
    String email,
    String telefono,
    String documento,
    DatosDireccion direccion
) {

}
