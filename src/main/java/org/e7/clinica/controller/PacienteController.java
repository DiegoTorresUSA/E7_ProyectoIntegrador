package org.e7.clinica.controller;

import org.e7.clinica.model.Paciente;
import org.e7.clinica.service.PacienteService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    //Se instacia como atributo, porque necesitamos acceder a sus metodos
    private PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
    @PostMapping("/guardar")
    public ResponseEntity<Paciente>  guardarPaciente(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?>  buscarPorId(@PathVariable Integer id){
        Paciente paciente = pacienteService.buscarPorId(id);
        if(paciente != null){
            return ResponseEntity.ok(paciente);
        } else {
            // ResponseEntity.status(HttpStatus.NOT_FOUND).body("paciente no encontrado");
            //ResponseEntity.notFound().build();
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
        }
    }

    @GetMapping("/buscartodos")
    public List<Paciente> buscarTodos(){
        return pacienteService.buscarTodos();
    }

    @PutMapping("/modificar")
    public String modificarPaciente(@RequestBody Paciente paciente){
        pacienteService.modificarPaciente(paciente);
        return "el paciente fue modificado";
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarPaciente(@PathVariable Integer id){
        pacienteService.eliminarPaciente(id);
        return "el paciente fue eliminado";
    }
}
