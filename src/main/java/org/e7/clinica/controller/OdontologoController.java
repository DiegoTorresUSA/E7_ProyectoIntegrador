package org.e7.clinica.controller;

import org.e7.clinica.entity.Odontologo;
import org.e7.clinica.entity.Paciente;
import org.e7.clinica.service.OdontologoService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        Optional <Odontologo>odontologo = odontologoService.buscarPorId(id);
        if (odontologo.isPresent()){
            return ResponseEntity.ok(odontologo.get());
        }else {
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
        }
    }
    @GetMapping("/buscartodos")
    public ResponseEntity <List<Odontologo> >buscarTodos(){
        return  ResponseEntity.ok(odontologoService.buscarTodos());
    }

    @PutMapping("/modificar")
    public ResponseEntity<?> modificarOdontologo(@RequestBody Odontologo odontologo){
       Optional< Odontologo> odontologoEncontrado = odontologoService.buscarPorId(odontologo.getId());
        if (odontologoEncontrado.isPresent()){
            odontologoService.modificarOdontologo(odontologoEncontrado.get());
            String jsonResponse = "{\"mensaje\": \"El paciente fue modificado\"}";
            return ResponseEntity.ok(jsonResponse);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Integer id){
        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarPorId(id);
        if (odontologoEncontrado.isPresent()){
            odontologoService.eliminarOdontologo(id);
            String jsonResponse = "{\"mensaje\": \"El paciente fue Eliminado\"}";
            return ResponseEntity.ok(jsonResponse);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
