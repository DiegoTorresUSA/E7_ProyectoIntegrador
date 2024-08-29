package org.e7.clinica.controller;

import org.e7.clinica.model.Odontologo;
import org.e7.clinica.service.OdontologoService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class VistaControllerOdontologo {
    private OdontologoService odontologoService;

    public VistaControllerOdontologo(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping("/index")
    public String mostrarOdontologoPorId(Model model, @RequestParam Integer id){
        Odontologo odontologo = odontologoService.buscarPorId(id);
        model.addAttribute("nombre", odontologo.getNombre());
        model.addAttribute("apellido", odontologo.getApellido());
        return "odontologo";
    }
}
