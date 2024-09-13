package org.e7.clinica.service.impl;

import org.e7.clinica.entity.Odontologo;
import org.e7.clinica.exception.ResourceNotFoundException;
import org.e7.clinica.service.IOdontologoService;
import org.springframework.stereotype.Service;
import org.e7.clinica.repository.IOdontologoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {
    private IOdontologoRepository iOdontologoRepository;

    public OdontologoService(IOdontologoRepository iOdontologoRepository) {
        this.iOdontologoRepository = iOdontologoRepository;
    }

    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return iOdontologoRepository.save(odontologo);
    }

    @Override
    public Optional<Odontologo> buscarPorId(Integer id) {
        Optional<Odontologo> odontologo = iOdontologoRepository.findById(id);
        if (odontologo.isPresent()) {
            return odontologo;//Repository.findById(id);
        } else {
            throw new ResourceNotFoundException("El odontologo con ID  " + id + "  no fue encontrado");
        }
    }

    @Override
    public List<Odontologo> buscarTodos() {
        List<Odontologo>    odontologos=iOdontologoRepository.findAll();
        if(odontologos.isEmpty()){
            throw new ResourceNotFoundException("No se encontraron odontologos");
        }
        return iOdontologoRepository.findAll();
    }

    @Override
    public void modificarOdontologo(Odontologo odontologo) {
        Optional<Odontologo> odontologoEncontrado = iOdontologoRepository.findById(odontologo.getId());
        if(odontologoEncontrado.isPresent()){
            System.out.println("Odontólogo encontrado, actualizando: " + odontologo.getId());
            iOdontologoRepository.save(odontologo);
        } else {
            System.out.println("Odontólogo no encontrado con ID: " + odontologo.getId());
            throw new ResourceNotFoundException("El odontologo  no fue encontrado");
        }
    }

    @Override
    public void eliminarOdontologo(Integer id) {
        Optional<Odontologo> odontologoEncontrado = iOdontologoRepository.findById(id);
        if(odontologoEncontrado.isPresent()){
            iOdontologoRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("El Odontologo  con ID "+ id +" no fue encontrado");
        }
    }

    @Override
    public List<Odontologo> buscarPorApellidoyNombre(String apellido, String nombre) {
        return iOdontologoRepository.findByApellidoAndNombre(apellido, nombre);
    }

    @Override
    public List<Odontologo> buscarPorNombreOderByMatricula(String nombre) {
        return iOdontologoRepository.findByNombreOrderByMatricula(nombre);
    }

    @Override
    public Long contarOdontologos() {
        return iOdontologoRepository.contarOdontologos();
    }

}



