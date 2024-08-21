package org.e7.clinica.controller;

import org.e7.clinica.model.Odontologo;
import org.e7.clinica.service.OdontologoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {

    private OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping("/guardar")
    public Odontologo guardarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.guardarOdontologo(odontologo);
    }

    @GetMapping("/odontologo")
    public String mostrarOdontologo(Model model) {
        List<Odontologo> odontologos = odontologoService.listarOdontologo();
        model.addAttribute("odontologos", odontologos);
        return "odontologo";
    }
}
