package org.e7.clinica.controller;

import org.e7.clinica.model.Odontologo;
import org.e7.clinica.service.OdontologoService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Odontologo>  guardarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @GetMapping("/odontologo")
    public String mostrarOdontologo(Model model) {
        List<Odontologo> odontologos = odontologoService.listarOdontologo();
        model.addAttribute("odontologos", odontologos);
        return "odontologo";
    }

    @GetMapping("/listarOdontologos")
    public ResponseEntity<List<Odontologo>>  listarOdontologos(){
        return ResponseEntity.ok(odontologoService.listarOdontologo());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        Odontologo odontologo = odontologoService.buscarporId(id);
        if (odontologo != null){
            return ResponseEntity.ok(odontologo);
        }else {
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
        }
    }

    @PutMapping("/modificar")
    public ResponseEntity<?> modificarOdontologo(@RequestBody Odontologo odontologo){
        Odontologo odontologoEncontrado = odontologoService.buscarporId(odontologo.getId());
        if (odontologoEncontrado != null){
            odontologoService.modificarOdontologo(odontologo);
            String jsonResponse = "{\"mensaje\": \"El paciente fue modificado\"}";
            return ResponseEntity.ok(jsonResponse);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Integer id){
        Odontologo odontologoEncontrado = odontologoService.buscarporId(id);
        if (odontologoEncontrado != null){
            odontologoService.eliminarOdontologo(id);
            String jsonResponse = "{\"mensaje\": \"El paciente fue Eliminado\"}";
            return ResponseEntity.ok(jsonResponse);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}