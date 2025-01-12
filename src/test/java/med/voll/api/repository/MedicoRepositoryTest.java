package med.voll.api.repository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import jakarta.persistence.EntityManager;
import med.voll.api.DTO.DatosDireccion;
import med.voll.api.DTO.consulta.Consulta;
import med.voll.api.DTO.consulta.MotivoCancelacion;
import med.voll.api.DTO.medico.DatosRegistroMedico;
import med.voll.api.DTO.medico.Especialidad;
import med.voll.api.DTO.medico.Medico;
import med.voll.api.DTO.paciente.DatosRegistroPaciente;
import med.voll.api.DTO.paciente.Paciente;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class MedicoRepositoryTest {


    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("Deberia devolver null cuando el medico buscado existe pero no esta disponible en esa fecha")
    void testElegirMedicoAleatorioEscenario1() {

        //given o arrange

        var lunesSiguiente = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        var medico = registrarMedico("Medico1", "medico1@gmail.com", "123456", Especialidad.CARDIOLOGIA);
        var paciente = registrarPacientes("Paciente1", "paciente1@gmail.com", "123457");
        registrarConsulta(medico , paciente, lunesSiguiente, null);


        //then  o assert

        var medicoLibre = medicoRepository.elegirMedicoAleatorio(Especialidad.CARDIOLOGIA, lunesSiguiente);

        assertThat(medicoLibre).isNull();
    }



    @Test
    @DisplayName("Deberia devolver medico cuando el medico buscado esta disponible en esa fecha")
    void testElegirMedicoAleatorioEscenario2() {

        //Given o arrange

        var lunesSiguiente = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        var medico = registrarMedico("Medico1", "medico1@gmail.com", "123456", Especialidad.CARDIOLOGIA);
        
         
        //when o act
        
        var medicoLibre = medicoRepository.elegirMedicoAleatorio(Especialidad.CARDIOLOGIA, lunesSiguiente);


        //then o assert

        assertThat(medicoLibre).isEqualTo(medico);
    }



    private void registrarConsulta(Medico medico, Paciente paciente, LocalDateTime fecha, MotivoCancelacion motivoCancelacion) {
        em.persist(new Consulta(null, medico, paciente, fecha, motivoCancelacion));
    }

    private Medico registrarMedico(String nombre, String email, String documento, Especialidad especialidad) {
        var medico = new Medico(datosMedico(nombre, email, documento, especialidad));
        em.persist(medico);
        return medico;
    }

    private Paciente registrarPacientes(String nombre, String email, String documento){
        var paciente = new Paciente(datosPaciente(nombre, email, documento));
        em.persist(paciente);
        return paciente;
    }

    private DatosRegistroMedico datosMedico(String nombre, String email, String docummento, Especialidad especialidad) {
        return new DatosRegistroMedico(nombre, email,"12345546", docummento, especialidad, datosDireccion());
    }

    private DatosRegistroPaciente datosPaciente(String nombre, String email, String documento) {
        return new DatosRegistroPaciente(nombre, email, "1234567", documento, datosDireccion());
    }

    private DatosDireccion datosDireccion() {
        return new DatosDireccion("calle x", "barrio y", "ciudad z", "1234");
    }
}
