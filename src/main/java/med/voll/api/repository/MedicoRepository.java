package med.voll.api.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import med.voll.api.DTO.medico.Especialidad;
import med.voll.api.DTO.medico.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findByActivoTrue(Pageable paginacion);

    @Query("""
            SELECT m FROM Medico m
            WHERE m.activo = true
            AND m.especialidad = :especialidad
            AND m.id not in(SELECT c.medico.id FROM Consulta c WHERE c.fecha = :fecha)
            ORDER BY  rand()
            LIMIT 1
            """)
    Medico elegirMedicoAleatorio(@Param("especialidad")Especialidad especialidad,@Param("fecha") LocalDateTime fecha);


    @Query("""
            SELECT m.activo FROM Medico m WHERE m.id = :idMedico
            """)
    boolean findActivoById(Long idMedico);
    
}
