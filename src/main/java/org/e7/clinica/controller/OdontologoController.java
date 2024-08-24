package org.e7.clinica.controller;

import org.e7.clinica.model.Odontologo;
import org.e7.clinica.service.OdontologoService;
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

    @GetMapping("/listarOdontologos")
    public List<Odontologo> listarOdontologos(){
        return odontologoService.listarOdontologo();
    }

    @GetMapping("/buscar/{id}")
    public Odontologo buscarPorId(@PathVariable Integer id){
        return odontologoService.buscarporId(id);
    }

    @PutMapping("/modificar")
    public String modificarOdontologo(@RequestBody Odontologo odontologo){
        odontologoService.modificarOdontologo(odontologo);
        return "El odontologo con id: " + odontologo.getId() + " fue actualizado";
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarOdontologo(@PathVariable Integer id){
        odontologoService.eliminarOdontologo(id);
        return "El odontologo con id: " + id+ " fue Eliminado";
    }
}