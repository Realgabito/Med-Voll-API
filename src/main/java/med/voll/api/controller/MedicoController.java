package med.voll.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.DTO.DatosActualizarMedico;
import med.voll.api.DTO.DatosListadoMedico;
import med.voll.api.DTO.DatosRegistroMedico;
import med.voll.api.DTO.Medico;
import med.voll.api.repository.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    public void registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico) {
     medicoRepository.save(new Medico(datosRegistroMedico)); 
    }

    @GetMapping
    public org.springframework.data.domain.Page<DatosListadoMedico> listadoMedico(@PageableDefault(size = 10) Pageable paginacion) {
        // return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        return medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new);
    }
    
    @PutMapping
    @Transactional
    public void actualizarInformacion(@RequestBody @Valid DatosActualizarMedico DatosActualizarMedico) {
        Medico medico = medicoRepository.getReferenceById(DatosActualizarMedico.id());
        medico.actualizarInformacion(DatosActualizarMedico);
    }


    //DELETE LOGICO
    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarMedico(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
    }


    //DELETE EN BASE DE DATOS
    // public void eliminarMedico(@PathVariable Long id) {
    //     Medico medico = medicoRepository.getReferenceById(id);
    //     medicoRepository.delete(medico);
    // }
    
}
