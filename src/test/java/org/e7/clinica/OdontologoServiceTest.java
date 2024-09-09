package org.e7.clinica;

import org.e7.clinica.entity.Odontologo;
import org.e7.clinica.entity.Paciente;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
class OdontologoServiceTest {
    @Autowired
    OdontologoService odontologoService;
    Odontologo odontologo;
    Odontologo odontologo1;
    Odontologo odontologoDesdeDB;

    @BeforeEach
    void crearOdontologo(){
        odontologo = new Odontologo();
        odontologo.setNombre("aleja");
        odontologo.setApellido("osorio");
        odontologo.setMatricula("3");
        odontologoDesdeDB = odontologoService.guardarOdontologo(odontologo);

        odontologo1 = new Odontologo();
        odontologo1.setNombre("Diego");
        odontologo1.setApellido("Torres");
        odontologo1.setMatricula("4");
        odontologoDesdeDB = odontologoService.guardarOdontologo(odontologo1);
    }

    @Test
    @DisplayName("Testeamos el insert a la Base de Datos")
    void testInsert() {
        //dado
        //cuando
        //Entonces
        assertNotNull(odontologoDesdeDB.getMatricula());

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
        System.out.println(listaOdontologos);
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
    }

    /*@Test
    @DisplayName("Testing para eliminar un odontologo en BD")
    void testDelete(){
        //dado
        Integer id = 2;
        // cuando
        odontologoDesdeDB = odontologoService.buscarPorId(id).get();
        odontologoService.eliminarOdontologo(id);
        // entonces
        assertEquals(id.);
    }*/
}