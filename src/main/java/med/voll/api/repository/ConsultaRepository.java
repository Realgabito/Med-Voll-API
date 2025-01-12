package med.voll.api.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import med.voll.api.DTO.consulta.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByMedicoIdAndFecha(Long idMedico, LocalDateTime fecha);

    boolean existsByPacienteIdAndFechaBetween(Long idPaciente, LocalDateTime primerHorario,
            LocalDateTime ultimoHorario);


}
