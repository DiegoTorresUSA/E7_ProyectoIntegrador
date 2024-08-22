package org.e7.clinica.controller;

import org.e7.clinica.model.Odontologo;
import org.e7.clinica.model.Paciente;
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

    @GetMapping("/odontologo")  // -> listar odontologos? ?????
    public String mostrarOdontologo(Model model) {
        List<Odontologo> odontologos = odontologoService.listarOdontologo();
        model.addAttribute("odontologos", odontologos);
        return "odontologo";
    }
    @PutMapping("/modificar")
    public String modificarOdontologo(@RequestBody Odontologo odontologo){
        odontologoService.modificarOdontologo(odontologo);
        return "el odontologo  fue modificado";
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarOdontologo(@PathVariable Integer id) {
        odontologoService.eliminarOdontologoe(id);
        return "el paciente fue eliminado";
    }
}
