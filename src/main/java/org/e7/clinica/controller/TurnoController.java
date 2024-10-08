package org.e7.clinica.controller;

import org.e7.clinica.entity.Turno;
import org.e7.clinica.service.impl.TurnoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turno")
public class TurnoController {

    private TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarTurno(@RequestBody Turno turno){
        return ResponseEntity.ok(turnoService.guardarTurno(turno)) ;
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?>  buscarPorId(@PathVariable Integer id){
        turnoService.buscarporId(id);
        return ResponseEntity.ok(turnoService.buscarporId(id));
    }

    @GetMapping("/buscartodos")
    public ResponseEntity <List<Turno>>buscarTodos(){
        return  ResponseEntity.ok(turnoService.buscarTodos());
    }

 @PutMapping("/modificar")
    public ResponseEntity<?> modificarTurno(@RequestBody Turno turno){
            turnoService.modificarTurno(turno);
            return ResponseEntity.ok("{\"mensaje\": \"El turno fue modificado\"}") ;
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Integer id) {
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok("{\"mensaje\": \"El turno fue eliminado\"}");
    }

}

