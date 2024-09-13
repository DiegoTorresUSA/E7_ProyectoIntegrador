package org.e7.clinica.controller;
import org.e7.clinica.entity.Odontologo;
import org.e7.clinica.service.impl.OdontologoService;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?>  buscarPorId(@PathVariable Integer id){
        odontologoService.buscarPorId(id);
        return ResponseEntity.ok(odontologoService.buscarPorId(id));
    }
    @GetMapping("/buscartodos")
    public ResponseEntity <List<Odontologo> >buscarTodos(){
        return  ResponseEntity.ok(odontologoService.buscarTodos());
    }

    @PutMapping("/modificar/")
    public ResponseEntity<?> modificarOdontologo(@RequestBody Odontologo odontologo){
        odontologoService.modificarOdontologo(odontologo);
        return ResponseEntity. ok("{\"mensaje\": \"El odontologo fue modificado\"}");
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Integer id){
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("{\"mensaje\": \"El odontologo fue eliminado\"}");
    }

    @GetMapping("/buscarApellidoNombre")
    public ResponseEntity <List<Odontologo> >buscarApellidoNombre(@RequestParam String apellido,
                                                                  @RequestParam String nombre){
        return  ResponseEntity.ok(odontologoService.buscarPorApellidoyNombre(apellido, nombre));
    }

    @GetMapping("/buscarNombreOrdenarMatricula")
    public ResponseEntity <List<Odontologo> >buscarApellidoNombre(@RequestParam String nombre){
        return ResponseEntity.ok(odontologoService.buscarPorNombreOderByMatricula(nombre));
    }

    @GetMapping("/conteoOdontologos")
    public ResponseEntity <Long> conteoOdontologos(){
        Long count = odontologoService.contarOdontologos();
        return ResponseEntity.ok(count);
    }
}
