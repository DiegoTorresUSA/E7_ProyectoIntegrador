package org.e7.clinica;

import org.e7.clinica.model.Odontologo;
import org.e7.clinica.dao.impl.OdontologoDaoH2;
import org.e7.clinica.service.OdontologoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class OdontologoServiceTest {
    static org.slf4j.Logger logger = LoggerFactory.getLogger(OdontologoServiceTest.class);
    OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());

    @BeforeAll
    static void crearTabla() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./clinica;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");

        }catch (Exception e) {
            logger.error(e.getMessage());
        }finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
        }
    }

    @Test
    @DisplayName("Testeamos el insert a la Base de Datos")
    void testInsert() {
        //dado
        Odontologo odontologo = new Odontologo("b1", "Alejandra", "Osorio");
        //cuando
        Odontologo odontologoDB = odontologoService.guardarOdontologo(odontologo);
        //Entonces
        assertNotNull(odontologoDB.getMatricula());

    }

    @Test
    @DisplayName("Testear la consulta de todos los odontologos en BD")
    void caso2(){
        //dado
        List<Odontologo> listaOdontologos = new ArrayList<>();
        // cuando
        listaOdontologos = odontologoService.listarOdontologo();
        logger.info(String.valueOf(listaOdontologos));
        // entonces
        assertNotNull(listaOdontologos);
    }
}