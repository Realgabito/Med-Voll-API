package med.voll.api.DTO.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.DTO.Direccion;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Paciente")
@Table(name = "pacientes")

public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String documentoIdentidad;
    private String telefono;

    @Embedded
    private Direccion direccion;

    private Boolean activo;

    public Paciente(DatosRegistroPaciente datos) {
        this.activo = true;
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.telefono = datos.telefono();
        this.documentoIdentidad = datos.documentoIdentidad();
        this.direccion = new Direccion(datos.direccion());

    }

    public void actualizarInformacion(DatosActualizacionPaciente datosActualizacionPaciente) {
        if (datosActualizacionPaciente.nombre() != null) {
            this.nombre = datosActualizacionPaciente.nombre();
        }

        if (datosActualizacionPaciente.telefono() != null) {
            this.telefono = datosActualizacionPaciente.telefono();
        }

        if (datosActualizacionPaciente.direccion() != null) {
            this.direccion.actualizarInformacion(datosActualizacionPaciente.direccion());
        }
    }

    public void eliminarPaciente() {
        this.activo = false;
    }
}
