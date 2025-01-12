package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.DTO.consulta.DatosCancelarConsulta;
import med.voll.api.DTO.consulta.DatosDetalleConsulta;
import med.voll.api.DTO.consulta.DatosReservaConsulta;
import med.voll.api.DTO.consulta.ReservaDeConsulta;


@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {


    @Autowired
    private ReservaDeConsulta reserva;

    @PostMapping
    @Transactional
    
    public ResponseEntity reservar(@RequestBody @Valid DatosReservaConsulta datosReservaConsulta) {
        

        var detalleConsulta = reserva.reservar(datosReservaConsulta);
        

        return ResponseEntity.ok(detalleConsulta);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DatosCancelarConsulta datosCancelarConsulta) {

        reserva.cancelar(datosCancelarConsulta);
        return ResponseEntity.noContent().build();
    }

}
