package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.DTO.paciente.DatosListaPaciente;
import med.voll.api.DTO.paciente.DatosRegistroPaciente;
import med.voll.api.DTO.paciente.Paciente;
import med.voll.api.repository.PacienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.data.domain.Pageable;


@RestController
@RequestMapping("pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping
    public Page<DatosListaPaciente> listarPaciente(@PageableDefault( size = 10, sort = {"nombre"})Pageable paginacion) {
        return (Page<DatosListaPaciente>) pacienteRepository.findByActivoTrue(paginacion).map(DatosListaPaciente::new);
    }

    @PostMapping
    @Transactional
    public void registrar(@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente) {
        pacienteRepository.save(new Paciente(datosRegistroPaciente));
    }

    @PutMapping
    @Transactional
    public void remover(@PathVariable Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.eliminarPaciente();
    }
}
