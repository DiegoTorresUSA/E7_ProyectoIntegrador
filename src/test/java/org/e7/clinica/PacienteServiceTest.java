package org.e7.clinica;


import org.e7.clinica.entity.Domicilio;
import org.e7.clinica.entity.Paciente;
import org.e7.clinica.service.impl.PacienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
class PacienteServiceTest {
    @Autowired
    PacienteService pacienteService;
    Paciente paciente;
    Paciente pacienteDesdeDb;

    @BeforeEach
    void crearPaciente(){
        Domicilio domicilio = new Domicilio(null, "falsa", 456, "Alamos", "bogotá");
        paciente = new Paciente();
        paciente.setApellido("Torres");
        paciente.setNombre("Diego");
        paciente.setDni("80189301");
        paciente.setFechaIngreso(LocalDate.of(2024, 9,9));
        paciente.setDomicilio(domicilio);
        pacienteDesdeDb = pacienteService.guardarPaciente(paciente);
    }

    @Test
    @DisplayName("Testear que un paciente se guarde en la base de datos con su domicilio")
    void caso1(){
        //dado
        // cuando
        // entonces
        assertNotNull(pacienteDesdeDb.getId());
    }

    @Test
    @DisplayName("Testear que un paciente pueda ser obtenido cuando se envia el id")
    void caso2(){
        //dado
        Integer id = 1;
        // cuando
        Paciente paciente = pacienteService.buscarPorId(id).get();
        // entonces
        assertEquals(id, paciente.getId());
    }
}
//hacer test para modificar y eliminar
