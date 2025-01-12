package med.voll.api.repository;


import med.voll.api.DTO.paciente.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Page<Paciente> findByActivoTrue(Pageable paginacion);


    @Query("""
        SELECT p.activo FROM Paciente p WHERE p.id = :idPaciente
         """)
    boolean findActivoById(Long idPaciente);

    
}
