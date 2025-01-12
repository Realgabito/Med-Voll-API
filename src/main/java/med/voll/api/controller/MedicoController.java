package med.voll.api.controller;


import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.DTO.DatosDireccion;
import med.voll.api.DTO.medico.DatosActualizarMedico;
import med.voll.api.DTO.medico.DatosListadoMedico;
import med.voll.api.DTO.medico.DatosRegistroMedico;
import med.voll.api.DTO.medico.DatosRespuestaMedico;
import med.voll.api.DTO.medico.Medico;
import med.voll.api.repository.MedicoRepository;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico, UriComponentsBuilder uriComponentsBuilder) {
        Medico medico = medicoRepository.save(new Medico(datosRegistroMedico)); 

        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(), medico.getTelefono(), medico.getEspecialidad().toString(), 
        new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getBarrio(), medico.getDireccion().getCiudad(), medico.getDireccion().getComplemento()));

        // URI url = 
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaMedico);
    }

    @GetMapping
    public ResponseEntity <org.springframework.data.domain.Page<DatosListadoMedico>>  listadoMedico(@PageableDefault(size = 10) Pageable paginacion) {
        // return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new)) ;
    }
    
    @PutMapping
    @Transactional
    public ResponseEntity actualizarInformacion(@RequestBody @Valid DatosActualizarMedico DatosActualizarMedico) {
        Medico medico = medicoRepository.getReferenceById(DatosActualizarMedico.id());
        medico.actualizarInformacion(DatosActualizarMedico);
        return ResponseEntity.ok(new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(), medico.getTelefono(), medico.getEspecialidad().toString(),
                new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getBarrio(), medico.getDireccion().getCiudad(), medico.getDireccion().getComplemento())));
    }


    //DELETE LOGICO
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }


    //DELETE EN BASE DE DATOS
    // public void eliminarMedico(@PathVariable Long id) {
    //     Medico medico = medicoRepository.getReferenceById(id);
    //     medicoRepository.delete(medico);
    // }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaMedico> retornaDatosMedico(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);

        var datosMedico = new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(), medico.getTelefono(), medico.getEspecialidad().toString(), 
        new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getBarrio(), medico.getDireccion().getCiudad(), medico.getDireccion().getComplemento()));

        return ResponseEntity.ok(datosMedico);
    }

    
    
}
