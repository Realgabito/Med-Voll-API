package med.voll.api.DTO;

import jakarta.validation.constraints.NotBlank;

public record DatosDireccion(
    
     @NotBlank
     String calle,
     
     @NotBlank
     String barrio,
     
     @NotBlank 
     String ciudad,

     @NotBlank 
     String complemento
     
) {

}
