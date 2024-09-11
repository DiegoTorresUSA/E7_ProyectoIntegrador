package org.e7.clinica.controller;

import org.e7.clinica.entity.Paciente;
import org.e7.clinica.service.impl.PacienteService;
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
        pacienteService.buscarPorId(id);
            return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }

    @GetMapping("/buscartodos")
    public ResponseEntity <List<Paciente> >buscarTodos(){
        return  ResponseEntity.ok(pacienteService.buscarTodos());
    }
    //No hay pacientes guardados - agregar excepcion
    @PutMapping("/modificar")
    public ResponseEntity<?> modificarPaciente(@RequestBody Paciente paciente){
            pacienteService.modificarPaciente(paciente);
            return ResponseEntity. ok("{\"mensaje\": \"El paciente fue modificado\"}");
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Integer id){
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("{\"mensaje\": \"El paciente fue eliminado\"}");
    }/*
    @GetMapping("/buscarApellidoNombre")
    public ResponseEntity<List<Paciente>> buscarApellidoYNombre(@RequestParam String apellido,


        return ResponseEntity.ok(pacienteService.buscarPorApellidoyNombre(apellido, nombre));
    }

    @GetMapping("/buscarNombre/{nombre}")
    public ResponseEntity<List<Paciente>> buscarNombreLike(@PathVariable String nombre){
        return ResponseEntity.ok(pacienteService.buscarLikeNombre(nombre));
    }*/

}
