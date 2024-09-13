package org.e7.clinica;

import org.e7.clinica.entity.Domicilio;
import org.e7.clinica.entity.Odontologo;
import org.e7.clinica.entity.Paciente;
import org.e7.clinica.entity.Turno;
import org.e7.clinica.service.impl.OdontologoService;
import org.e7.clinica.service.impl.PacienteService;
import org.e7.clinica.service.impl.TurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class TurnoServiceTest {

    @Autowired
    PacienteService pacienteService;
    Paciente paciente;
    Paciente pacienteDesdeDb;
    @Autowired
    OdontologoService odontologoService;
    Odontologo odontologo;
    Odontologo odontologoDesdeDB;
    @Autowired
    private TurnoService turnoService;

    @BeforeEach
    void crearPaciente() {
        Domicilio domicilio = new Domicilio(null, "falsa", 456, "Alamos", "bogotá");

        Paciente paciente = new Paciente();
        paciente.setApellido("osorio");
        paciente.setNombre("aleja");
        paciente.setDni("123456");
        paciente.setFechaIngreso(LocalDate.of(2024, 9, 9));
        paciente.setDomicilio(domicilio);
        pacienteDesdeDb = pacienteService.guardarPaciente(paciente);
        //System.out.println("Paciente 1: " + pacienteDesdeDb);

        Odontologo odontologo1 = new Odontologo();
        odontologo1.setNombre("sancho");
        odontologo1.setApellido("Panza");
        odontologo1.setMatricula("1");
        odontologoDesdeDB = odontologoService.guardarOdontologo(odontologo1);
        //System.out.println("Odontologo 1: " + odontologoDesdeDB);
    }

    @Test
    @DisplayName("Probar que guarde paciente y odontologo")
    void caso3(){
        assertNotNull(pacienteDesdeDb.getId());
        assertNotNull(odontologoDesdeDB.getMatricula());
    }
    @Test
    @DisplayName("Testear que se guarde un Turno")
    void caso4(){

        Turno turno = new Turno();
        turno.setPaciente(pacienteDesdeDb);
        turno.setOdontologo(odontologoDesdeDB);
        turno.setFecha(LocalDate.now());
        Turno turnoDesdeDB = turnoService.guardarTurno(turno);

        //Integer id = 1;

        //System.out.println("El paciente " + pacienteDesdeDb);
        assertNotNull(turnoDesdeDB.getId());
    }
}
