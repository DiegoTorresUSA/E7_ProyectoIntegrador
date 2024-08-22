package org.e7.clinica.dao.impl;

import org.e7.clinica.db.H2Connection;
import org.e7.clinica.dao.IDaoOdontologo;
import org.e7.clinica.model.Domicilio;
import org.e7.clinica.model.Odontologo;
import org.e7.clinica.model.Paciente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OdontologoDaoH2 implements IDaoOdontologo<Odontologo> {
    private static final Logger logger = LoggerFactory.getLogger(OdontologoDaoH2.class);
    private static final String INSERT = "INSERT INTO ODONTOLOGOS VALUES (DEFAULT,?,?,?)";
    public static final String SELECT_ID ="SELECT * FROM PACIENTES WHERE ID = ?";
    private static String SELECT_ALL = "select * from ODONTOLOGOS";
    private static String UPDATE = "UPDATE ODONTOLOGOS SET MATRICULA=?, NOMBRE=?, APELLIDO=? WHERE ID= ?";
    private static String DELETE = "DELETE FROM ODONTOLOGOS WHERE ID=?";


    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection = null;
        Odontologo odontologoARetornar = null;
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, odontologo.getMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.executeUpdate();
            connection.commit();
            logger.info("Odontologo guardado exitosamente");
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            Integer Id = null;
            if (resultSet.next()) {
                Id = resultSet.getInt(1);
                odontologoARetornar = new Odontologo(Id, odontologo.getMatricula(), odontologo.getNombre(), odontologo.getApellido());
            }
            logger.info("Información obtenida" + odontologoARetornar);
        }
        catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    logger.error(ex.getMessage());
                    e.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return odontologoARetornar;
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        Connection connection = null;
        Odontologo odontologoEncontrado = null;
        try{
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Integer Id = resultSet.getInt(1);
                String matriculaOdontologo = resultSet.getString(2);
                String nombreOdontologo = resultSet.getString(3);
                String apellidoOdontologo = resultSet.getString(4);
                odontologoEncontrado = new Odontologo(Id, matriculaOdontologo, nombreOdontologo, apellidoOdontologo);
            }
            if(odontologoEncontrado != null){
                logger.info("odontologo encontrado "+ odontologoEncontrado);
            }else logger.info("odontologo no encontrado");

        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return odontologoEncontrado;
    }
    @Override
    public List<Odontologo> listarOdontologos() {
        Connection connection = null;
        Odontologo consultaOdontologo = null;
        List<Odontologo> odontologos = new ArrayList<Odontologo>();

        try {
            connection = H2Connection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                Integer Id = resultSet.getInt(1);
                String matriculaOdontologo = resultSet.getString(2);
                String nombreOdontologo = resultSet.getString(3);
                String apellidoOdontologo = resultSet.getString(4);
                consultaOdontologo = new Odontologo(Id, matriculaOdontologo, nombreOdontologo, apellidoOdontologo);
                odontologos.add(consultaOdontologo);
                //logger.info(productos);
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
        }
        return odontologos;
    }

    @Override
    public void modificar(Odontologo odontologo) {
        Connection connection = null;
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, odontologo.getNombre());
            preparedStatement.setString(2, odontologo.getApellido());
            preparedStatement.setString(3, odontologo.getMatricula());
            preparedStatement.setInt(4, odontologo.getId());
            preparedStatement.executeUpdate();
            connection.commit();

            logger.info("Odontologo con id: " + odontologo.getId() + "Actualizado");
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    logger.error(ex.getMessage());
                    e.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public void eliminar(Integer id) {
        Connection connection = null;
        Odontologo odontologo = buscarPorId(id);
        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            if (odontologo != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                connection.commit();
            }
            logger.info("odontologo con id "+id+ " eliminado con exito" );

        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    logger.error(ex.getMessage());
                    e.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
