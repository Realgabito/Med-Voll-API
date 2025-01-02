package med.voll.api.DTO;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {
    
    private String calle;
    private String barrio;
    private String ciudad;
    private String complemento;
    
    public Direccion(DatosDireccion direccion) {
       this.calle = direccion.calle();
       this.barrio = direccion.barrio();
       this.ciudad = direccion.ciudad();
       this.complemento = direccion.complemento();
    }

    public Direccion actualizarInformacion(DatosDireccion direccion) {
        this.calle = direccion.calle();
        this.barrio = direccion.barrio();
        this.ciudad = direccion.ciudad();
        this.complemento = direccion.complemento();
        return this;
    }
}
