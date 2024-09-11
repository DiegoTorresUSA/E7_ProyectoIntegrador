package org.e7.clinica;

import org.e7.clinica.entity.Odontologo;
import org.e7.clinica.exception.ResourceNotFoundException;
import org.e7.clinica.service.impl.OdontologoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
class OdontologoServiceTest {
    @Autowired
    OdontologoService odontologoService;
    Odontologo odontologo;
    /*Odontologo odontologo1;*/
    Odontologo odontologoDesdeDB;

    @BeforeEach
    void crearOdontologo(){
        odontologo = new Odontologo();
        odontologo.setNombre("aleja");
        odontologo.setApellido("osorio");
        odontologo.setMatricula("3");
        odontologoDesdeDB = odontologoService.guardarOdontologo(odontologo);

        /*odontologo1 = new Odontologo();
        odontologo1.setNombre("Diego");
        odontologo1.setApellido("Torres");
        odontologo1.setMatricula("4");
        odontologoDesdeDB = odontologoService.guardarOdontologo(odontologo1);
        System.out.println(odontologoDesdeDB);*/
    }

    @Test
    @DisplayName("Testeamos el insert a la Base de Datos")
    void testInsert() {
        //dado
        //cuando
        //Entonces
        assertNotNull(odontologoDesdeDB.getMatricula());
        System.out.println("test insert caso1" + odontologoDesdeDB);
    }

    @Test
    @DisplayName("Testear la consulta de todos los odontologos en BD")
    void caso2(){
        //dado
        List<Odontologo> listaOdontologos = new ArrayList<>();
        // cuando
        listaOdontologos = odontologoService.buscarTodos();
        // entonces
        assertNotNull(listaOdontologos);
        System.out.println("test consulta caso 2" + listaOdontologos);
    }
    //hacer test para modificar y eliminar

    @Test
    @DisplayName("Testing para obtener un odontologo en BD")
    void testIdBD(){
        //dado
        Integer id = 1;
        // cuando
        odontologoDesdeDB = odontologoService.buscarPorId(id).get();
        // entonces
        assertEquals(id, odontologoDesdeDB.getId());
        System.out.println("test consulta caso 3" + odontologoDesdeDB);
    }

    @Test
    @DisplayName("Testing para eliminar un odontologo en BD")
    void testDelete(){
        //dado
        Odontologo odontologoAEliminar = new Odontologo();
        odontologoAEliminar.setNombre("Juan");
        odontologoAEliminar.setApellido("Perez");
        odontologoAEliminar.setMatricula("5");

        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologoAEliminar);

        System.out.println("odontologoGuardado" + odontologoGuardado);

        Integer id = odontologoGuardado.getId();
        // cuando
        odontologoService.eliminarOdontologo(id);
        // entonces
        assertThrows(ResourceNotFoundException.class, () -> {
            odontologoService.buscarPorId(id);
        }, "Se esperaba una ResourceNotFoundException cuando se busca un odontólogo eliminado");
    }
}