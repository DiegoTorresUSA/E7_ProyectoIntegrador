package org.e7.clinica.controller;

import org.e7.clinica.model.Paciente;
import org.e7.clinica.service.PacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
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
}
