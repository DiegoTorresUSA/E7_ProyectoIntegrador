package org.e7.clinica.controller;

import org.e7.clinica.model.Paciente;
import org.e7.clinica.service.PacienteService;
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
    @PostMapping("/guardar")
    public Paciente guardarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardarPaciente(paciente);
    }
    @GetMapping("/buscar/{id}")
    public Paciente buscarPorId(@PathVariable Integer id){
        return pacienteService.buscarPorId(id);
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
