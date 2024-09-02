package org.e7.clinica.controller;

import org.e7.clinica.entity.Turno;
import org.e7.clinica.service.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/turno")
public class TurnoController {

    private TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarTurno(@RequestBody Turno turno){
        Turno turnoAGuardar = turnoService.guardarTurno(turno);

        if (turnoAGuardar != null){
            return ResponseEntity.ok(turnoAGuardar);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El paciente o el odontologo no fueron encontrados");
        }
    }
}
