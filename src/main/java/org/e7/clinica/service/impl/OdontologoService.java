package org.e7.clinica.service.impl;

import org.e7.clinica.exception.BadRequestException;
import org.e7.clinica.entity.Odontologo;
import org.e7.clinica.exception.ResourceNotFoundException;
import org.e7.clinica.service.IOdontologoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.e7.clinica.repository.IOdontologoRepository;


import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {
    private final Logger logger = LoggerFactory.getLogger(OdontologoService.class);
    private IOdontologoRepository iOdontologoRepository;

    public OdontologoService(IOdontologoRepository iOdontologoRepository) {
        this.iOdontologoRepository = iOdontologoRepository;
    }

    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {
    if (odontologo.getNombre() == null || odontologo.getApellido()== null || odontologo.getMatricula()== null) {
            logger.error("El nombre, apellido y matricula son obligatorios. Revisa los datos e intenta nuevamente.");
            throw new BadRequestException("El nombre y apellido del odontólogo son obligatorios");
    }
        return iOdontologoRepository.save(odontologo);
    }

    @Override
    public Optional<Odontologo> buscarPorId(Integer id) {
        Optional<Odontologo> odontologo = iOdontologoRepository.findById(id);
        if (odontologo.isPresent()) {
            logger.info("odontologo encontrado: "+ odontologo.get());
            return odontologo;//Repository.findById(id);
        } else {
            logger.error("El odontologo con ID  " + id + "  no fue encontrado");
            throw new ResourceNotFoundException("El odontologo con ID  " + id + "  no fue encontrado");
        }
    }

    @Override
    public List<Odontologo> buscarTodos() {
        List<Odontologo>    odontologos=iOdontologoRepository.findAll();
        if(odontologos.isEmpty()){
            logger.error("No se encontraron odontologos");
            throw new ResourceNotFoundException("No se encontraron odontologos");
        }
        List<Odontologo> odontologosDesdeDb = iOdontologoRepository.findAll();
        logger.info("Odontologos encontrados: "+ odontologosDesdeDb.size());
        return odontologosDesdeDb;
    }

    @Override
    public void modificarOdontologo(Odontologo odontologo) {
        Optional<Odontologo> odontologoEncontrado = iOdontologoRepository.findById(odontologo.getId());
        if(odontologoEncontrado.isPresent()){
            logger.info("Odontólogo encontrado, actualizando: " + odontologo.getId());
            iOdontologoRepository.save(odontologo);
        } else {
            logger.error("Odontólogo no encontrado con ID: " + odontologo.getId());
            throw new ResourceNotFoundException("El odontologo  no fue encontrado");
        }
    }

    @Override
    public void eliminarOdontologo(Integer id) {
        Optional<Odontologo> odontologoEncontrado = iOdontologoRepository.findById(id);
        if(odontologoEncontrado.isPresent()){
            iOdontologoRepository.deleteById(id);
            logger.info("Odontologo "+odontologoEncontrado.get() + " eliminado");
        } else {
            logger.error("El Odontologo con ID" + id + " no fue encontrado");
            throw new ResourceNotFoundException("El Odontologo  con ID "+ id +" no fue encontrado");
        }
    }

    @Override
    public List<Odontologo> buscarPorApellidoyNombre(String apellido, String nombre) {
        List<Odontologo> odontologos=iOdontologoRepository.findByApellidoAndNombre(apellido, nombre);
        logger.info("Odontologos Encontrados" + odontologos.size());
        return odontologos;
    }

    @Override
    public List<Odontologo> buscarPorNombreOderByMatricula(String nombre) {
        List<Odontologo> buscarPorNombreOderByMatricula = iOdontologoRepository.findByNombreOrderByMatricula(nombre);
        logger.info("Odontologos Encontrados" + buscarPorNombreOderByMatricula.size());
        return buscarPorNombreOderByMatricula;
    }

    @Override
    public Long contarOdontologos() {
        return iOdontologoRepository.contarOdontologos();
    }

}



