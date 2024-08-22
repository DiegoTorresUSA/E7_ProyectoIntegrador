package org.e7.clinica;

import org.e7.clinica.dao.impl.PacienteDaoH2;
import org.e7.clinica.db.H2Connection;
import org.e7.clinica.model.Domicilio;
import org.e7.clinica.model.Paciente;
import org.e7.clinica.service.PacienteService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PacienteServiceTest {
    static Logger logger = LoggerFactory.getLogger(PacienteServiceTest.class);
    PacienteService pacienteService = new PacienteService(new PacienteDaoH2());

    @BeforeAll
    static void tablas(){
        H2Connection.crearTablas();
    }


    @Test
    @DisplayName("Testear que un paciente se guarde en la base de datos con su domicilio")
    void caso1(){
        //dado
        Paciente paciente = new Paciente("Romero","Luciana", "56655", LocalDate.of(2024, 7, 16),
                new Domicilio("Falsa", 456, "Cipolleti", "Rio Negro"));
        // cuando
        Paciente pacienteDesdeDB = pacienteService.guardarPaciente(paciente);
        // entonces
        assertNotNull(pacienteDesdeDB.getId());
    }

    @Test
    @DisplayName("Testear que un paciente pueda ser obtenido cuando se envia el id")
    void caso2(){
        //dado
        Integer id = 1;
        // cuando
        Paciente paciente = pacienteService.buscarPorId(id);
        // entonces
        assertEquals(id, paciente.getId());
    }
//hacer test para modificar y eliminar
}