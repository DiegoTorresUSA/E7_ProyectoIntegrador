package org.e7.clinica.controller;

import org.e7.clinica.entity.Paciente;
import org.e7.clinica.entity.Turno;
import org.e7.clinica.service.impl.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?>  buscarPorId(@PathVariable Integer id){
        Optional<Turno> turno = turnoService.buscarporId(id);
        if(turno.isPresent()){
            return ResponseEntity.ok(turno.get());
        } else {
            // ResponseEntity.status(HttpStatus.NOT_FOUND).body("paciente no encontrado");
            //ResponseEntity.notFound().build();
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
        }
    }

    @GetMapping("/buscartodos")
    public ResponseEntity <List<Turno>>buscarTodos(){
        return  ResponseEntity.ok(turnoService.buscarTodos());
    }

 /*   @PutMapping("/modificar")
    public ResponseEntity<?> modificarPaciente(@RequestBody Turno turno){
        Optional <Turno>  turnoGenerado= turnoService.buscarporId(turno.getId()));
        if(turnoGenerado.isPresent()){
            turnoService.modificarTurno(turnoGenerado.get());
            String jsonResponse = "{\"mensaje\": \"El turno fue modificado\"}";
            return ResponseEntity.ok(jsonResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Integer id){
        Optional <Turno> turnoEncontrado = turnoService.buscarporId(id);
        if(turnoEncontrado.isPresent()){
            turnoService.eliminarTurno(id);
            String jsonResponse = "{\"mensaje\": \"El turno fue eliminado\"}";
            return ResponseEntity.ok(jsonResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
}}
// hacer todos los metodos
