package org.e7.clinica.controller;

import org.e7.clinica.model.Paciente;
import org.e7.clinica.service.PacienteService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/index")
    public String mostrarPacientePorId(Model model, @RequestParam Integer id) {
        Paciente paciente = pacienteService.buscarPorId(id);
        model.addAttribute("nombrePaciente", paciente.getNombre());
        model.addAttribute("apellidoPaciente", paciente.getApellido());
        return "paciente";
    }

    @PostMapping("/guardar")
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    @GetMapping("/listarPacientes")
    public ResponseEntity<List<Paciente>> listarPacientes() {
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        Paciente paciente = pacienteService.buscarPorId(id);
        if(paciente != null){
            return ResponseEntity.ok(paciente);
        } else {
            // ResponseEntity.status(HttpStatus.NOT_FOUND).body("paciente no encontrado");
            //ResponseEntity.notFound().build();
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
        }
    }
    @PutMapping("/modificar")
    public ResponseEntity<?> modificarPaciente(@RequestBody Paciente paciente){
        Paciente pacienteEncontrado = pacienteService.buscarPorId(paciente.getId());
        if(pacienteEncontrado!= null){
            pacienteService.modificarPaciente(paciente);
            String jsonResponse = "{\"mensaje\": \"El paciente fue modificado\"}";
            return ResponseEntity.ok(jsonResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Integer id){
        Paciente pacienteEncontrado = pacienteService.buscarPorId(id);
        if(pacienteEncontrado!= null){
            pacienteService.eliminarPaciente(id);
            String jsonResponse = "{\"mensaje\": \"El paciente fue modificado\"}";
            return ResponseEntity.ok(jsonResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
